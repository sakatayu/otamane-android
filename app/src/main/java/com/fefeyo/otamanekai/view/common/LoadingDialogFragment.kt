package com.fefeyo.otamanekai.view.common

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.ImageView
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import com.bumptech.glide.Glide
import com.fefeyo.otamanekai.R

class LoadingDialogFragment: DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val view = LayoutInflater.from(context).inflate(R.layout.custom_loading_dialog, null)
        return AlertDialog.Builder(activity!!)
                .setView(view)
                .setCancelable(false)
                .create()
                .apply {
                    window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                }
    }

    companion object {
        private const val TAG = "LoadingDialogFragment"
        private val myInstance by lazy { getInstance() }
        private fun getInstance() = LoadingDialogFragment()
        fun show(fragmentManager: FragmentManager) = myInstance.show(fragmentManager, TAG)
        fun hide() = myInstance.dismiss()
    }
}
