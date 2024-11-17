package com.mehnaz.gozayaanproject.utils

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF
import com.bumptech.glide.load.Key
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation
import java.security.MessageDigest

class BottomRoundedCornersTransformation(private val radius: Float) : BitmapTransformation() {

    override fun transform(pool: com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool, toTransform: Bitmap, outWidth: Int, outHeight: Int): Bitmap {
        val paint = Paint()
        paint.isAntiAlias = true
        paint.shader = android.graphics.BitmapShader(toTransform, android.graphics.Shader.TileMode.CLAMP, android.graphics.Shader.TileMode.CLAMP)

        val output = Bitmap.createBitmap(toTransform.width, toTransform.height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(output)

        // Draw a rounded rect with rounded corners at the bottom
        val rectF = RectF(0f, (toTransform.height - radius), toTransform.width.toFloat(), toTransform.height.toFloat())
        canvas.drawRoundRect(rectF, radius, radius, paint)

        return output
    }

    override fun updateDiskCacheKey(messageDigest: MessageDigest) {
        messageDigest.update("bottom_round_corners".toByteArray(Charsets.UTF_8))
    }
}
