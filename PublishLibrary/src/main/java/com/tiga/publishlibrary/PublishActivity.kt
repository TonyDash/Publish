package com.tiga.publishlibrary

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.tiga.publishlibrary.adapter.PublishPhotoAdapter
import kotlinx.android.synthetic.main.activity_publish.*

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
        initView()
    }

    private fun initView() {
        rv_photos.adapter = PublishPhotoAdapter(this, mutableListOf())
        rv_photos.layoutManager = GridLayoutManager(this,3)
        rv_photos.isNestedScrollingEnabled = false
    }
}