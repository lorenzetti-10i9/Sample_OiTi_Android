package com.br10i9.sample_oiti_android

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatDialogFragment

class ErrorDialog : AppCompatDialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val view = LayoutInflater.from(context).inflate(R.layout.error_dialog, null)

        return AlertDialog.Builder(this.requireContext())
                .setView(view)
                .create()
    }
}
