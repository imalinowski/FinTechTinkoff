package com.malinowski.memloader

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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp)
        ) {
            val modifier = Modifier.weight(1.0f).clickable { }.height(40.dp)
            Text(
                text = "Последнее", modifier,
                textAlign = TextAlign.Center,
                color = colorResource(R.color.tinkoff_blue),
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "Лучшее", modifier,
                textAlign = TextAlign.Center
            )
            Text(
                text = "Горячее", modifier,
                textAlign = TextAlign.Center
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp)
        ) {
            val modifier = Modifier.weight(1.0f).height(2.dp)
            Spacer(
                modifier = modifier
                    .background(colorResource(R.color.tinkoff_blue))
                    .clip(RoundedCornerShape(1.dp))
            )
            Spacer(modifier = modifier)
            Spacer(modifier = modifier)
        }
    }

    @Composable
    fun MemButtons() {
        OutlinedButton(
            onClick = { },
            modifier = Modifier.size(60.dp),
            shape = CircleShape,
            elevation = ButtonDefaults.elevation()
        ) {
            Icon(
                Icons.Rounded.Replay, "Icon",
                modifier = Modifier.fillMaxSize(),
                tint = MaterialTheme.colors.primary
            )
        }

        OutlinedButton(
            onClick = { viewModel.randomMem() },
            modifier = Modifier.size(60.dp),
            shape = CircleShape,
            elevation = ButtonDefaults.elevation()
        ) {
            Icon(
                Icons.Rounded.ArrowForward, "Icon",
                modifier = Modifier.fillMaxSize(),
                tint = Color.Green,
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