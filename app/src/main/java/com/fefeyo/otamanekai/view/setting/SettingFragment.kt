package com.fefeyo.otamanekai.view.setting


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.fefeyo.otamanekai.R
import com.fefeyo.otamanekai.databinding.FragmentSettingBinding


class SettingFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val binding = DataBindingUtil.inflate<FragmentSettingBinding>(inflater, R.layout.fragment_setting, container, false)
        binding.setLifecycleOwner(this)

        return binding.root
    }


}
