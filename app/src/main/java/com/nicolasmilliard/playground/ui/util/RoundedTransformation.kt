package com.nicolasmilliard.playground.ui.util

import android.graphics.Bitmap
import android.graphics.BitmapShader
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Shader
import com.squareup.picasso3.RequestHandler
import com.squareup.picasso3.Transformation

class RoundedTransformation(private val radius: Float? = null, private val margin: Float = 0f) : Transformation {
    override fun transform(source: RequestHandler.Result): RequestHandler.Result {
        val bitmap = source.bitmap ?: return source

        val result = Bitmap.createBitmap(bitmap.width, bitmap.height, bitmap.config)

        val paint = Paint().apply {
            isAntiAlias = true
            shader = BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP)
        }

        Canvas(result).drawRoundRect(
            margin, margin, bitmap.width - margin, bitmap.height - margin,
            radius ?: bitmap.width.toFloat() / 2, radius ?: bitmap.height.toFloat() / 2,
            paint
        )

        bitmap.recycle()

        return RequestHandler.Result(result, source.loadedFrom, source.exifRotation)
    }

    override fun key(): String {
        return "rounded(radius=$radius, margin=$margin)"
    }
}