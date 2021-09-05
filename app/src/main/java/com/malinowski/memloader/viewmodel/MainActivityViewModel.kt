package com.malinowski.memloader.viewmodel

import android.app.Application
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.malinowski.memloader.model.Mem
import com.android.volley.Request
import org.json.JSONObject

class MainActivityViewModel(application: Application) : AndroidViewModel(application) {
    val mem = MutableLiveData<Mem>().apply {
        value = Mem("","")
    }
    private val memStorage = mutableListOf<Mem>()
    var curMem = mutableStateOf(0)

    private val queue = Volley.newRequestQueue(getApplication())
    init{ randomMem() }

    private fun randomMem(){
        queue.add(StringRequest(
            Request.Method.GET,
            "https://developerslife.ru/random?json=true",
            { response ->
                Log.i("RASP",response)
                val obj = JSONObject(response)
                if(!obj.has("description") || !obj.has("gifURL"))
                    randomMem()
                else{
                    memStorage.add(
                        Mem(obj.getString("description"),
                            obj.getString("gifURL").replace("http","https")
                        )
                    )
                    if(curMem.value < memStorage.size)
                        mem.postValue(memStorage[curMem.value])
                }
            },
        ) { error ->
                mem.postValue(Mem("",""))
                Log.e("RASP", error.message.toString())
        })
    }

    fun nextMem(){
        if(mem.value != Mem("","")) curMem.value += 1
        if(curMem.value >= memStorage.size) randomMem()
        else mem.postValue(memStorage[curMem.value])
    }

    fun prevMem(){
        if(curMem.value > 0) curMem.value -=1
        if(curMem.value < memStorage.size)
            mem.postValue(memStorage[curMem.value])
    }
}