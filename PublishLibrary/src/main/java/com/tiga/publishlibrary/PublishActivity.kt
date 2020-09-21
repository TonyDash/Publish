package com.tiga.publishlibrary

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class PublishActivity : AppCompatActivity() {

    companion object {
        fun go(context: Context) {
            val intent = Intent(context, PublishActivity::class.java).apply {

            }
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_publish)
    }
}