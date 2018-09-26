package com.fefeyo.otamanekai.view.register

import android.app.Activity.RESULT_OK
import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.Window
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.fefeyo.otamanekai.R
import com.fefeyo.otamanekai.databinding.CustomAddProductDialogBinding
import com.fefeyo.otamanekai.util.click
import com.fefeyo.otamanekai.util.toByteArray
import com.theartofdev.edmodo.cropper.CropImage
import com.theartofdev.edmodo.cropper.CropImageView

class AddProductDialogFragment : DialogFragment() {

    private val inflater by lazy {
        LayoutInflater.from(context)
    }

    private val viewModel by lazy {
        ViewModelProviders.of(this)[AddProductViewModel::class.java]
    }

    lateinit var binding: CustomAddProductDialogBinding

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        binding = DataBindingUtil.inflate(inflater, R.layout.custom_add_product_dialog, null, false)
        binding.setLifecycleOwner(this)
        binding.viewmodel = viewModel
        binding.inputImageContainer.click {
            CropImage.activity()
                    .setGuidelines(CropImageView.Guidelines.ON)
                    .setAspectRatio(4, 3)
                    .start(context!!, this)
        }
        binding.submit.click {
            viewModel.insertProductWork()
            dismiss()
        }

        val dialog = AlertDialog.Builder(activity!!)
                .setView(binding.root)
                .create()
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.window?.apply {
            setBackgroundDrawable(ColorDrawable(Color.WHITE))
            setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        }

        return dialog
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            val result = CropImage.getActivityResult(data)
            if (resultCode == RESULT_OK) {
                val image = MediaStore.Images.Media.getBitmap(activity?.contentResolver, result.uri).toByteArray()
                Glide.with(this)
                        .load(image)
                        .into(binding.inputImage)
                viewModel.setImage(image)
            }
        }
    }

    companion object {
        const val TAG = "AddProductDialogFragment"
        fun newInstance() = AddProductDialogFragment()
    }

}
