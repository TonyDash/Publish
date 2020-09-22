package com.tiga.publishlibrary.utils

import android.app.Activity
import android.content.Context
import android.util.DisplayMetrics
import android.view.WindowManager

class UIUtils {
    companion object{
        /**
         * dip-->px
         */
        fun dip2Px(context: Context,dip: Int): Int {
            val density: Float = context.resources.displayMetrics.density
            return (dip * density + 0.5f).toInt()
        }
        /**
         * dip-->px
         */
        fun dip2Px(context: Activity, dip: Int): Int {
            val density: Float = context.resources.displayMetrics.density
            return (dip * density + 0.5f).toInt()
        }

        /**
         * 获取屏幕宽度
         *
         * @param context
         * @return
         */
        fun getScreenWidth(context: Context): Int {
            val windowManager =
                context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
            val dm = DisplayMetrics()
            //取得窗口属性
            windowManager.defaultDisplay.getMetrics(dm)
            return dm.widthPixels
        }
    }
}