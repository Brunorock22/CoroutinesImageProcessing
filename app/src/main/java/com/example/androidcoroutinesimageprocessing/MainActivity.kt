package com.example.androidcoroutinesimageprocessing

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import java.net.URL

class MainActivity : AppCompatActivity() {

    private val IMAGE_URL =
        "https://www.infoescola.com/wp-content/uploads/2010/10/Bulldog-Ingl%C3%AAs_607907102.jpg"
    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        coroutineScope.launch {
            val originalDefered = coroutineScope.async(Dispatchers.IO) { getOriginalBitmap() }
            val originalBitmap = originalDefered.await()
            val filterDefered = coroutineScope.async(Dispatchers.Default) { Filter.apply(originalBitmap) }
            loadImage(filterDefered.await())
        }
    }

    private fun getOriginalBitmap() =
        URL(IMAGE_URL).openStream().use {
            BitmapFactory.decodeStream(it)
        }

    private fun loadImage(bitMap: Bitmap) {
        progressBar.visibility = View.GONE
        imageView.setImageBitmap(bitMap)
        imageView.visibility = View.VISIBLE
    }

}