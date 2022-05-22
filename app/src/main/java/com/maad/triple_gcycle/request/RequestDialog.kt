package com.maad.triple_gcycle.request

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.maad.triple_gcycle.R

class RequestDialog: DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
       val builder = AlertDialog.Builder(activity)
        val inflater = requireActivity().layoutInflater
        builder
            .setView(inflater.inflate(R.layout.request_dialog, null))
        return builder.create()
    }

}