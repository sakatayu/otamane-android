package com.fefeyo.otamanekai.view.common

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.fefeyo.otamanekai.R
import com.fefeyo.otamanekai.data.model.Stuff
import com.fefeyo.otamanekai.databinding.CustomInputStuffDialogBinding
import com.fefeyo.otamanekai.util.click

class InputStuffDialogFragment : DialogFragment() {

    var listener: OnInputStuffListener? = null

    private val stuff by lazy {
        arguments?.get(KEY_STUFF) as Stuff
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        listener = parentFragment as OnInputStuffListener
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = DataBindingUtil.inflate<CustomInputStuffDialogBinding>(inflater,
                R.layout.custom_input_stuff_dialog, container, false)
        val viewModel = InputStuffDialogViewModel()
                .apply {
                    name.postValue(stuff.name)
                    displayPrice.postValue(stuff.price.toString())
                    isMust.postValue(stuff.isMust)
                    isAdvance.postValue(stuff.isAdvance)
                }
        binding.viewmodel = viewModel
        binding.submit.click {
            listener?.onInput(viewModel.generateStuff(stuff))
        }

        return binding.root
    }

    class InputStuffDialogViewModel : ViewModel() {
        val name = MutableLiveData<String>()
        val displayPrice = MutableLiveData<String>()
        val isMust = MutableLiveData<Boolean>().apply { postValue(false) }
        val isAdvance = MutableLiveData<Boolean>().apply { postValue(false) }

        fun submitAvailable() = name.value?.isNotEmpty() == true && displayPrice.value?.isNotEmpty() == true

        fun generateStuff(stuff: Stuff) = stuff.copy(
                name = name.value!!,
                price = displayPrice.value?.toInt()!!,
                isMust = isMust.value!!,
                isAdvance = isAdvance.value!!
        )
    }

    interface OnInputStuffListener {
        fun onInput(stuff: Stuff)
    }

    companion object {
        const val KEY_STUFF = "key_stuff"
        fun newInstance(stuff: Stuff) = InputStuffDialogFragment().apply {
            arguments = bundleOf(KEY_STUFF to stuff)
        }
    }
}
