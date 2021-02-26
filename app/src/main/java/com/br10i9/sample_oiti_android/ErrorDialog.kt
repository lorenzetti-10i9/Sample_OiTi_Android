package com.br10i9.sample_oiti_android

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatDialogFragment

class ErrorDialog : AppCompatDialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val view = LayoutInflater.from(context).inflate(R.layout.error_dialog, null)
        val bundle = Bundle()
        bundle.putString("action", "")
        view.findViewById<Button>(R.id.retryButton)?.setOnClickListener {
            bundle.remove("action")
            bundle.putString("action", "retry")
            dialog?.dismiss()
            parentFragmentManager.setFragmentResult("errorDialog", bundle)
        }
        return AlertDialog.Builder(this.requireContext())
                .setView(view)
                .create()
    }
}
