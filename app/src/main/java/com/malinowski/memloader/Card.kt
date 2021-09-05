package com.malinowski.memloader

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.malinowski.memloader.model.Mem
import com.skydoves.landscapist.glide.GlideImage

val mem = mutableStateOf(Mem("",""))

@Composable
fun Card(modifier: Modifier = Modifier, mem_: Mem){
    mem.value = mem_
    ConstraintLayout (
        modifier = Modifier
            .shadow(1.dp, shape = RoundedCornerShape(15.dp))
    ) {
        val (text,gradient) = createRefs()
        GlideImage(
            imageModel = mem.value.gifUrl,
            modifier = modifier.fillMaxSize(),
            contentScale = ContentScale.Fit,
            // shows a progress indicator when loading an image.
            loading = {
                ConstraintLayout(
                    modifier = Modifier.fillMaxSize()
                ) {
                    val indicator = createRef()
                    CircularProgressIndicator(
                        modifier = Modifier.constrainAs(indicator) {
                            top.linkTo(parent.top)
                            bottom.linkTo(parent.bottom)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                        }
                    )
                }
            },
            // shows an error text message when request failed.
            failure = {
                Text(text = "image request failed.", modifier = Modifier.fillMaxSize(), textAlign = TextAlign.Center)
            })
        Box(
            modifier = Modifier.fillMaxWidth().height(50.dp)
                .background(
                Brush.verticalGradient(
                    listOf(Color.Transparent, Color.Gray),
                )
                ).constrainAs(gradient) {
                    bottom.linkTo(parent.bottom)
                }
        ) {}
        Text(
            text = mem.value.text,
            textAlign = TextAlign.Center,
            color = Color.White,
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
                .constrainAs(text) {
                    bottom.linkTo(parent.bottom)
                }
        )
    }
}

@Preview
@Composable
fun cardPreview(){
    Card(mem_= Mem("mem load ",""))
}
fun createImage(width: Int, height: Int, color: Color): ImageBitmap {
    val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
    val canvas = Canvas(bitmap)
    val paint = Paint()
    paint.color = color.hashCode()
    canvas.drawRect(0f, 0f, width.toFloat(), height.toFloat(), paint)
    return bitmap.asImageBitmap()
}