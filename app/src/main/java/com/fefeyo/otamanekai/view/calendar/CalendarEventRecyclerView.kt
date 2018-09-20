package com.fefeyo.otamanekai.view.calendar

import android.content.Context
import android.util.AttributeSet
import androidx.paging.PagedList
import androidx.recyclerview.widget.RecyclerView
import com.fefeyo.otamanekai.data.model.Event

class CalendarEventRecyclerView @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyle: Int = 0
) : RecyclerView(context, attrs, defStyle) {

    private val adapter = CalendarEventPagedListAdapter()

    var events: PagedList<Event>?
        get() = adapter.currentList
        set(value) = adapter.submitList(value)

    init {
        setAdapter(adapter)
    }
}
