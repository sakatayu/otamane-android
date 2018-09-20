package com.fefeyo.otamanekai.view.calendar

import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import com.fefeyo.otamanekai.R
import com.fefeyo.otamanekai.data.model.Event
import com.fefeyo.otamanekai.databinding.ItemCalendarEventBinding
import com.fefeyo.otamanekai.view.common.AbstractViewHolder

class CalendarEventPagedListAdapter:
        PagedListAdapter<Event, CalendarEventPagedListAdapter.CalendarEventViewHolder>(ITEM_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CalendarEventViewHolder =
            CalendarEventViewHolder(parent)

    override fun onBindViewHolder(holder: CalendarEventViewHolder, position: Int) =
            holder.bind(getItem(position))


    class CalendarEventViewHolder(parent: ViewGroup):
            AbstractViewHolder<ItemCalendarEventBinding>(parent, R.layout.item_calendar_event) {
        fun bind(event: Event?) {
            binding.event = event
        }
    }

    companion object {
        private val ITEM_CALLBACK = object : DiffUtil.ItemCallback<Event>() {
            override fun areItemsTheSame(oldItem: Event, newItem: Event): Boolean =
                    oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Event, newItem: Event): Boolean =
                    oldItem == newItem
        }
    }
}
