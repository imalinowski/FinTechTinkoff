package com.malinowski.memloader

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowForward
import androidx.compose.material.icons.rounded.Replay
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.malinowski.memloader.ui.theme.MemLoaderTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MemLoaderTheme {
                MemScreen()
            }
        }
    }
}

@Composable
fun MemScreen() {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = "Developers Life",
            modifier = Modifier,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            fontSize = 30.sp
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {
            val modifier = Modifier.weight(1.0f)
            Text(text = "Последнее", modifier, textAlign = TextAlign.Center)
            Text(text = "Лучшее", modifier, textAlign = TextAlign.Center)
            Text(text = "Горячее", modifier, textAlign = TextAlign.Center)
        }
        Box(
            modifier = Modifier
                .weight(2.0f)
                .fillMaxSize()
                .padding(10.dp)
        ) {
            Card(bitmap = createImage(300, 300, Color.Yellow))
        }
        Row(
            Modifier.padding(10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            OutlinedButton(
                onClick = { },
                modifier = Modifier.size(60.dp),
                shape = CircleShape,
                elevation = ButtonDefaults.elevation(defaultElevation = 1.dp)
            ) {
                Icon(
                    Icons.Rounded.Replay, "Icon",
                    modifier = Modifier.fillMaxSize(),
                    tint = MaterialTheme.colors.primaryVariant
                )
            }

            OutlinedButton(
                onClick = { },
                modifier = Modifier.size(60.dp),
                shape = CircleShape,
                elevation = ButtonDefaults.elevation(defaultElevation = 1.dp)
            ) {
                Icon(
                    Icons.Rounded.ArrowForward, "Icon",
                    modifier = Modifier.fillMaxSize(),
                    tint = MaterialTheme.colors.primaryVariant,
                )
            }
        }
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