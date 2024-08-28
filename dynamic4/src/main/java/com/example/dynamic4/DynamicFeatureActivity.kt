package com.example.dynamic4


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class DynamicFeatureActivity : AppCompatActivity() {
    companion object {
        init {
            System.loadLibrary("libDynamic4")
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dynamic4)
    }
}
