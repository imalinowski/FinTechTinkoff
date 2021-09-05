package com.malinowski.memloader.viewmodel

import android.app.Application
import android.util.Log
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
    private val queue = Volley.newRequestQueue(getApplication())
    init{ randomMem() }
    fun randomMem(){
        queue.add(StringRequest(
            Request.Method.GET,
            "https://developerslife.ru/random?json=true",
            { response ->
                Log.i("RASP",response)
                val obj = JSONObject(response)
                if(!obj.has("description") || !obj.has("gifURL"))
                    randomMem()
                else mem.postValue(
                    Mem(obj.getString("description"),
                        obj.getString("gifURL").replace("http","https")
                    )
                )
            },
        ) { error -> Log.e("RASP", error.message.toString()) })
    }
}