package com.example.androidcoroutinesimageprocessing

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.androidcoroutinesimageprocessing.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import java.net.URL

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: MainViewModel
    private val IMAGE_URL =
        "https://www.infoescola.com/wp-content/uploads/2010/10/Bulldog-Ingl%C3%AAs_607907102.jpg"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)

        viewModel.imageBitMap.observe(this, Observer { result ->
            loadImage(result)
        })
        viewModel.getImageFromUrl(IMAGE_URL)
    }

    private fun loadImage(bitMap: Bitmap) {
        progressBar.visibility = View.GONE
        imageView.setImageBitmap(bitMap)
        imageView.visibility = View.VISIBLE
    }

}