package com.fefeyo.otamanekai.view.common.customview

import android.content.Context
import android.util.AttributeSet
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.fefeyo.otamanekai.R
import com.fefeyo.otamanekai.data.model.Event
import com.fefeyo.otamanekai.databinding.ItemEventListBinding
import com.fefeyo.otamanekai.util.click
import com.fefeyo.otamanekai.util.dataenum.EventType
import com.fefeyo.otamanekai.util.setDrawables
import com.fefeyo.otamanekai.util.simpleFormat
import com.fefeyo.otamanekai.view.common.AbstractViewHolder

class EventListRecyclerView @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyle: Int = 0
) : RecyclerView(context, attrs, defStyle) {

    var onClickItem: (event: Event) -> Unit = {}
    private val adapter = EventListAdapter()
    var events: List<Event>? = null
        set(value) {
            field = value
            adapter.submitList(value)
        }

    init {
        setAdapter(adapter)
    }

    inner class EventListAdapter : ListAdapter<Event, EventListAdapter.EventViewHolder>(ITEM_CALLBACK) {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder =
                EventViewHolder(parent)

        override fun onBindViewHolder(holder: EventViewHolder, position: Int) =
                holder.bind(getItem(position))

        inner class EventViewHolder(parent: ViewGroup) :
                AbstractViewHolder<ItemEventListBinding>(parent, R.layout.item_event_list) {
            fun bind(event: Event) {
                binding.event = event
                binding.date.text = event.date.simpleFormat()
                val icon = when(event.eventType) {
                    EventType.LIVE -> R.drawable.live_black
                    EventType.BD -> R.drawable.bd_black
                    EventType.BROADCAST -> R.drawable.broadcast_black
                    EventType.OTHERS -> R.drawable.other_black
                }
                binding.name.setDrawables(context, start = icon)
                binding.root.click {
                    onClickItem(event)
                }
            }
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
