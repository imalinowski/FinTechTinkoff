package com.malinowski.memloader.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowForward
import androidx.compose.material.icons.rounded.Replay
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.malinowski.memloader.R
import com.malinowski.memloader.model.Mem
import com.malinowski.memloader.ui.theme.MemLoaderTheme
import com.malinowski.memloader.viewmodel.MainActivityViewModel

class MainActivity : ComponentActivity() {
    val viewModel: MainActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.mem.observe(this, {
            mem.value = it
        })
        setContent {
            MemLoaderTheme {
                MemScreen()
            }
        }
    }

    @Composable
    fun MemScreen() {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = "Developers Life",
                modifier = Modifier.padding(10.dp),
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                fontSize = 30.sp
            )
            MemNavigator()

            Box(
                modifier = Modifier
                    .weight(2.0f)
                    .fillMaxSize()
                    .padding(10.dp)
            ) {

                Card(mem_ = Mem("", ""))
            }

            Row(
                Modifier.padding(10.dp),
                verticalAlignment = CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                MemButtons()
            }
        }
    }

    @Composable
    fun MemNavigator() {
        var curCategory by remember { mutableStateOf(0) }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp)
        ) {
            val modifier = Modifier.weight(1.0f).clip(RoundedCornerShape(3.dp)).height(40.dp)
            val categories = listOf("Последнее","Лучшее","Горячее")
            for ((i,v) in categories.withIndex()){
                if (curCategory == i) Text(
                    text = v, modifier.clickable { curCategory = i },
                    textAlign = TextAlign.Center,
                    color = colorResource(R.color.tinkoff_blue),
                    fontWeight = FontWeight.Bold
                )
                else Text(
                    text = v, modifier.clickable {
                        curCategory = i
                        viewModel.nextMem()
                    },
                    textAlign = TextAlign.Center
                )
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp)
        ) {
            val modifier = Modifier.weight(1.0f).height(2.dp)
            for (i in 0..2)
                Spacer(
                    modifier =
                    if(curCategory == i)
                        modifier.background(colorResource(R.color.tinkoff_blue))
                    else modifier
                )
        }
    }

    @Composable
    fun MemButtons() {
        OutlinedButton(
            onClick = { viewModel.prevMem() },
            modifier = Modifier.size(60.dp),
            shape = CircleShape,
            elevation = ButtonDefaults.elevation(),
            enabled = viewModel.curMem.value != 0
        ) {
            Icon(
                Icons.Rounded.Replay, "Icon",
                modifier = Modifier.fillMaxSize(),
                tint = MaterialTheme.colors.primary
            )
        }

        OutlinedButton(
            onClick = { viewModel.nextMem() },
            modifier = Modifier.size(60.dp),
            shape = CircleShape,
            elevation = ButtonDefaults.elevation()
        ) {
            Icon(
                Icons.Rounded.ArrowForward, "Icon",
                modifier = Modifier.fillMaxSize(),
                tint = MaterialTheme.colors.primary,
            )
        }
    }

    @Preview(
        showBackground = true
    )
    @Composable
    fun defaultPreview() {
        MemLoaderTheme {
            MemScreen()
        }
    }
}