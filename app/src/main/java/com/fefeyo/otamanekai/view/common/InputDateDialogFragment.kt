package com.fefeyo.otamanekai.view.common

import android.app.DatePickerDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.widget.DatePicker
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
import org.threeten.bp.LocalDate
import java.util.*

class InputDateDialogFragment : DialogFragment(), DatePickerDialog.OnDateSetListener {

    var listener: OnSelectDateListener? = null
    val callbackTag by lazy {
        arguments?.get(KEY_CALLBACK_TAG) as String
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        listener = parentFragment as? OnSelectDateListener
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val calendar = Calendar.getInstance()
        val year = calendar[Calendar.YEAR]
        val month = calendar[Calendar.MONTH]
        val date = calendar[Calendar.DAY_OF_MONTH]

        return DatePickerDialog(activity!!, this, year, month, date)
    }

    override fun onDateSet(p0: DatePicker?, year: Int, month: Int, date: Int) {
        listener?.onSelectDate(callbackTag, LocalDate.of(year, month + 1, date))
    }

    interface OnSelectDateListener {
        fun onSelectDate(callbackTag: String, date: LocalDate)
    }

    companion object {
        const val TAG = "InputDateDialogFragment"
        const val KEY_CALLBACK_TAG = "key_callback_tag"
        fun newInstance(callbackTag: String) = InputDateDialogFragment().apply {
            arguments = bundleOf(KEY_CALLBACK_TAG to callbackTag)
        }
    }

}
