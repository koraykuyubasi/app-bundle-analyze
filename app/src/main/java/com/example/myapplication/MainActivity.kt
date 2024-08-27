package com.example.myapplication

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.example.myapplication.ui.theme.MyApplicationTheme
import com.example.libB.LibB
import java.lang.annotation.Native
import java.util.Locale

class MainActivity : ComponentActivity() {
    companion object {
        init {
            System.loadLibrary("libA")
        }
    }

    private external fun stringFromTest(): String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val cpp = stringFromTest()
        val countryCode = getString(R.string.app_name)
        setContent {
            MyApplicationTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = cpp,
                        local = countryCode,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }

//    private lateinit var splitInstallManager: SplitInstallManager
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
//
//        splitInstallManager = SplitInstallManagerFactory.create(this)
//
//        buttonRequestFeature.setOnClickListener {
//            val request = SplitInstallRequest.newBuilder()
//                .addModule("dynamicFeature")
//                .build()
//
//            splitInstallManager.startInstall(request)
//                .addOnSuccessListener {
//                    // Feature installed successfully
//                }
//                .addOnFailureListener {
//                    // Failed to install the feature
//                }
//        }
//    }
//}

    @Composable
    fun Greeting(name: String, local: String, modifier: Modifier = Modifier) {
        val context = LocalContext.current
        val configuration: Configuration = context.resources.configuration
        val stringFromLibB = LibB.testt()
        Text(
            text = "$name! $local, $stringFromLibB",
            modifier = modifier
        )
    }
}