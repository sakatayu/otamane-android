package com.fefeyo.otamanekai.view.register


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment

import com.fefeyo.otamanekai.R
import com.fefeyo.otamanekai.data.model.ProductWork
import com.fefeyo.otamanekai.databinding.FragmentEventRegisterBinding

class EventRegisterFragment : Fragment() {

    lateinit var binding: FragmentEventRegisterBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_event_register, container, false)
        val product = arguments?.get(KEY_PRODUCT) as? ProductWork
        product?.let {
            Log.d("登録する作品", "$it.name")
        }

        return binding.root
    }

    companion object {
        const val KEY_PRODUCT = "product"
    }
}
