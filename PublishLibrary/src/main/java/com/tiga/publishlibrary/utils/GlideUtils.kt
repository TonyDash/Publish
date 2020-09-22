package com.tiga.publishlibrary.utils

import android.app.Activity
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.BitmapImageViewTarget
import com.tiga.publishlibrary.R

class GlideUtils {

    companion object {
        fun changeGifToPicture(context: Activity, url: String, imageView: ImageView?) {
            imageView?.run {
                var options = RequestOptions()
                options = options.transform(
                    CenterCrop(),
                    RoundedCorners(context.resources.getDimensionPixelOffset(R.dimen.dp_4))
                )
                Glide.with(context)
                    .asBitmap()
                    .load(url)
                    .apply(options)
                    .into(object : BitmapImageViewTarget(imageView) {
                        override fun setResource(resource: Bitmap?) {
                            val drawable: Drawable = BitmapDrawable(context.resources, resource)
                            imageView.setImageDrawable(drawable)
                        }
                    })
            }
        }

        fun loadRound(context: Activity, url: String, imageView: ImageView?) {
            imageView?.run {
                var options = RequestOptions()
                options = options.transform(
                    CenterCrop(),
                    RoundedCorners(context.resources.getDimensionPixelOffset(R.dimen.dp_4))
                )
                Glide.with(context)
                    .load(url)
                    .apply(options)
                    .into(imageView)
            }
        }
    }

}