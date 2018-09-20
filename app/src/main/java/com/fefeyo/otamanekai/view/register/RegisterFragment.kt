package com.fefeyo.otamanekai.view.register


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.fefeyo.otamanekai.R
import com.fefeyo.otamanekai.databinding.FragmentRegisterBinding


class RegisterFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val binding = DataBindingUtil.inflate<FragmentRegisterBinding>(inflater, R.layout.fragment_register, container, false)
        binding.setLifecycleOwner(this)

        return binding.root
    }

    companion object {
        const val KEY_EVENT_TYPE_ID = "key_event_type_id"
    }

}
