package com.malinowski.memloader

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun Card(modifier: Modifier = Modifier, bitmap: ImageBitmap){
    Image(
        bitmap = bitmap,
        contentDescription = "card",
        contentScale = ContentScale.Crop,
        modifier = modifier
            .fillMaxSize()
            .clip(RoundedCornerShape(15.dp))
    )
}

@Preview
@Composable
fun cardPreview(){
    Card(bitmap = createImage(300,400, Color.Cyan))
}
fun createImage(width: Int, height: Int, color: Color): ImageBitmap {
    val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
    val canvas = Canvas(bitmap)
    val paint = Paint()
    paint.color = color.hashCode()
    canvas.drawRect(0f, 0f, width.toFloat(), height.toFloat(), paint)
    return bitmap.asImageBitmap()
}