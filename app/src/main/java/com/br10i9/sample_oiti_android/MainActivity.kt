package com.br10i9.sample_oiti_android

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentResultListener
import br.com.oiti.certiface.FaceCaptchaActivity
import br.com.oiti.certiface.UserData

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
        } else {
            val appKey = APP_KEY
            val intent = Intent(this, FaceCaptchaActivity::class.java).apply {
                putExtra(FaceCaptchaActivity.PARAM_ENDPOINT, ENDPOINT)
                putExtra(FaceCaptchaActivity.PARAM_USER_DATA, UserData(appKey = appKey))
            }
            startActivityForResult(intent, CAPTCHA_RESULT_REQUEST)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == CAPTCHA_RESULT_REQUEST) {
            when (resultCode) {
                Activity.RESULT_OK -> onResultSuccess(data)
                Activity.RESULT_CANCELED -> onResultCancelled(data)
            }
        }
    }

    private fun onResultSuccess(data: Intent?) {
        val okDialog = OkDialog()
        okDialog.show(supportFragmentManager, "okDialog")
    }

    private fun onResultCancelled(data: Intent?) {
        val errorDialog = ErrorDialog()
        supportFragmentManager.setFragmentResultListener(
                "errorDialog",
                this,
                FragmentResultListener { _, result ->
                    if (result["action"] == "retry") {
                        onBiometriaButtonPressed()
                    }
                })
        errorDialog.show(supportFragmentManager, "errorDialog")
    }

    companion object {
        private val TAG = MainActivity::class.java.simpleName
        private const val CAPTCHA_RESULT_REQUEST = 1
        private const val ENDPOINT = "https://comercial.certiface.com.br:443"
        private const val APP_KEY = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJjZXJ0aWZhY2UiLCJ1c2VyIjoiNUFFMzRFMDU2QkU0RUZDRDI5MkFGQTUzODU0OTE1RURFQUQyfGZlbGlwZS5tb2JpbGUiLCJlbXBDb2QiOiIwMDAwMDAwMDAxIiwiZmlsQ29kIjoiMDAwMDAwMjU3OCIsImNwZiI6Ijg3OTc3Mzg2MDM4Iiwibm9tZSI6IjdBRUQ1NkU1NDFBMzdDNjEyMTE5QjA2MENBQUE0NkM1QTM1QzhBMTFBOEVEQUQ2NEQxNzNDNUY3NTUwRTZFNTM1QUREMUU3RDU2NEREQzFCQzg1QTVBMTczMDVGRDIzRDI1MkVFMEJERkM5OTYxRUI3OThCMDBDQkVBOTYyMUVFQTdEOUR8TUFVUklDSU8gQkVaRVJSQSIsIm5hc2NpbWVudG8iOiIwMS8wMS8xOTkwIiwiZWFzeS1pbmRleCI6IkFBQUFFb3ZRaVNyYUdNazhVMUhJR05kOEFCQ3RuQmFmRUNkN2ZMM05YUERIdm5SaEpBekVhNzF2VEJha0pnPT0iLCJrZXkiOiJUWFZqYUNCbGRtbHNJSE52YjI0Z2FHbG5hQ0JwYmlCb2IzQmxJR1J2SUhacFpYYz0iLCJleHAiOjE2MTQzMzIyNjgsImlhdCI6MTYxNDMzMTk2OH0.nGgJV1fe9Upe81Pt4yQptHgn8rHP1UyuXM5BVLL0gt4"
    }
}
