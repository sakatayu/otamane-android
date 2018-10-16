package com.fefeyo.otamanekai.view.calendar


import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import com.fefeyo.otamanekai.MainActivity
import com.fefeyo.otamanekai.R
import com.fefeyo.otamanekai.data.model.Event
import com.fefeyo.otamanekai.databinding.FragmentCalendarBinding
import com.fefeyo.otamanekai.util.dataenum.EventType
import com.fefeyo.otamanekai.util.slideInUp
import com.fefeyo.otamanekai.util.slideOutDown
import com.fefeyo.otamanekai.util.weekOfMonth
import kotlinx.android.synthetic.main.fragment_calendar.*

class CalendarFragment : Fragment() {

    private lateinit var binding: FragmentCalendarBinding

    private val viewModel by lazy {
        ViewModelProviders.of(this)[CalendarViewModel::class.java]
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        setHasOptionsMenu(true)
        activity?.invalidateOptionsMenu()
        (activity as? MainActivity)?.apply {
            switchTitle(R.string.app_name)
            setUpNavigationBack(false)
        }

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_calendar, container, false)
        binding.setLifecycleOwner(this)
        binding.calendar.setOnDateChangedListener { _, calendarDay, _ ->
            viewModel.getEventFromDate(calendarDay.date).observe(this, Observer {
                Log.d("きたお", it.size.toString())
                binding.eventList.events = it
                if (it.isNotEmpty() && !binding.eventList.isVisible) {
                    binding.eventList.slideInUp {
                        if (calendarDay.date.weekOfMonth() > 3) {
                            binding.calendarWrapper.smoothScrollTo(0, binding.calendarWrapper.bottom)
                        }
                    }
                } else if (it.isEmpty()) {
                    if (binding.eventList.isVisible) {
                        binding.eventList.slideOutDown()
                    }
                }
            })
        }

        viewModel.allEvents.observe(this, Observer {
            binding.calendar.addDecorators(splitAndGenerateDecorator(it))
        })

        viewModel.load()

        return binding.root
    }

    private fun splitAndGenerateDecorator(events: List<Event>): MutableList<EventDecorator> =
            mutableListOf<EventDecorator>().apply {
                EventType.values().forEach { type ->
                    add(EventDecorator(activity!!, type, events.asSequence()
                            .filter { it.eventType == type }
                            .map { it.date }
                            .toList())
                    )
                }
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
