package com.fefeyo.otamanekai.view.main


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.fefeyo.otamanekai.R
import com.fefeyo.otamanekai.databinding.FragmentMainBinding

class MainFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val binding = DataBindingUtil.inflate<FragmentMainBinding>(inflater, R.layout.fragment_main, container, false)
        binding.setLifecycleOwner(this)

        binding.bottomNavigation.setOnNavigationItemSelectedListener { menu ->
            val navigation = childFragmentManager.findFragmentById(R.id.navigation)?.view
            navigation?.let {
                when(menu.itemId) {
                    R.id.calendar -> Navigation.findNavController(navigation).navigate(R.id.to_calendar_fragment)
                    R.id.event -> Navigation.findNavController(navigation).navigate(R.id.to_event_fragment)
                    R.id.ticket -> Navigation.findNavController(navigation).navigate(R.id.to_ticket_fragment)
                    R.id.settings -> Navigation.findNavController(navigation).navigate(R.id.to_setting_fragment)
                }
            }

            true
        }

        return binding.root
    }
}
