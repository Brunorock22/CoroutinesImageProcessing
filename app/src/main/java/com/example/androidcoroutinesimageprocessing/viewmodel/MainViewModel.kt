package com.example.androidcoroutinesimageprocessing.viewmodel

import android.app.Application
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.androidcoroutinesimageprocessing.Filter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.net.URL

class MainViewModel(application: Application) : AndroidViewModel(application) {
    var imageBitMap = MutableLiveData<Bitmap>()

    fun getImageFromUrl(url: String) {
        GlobalScope.launch(Dispatchers.IO) {
            URL(url).openStream().use {
                // Use liveData.postValue(value) instead of liveData.value = value. It is called asynchronous.
                imageBitMap.postValue(BitmapFactory.decodeStream(it))
                delay(5000L)
                //Apply BLACK/WHITE filter after some time
                imageBitMap.postValue(imageBitMap.value?.let { it1 -> Filter.apply(it1) })
            }
        }
    }
}