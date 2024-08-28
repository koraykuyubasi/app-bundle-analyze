package com.example.dynamic3


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class DynamicFeatureActivity : AppCompatActivity() {
    companion object {
        init {
            System.loadLibrary("libDynamic3")
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dynamic3)
    }
}
