package com.br10i9.sample_oiti_android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // splash screen dismiss
        setTheme(R.style.Theme_Sample_OiTi_Android)
        setContentView(R.layout.activity_main)
    }
}