package com.fefeyo.otamanekai.view.calendar


import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.fefeyo.otamanekai.R
import com.fefeyo.otamanekai.databinding.FragmentCalendarBinding
import com.fefeyo.otamanekai.util.slideInUp
import com.fefeyo.otamanekai.util.slideOutDown
import com.fefeyo.otamanekai.util.weekOfMonth

class CalendarFragment : Fragment() {

    private lateinit var binding: FragmentCalendarBinding
    var flag = false

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        setHasOptionsMenu(true)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_calendar, container, false)
        binding.setLifecycleOwner(this)
        binding.calendar.setOnDateChangedListener { _, calendarDay, _ ->
            if (flag) {
                binding.eventList.slideOutDown()
            } else {
                binding.eventList.slideInUp {
                    if (calendarDay.date.weekOfMonth() > 3) {
                        binding.calendarWrapper.smoothScrollTo(0, binding.calendarWrapper.bottom)
                    }
                }
            }
            flag = flag.not()
        }

        return binding.root
    }


    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(R.menu.calendar_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.insert -> {
                Navigation.findNavController(activity!!, R.id.main_navigation)
                        .navigate(R.id.action_mainFragment_to_chooseProductFragment)
            }
        }
        return super.onOptionsItemSelected(item)
    }

}
