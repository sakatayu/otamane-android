package com.fefeyo.otamanekai.view.event


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment

import com.fefeyo.otamanekai.R
import com.fefeyo.otamanekai.databinding.FragmentEventBinding

class EventFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val binding = DataBindingUtil.inflate<FragmentEventBinding>(inflater, R.layout.fragment_event, container, false)
        binding.setLifecycleOwner(this)

        return binding.root
    }


}
