package com.LosMugiwaras.distribucion

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var change: ImageView
    private var currentImageIndex = 0

    private val images = listOf(
        R.mipmap.batman,
        R.mipmap.superman,
        R.mipmap.flash,
        R.mipmap.greenarrow,
        R.mipmap.wonderwoman,
        R.mipmap.greenlanter
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        change = findViewById(R.id.change)
        if (savedInstanceState != null) {
            currentImageIndex = savedInstanceState.getInt("currentImageIndex")
        }
        change.setImageResource(images[currentImageIndex])

        val imageMap = mapOf(
            R.id.imageBatman to R.mipmap.batman,
            R.id.imageSuperman to R.mipmap.superman,
            R.id.imageFlash to R.mipmap.flash,
            R.id.imageGA to R.mipmap.greenarrow,
            R.id.imageWW to R.mipmap.wonderwoman,
            R.id.imageGL to R.mipmap.greenlanter

        )

        imageMap.forEach { (imageViewId, imageResId) ->
            findViewById<ImageView>(imageViewId).setOnClickListener {
                change.setImageResource(imageResId)
                currentImageIndex = images.indexOf(imageResId)
            }
        }

        change.setOnClickListener {
            currentImageIndex = (currentImageIndex + 1) % images.size
            change.setImageResource(images[currentImageIndex])
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("currentImageIndex", currentImageIndex)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        currentImageIndex = savedInstanceState.getInt("currentImageIndex")
        change.setImageResource(images[currentImageIndex])
    }
}
