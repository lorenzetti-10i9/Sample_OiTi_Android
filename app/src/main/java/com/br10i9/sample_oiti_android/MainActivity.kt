package com.br10i9.sample_oiti_android

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_Sample_OiTi_Android)
        setContentView(R.layout.activity_main)

        if (applicationContext.packageManager.hasSystemFeature(PackageManager.FEATURE_CAMERA_FRONT)) {
            findViewById<Button>(R.id.biometriaButton).setOnClickListener { onBiometriaButtonPressed() }
        }
    }

    private fun onBiometriaButtonPressed() {
        val permissionStatus = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
        if (permissionStatus != PackageManager.PERMISSION_GRANTED) {
            startActivity(Intent(this, PermissionActivity::class.java))
        }
    }
}