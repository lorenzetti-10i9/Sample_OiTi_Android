package com.br10i9.sample_oiti_android

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

class PermissionActivity : AppCompatActivity() {

    private val requestPermissionLauncher = this.registerForActivityResult(ActivityResultContracts.RequestPermission())
        {
            isGranted: Boolean ->
            finish()
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_Sample_OiTi_Android)
        setContentView(R.layout.permission_activity)

        val backButton = findViewById<ImageButton>(R.id.backButton)
        backButton.setOnClickListener { finish() }

        val permissionButton = findViewById<Button>(R.id.permissionButton)
        permissionButton.setOnClickListener { onPermissionButtonPressed() }

    }

    private fun onPermissionButtonPressed() {
        val permissionStatus = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
        if (permissionStatus != PackageManager.PERMISSION_GRANTED) {
            requestPermissionLauncher.launch(Manifest.permission.CAMERA)
        }
    }
}