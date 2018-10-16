package com.fefeyo.otamanekai.view.register

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.daimajia.androidanimations.library.Techniques
import com.daimajia.androidanimations.library.YoYo
import com.fefeyo.otamanekai.R
import com.fefeyo.otamanekai.data.model.Stuff
import com.fefeyo.otamanekai.databinding.CustomRegisterDialogBinding
import com.fefeyo.otamanekai.view.common.InputDateDialogFragment
import com.fefeyo.otamanekai.util.click
import com.fefeyo.otamanekai.util.dataenum.EventType
import com.fefeyo.otamanekai.util.dataenum.RepeatSpan
import com.fefeyo.otamanekai.util.dataenum.Time
import com.fefeyo.otamanekai.util.waitClicks
import com.fefeyo.otamanekai.view.common.InputStuffDialogFragment
import com.jzxiang.pickerview.TimePickerDialog
import com.jzxiang.pickerview.data.Type
import org.threeten.bp.LocalDate
import org.threeten.bp.ZoneOffset
import java.util.*

class EventRegisterDialogFragment : DialogFragment(), InputDateDialogFragment.OnSelectDateListener,
        InputStuffDialogFragment.OnInputStuffListener {

    private val viewModel by lazy {
        ViewModelProviders.of(this)[EventRegisterDialogViewModel::class.java]
    }
    private val sharedViewModel by lazy {
        ViewModelProviders.of(parentFragment!!)[EventRegisterViewModel::class.java]
    }

    private val eventType by lazy { arguments?.get(KEY_EVENT_TYPE) as EventType }
    private val productId by lazy { arguments?.get(KEY_PRODUCT_ID) as Long }

    lateinit var binding: CustomRegisterDialogBinding

    private var isAllDayInitFlag = false
    private var isRepeatEventInitFlag = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(DialogFragment.STYLE_NORMAL, R.style.FullScreenDialogStyle)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(
                inflater, R.layout.custom_register_dialog, null, false)
        binding.setLifecycleOwner(this)
        binding.viewmodel = viewModel
        initViewState()
        viewModel.also {
            it.setEventType(eventType)
            it.setProductId(productId)
        }

        val adapter = ArrayAdapter.createFromResource(context,
                R.array.repeat_span, R.layout.item_spinner_row)
        adapter.setDropDownViewResource(R.layout.item_dropdown_spinner_row)
        binding.repeatSpinner.adapter = adapter

        viewModel.isAllDay.observe(this, Observer {
            if (isAllDayInitFlag) {
                if (it) {
                    YoYo.with(Techniques.FadeOutDown)
                            .duration(300)
                            .onEnd { binding.timeInputContainer.isVisible = false }
                            .playOn(binding.timeInputContainer)
                } else {
                    YoYo.with(Techniques.FadeInUp)
                            .duration(300)
                            .onStart { binding.timeInputContainer.isVisible = true }
                            .playOn(binding.timeInputContainer)
                }
            } else {
                isAllDayInitFlag = true
            }
        })
        viewModel.isRepeatEvent.observe(this, Observer {
            if (isRepeatEventInitFlag) {
                if (it) {
                    YoYo.with(Techniques.FadeInUp)
                            .duration(300)
                            .onStart { binding.repeatSpinner.isVisible = true }
                            .playOn(binding.repeatSpinner)
                } else {
                    YoYo.with(Techniques.FadeOutDown)
                            .duration(300)
                            .onEnd { binding.repeatSpinner.isVisible = false }
                            .playOn(binding.repeatSpinner)
                }
            } else {
                isRepeatEventInitFlag = true
            }
        })

        binding.apply {
            dateInputLayout.click {
                InputDateDialogFragment.newInstance(CALLBACK_DATE).show(childFragmentManager, InputDateDialogFragment.TAG)
            }
            opentimeInputLayout.click {
                showTimePickerDialog(getString(R.string.event_register_dialog_label_opentime), CALLBACK_OPENTIME)
            }
            curtaintimeInputLayout.click {
                showTimePickerDialog(getString(R.string.event_register_dialog_label_curtaintime), CALLBACK_CURTAINTIME)
            }
            repeatSpinner.onItemSelectedListener = object: AdapterView.OnItemSelectedListener {
                override fun onNothingSelected(p0: AdapterView<*>?) {
                    viewModel.repeatSpan.postValue(RepeatSpan.WEEKLY)
                }

                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, position: Int, id: Long) {
                    viewModel.repeatSpan.postValue(RepeatSpan.values()[position])
                }

            }
            close.click {
                dismiss()
            }
            save.waitClicks {
                sharedViewModel.insertEvent(viewModel.generateEvent())
                dismiss()
            }
        }

        return binding.root
    }

    override fun onStart() {
        super.onStart()
        dialog.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
    }

    override fun onSelectDate(callbackTag: String, date: LocalDate) {
        when (callbackTag) {
            CALLBACK_DATE -> viewModel.setDate(date)
        }
    }

    override fun onInput(stuff: Stuff) {
        Log.d("onInput", stuff.toString())
    }

    private fun showTimePickerDialog(title: String, callbackTag: String) {
        val initTime = Calendar.getInstance().apply {
            set(Calendar.HOUR_OF_DAY, 12)
            set(Calendar.MINUTE, 0)
        }
        TimePickerDialog.Builder()
                .setTitleStringId(title)
                .setCancelStringId("取消")
                .setSureStringId("決定")
                .setHourText("時")
                .setMinuteText("分")
                .setType(Type.HOURS_MINS)
                .setWheelItemTextSize(18)
                .setThemeColor(ContextCompat.getColor(context!!, R.color.colorPrimary))
                .setCyclic(false)
                .setCurrentMillseconds(initTime.time.time)
                .setCallBack { _, millseconds ->
                    val date = Date(millseconds)
                    val cal = Calendar.getInstance()
                    cal.time = date
                    when (callbackTag) {
                        CALLBACK_OPENTIME -> viewModel.setOpenTime(Time(cal[Calendar.HOUR_OF_DAY], cal[Calendar.MINUTE]))
                        CALLBACK_CURTAINTIME -> viewModel.setCurtainTime(Time(cal[Calendar.HOUR_OF_DAY], cal[Calendar.MINUTE]))
                    }
                }
                .build()
                .show(childFragmentManager, TAG)
    }

    private fun initViewState() {
        when (eventType) {
            EventType.LIVE -> resetAllValues()
            EventType.BD -> {
                viewModel.isAllDay.postValue(true)
                binding.timeInputContainer.isVisible = false
                binding.isAllDay.isEnabled = false
                viewModel.isRepeatEvent.postValue(false)
                binding.repeatCheck.isVisible = false
                binding.repeatSpinner.isVisible = false
                binding.hasSale.isVisible = false
            }
            EventType.BROADCAST -> {
                binding.curtaintimeInputLayout.isVisible = false
                binding.isAllDay.isVisible = false
                binding.hasSale.isVisible = false
            }
            else -> resetAllValues()
        }
    }

    private fun resetAllValues() {
        viewModel.isAllDay.postValue(false)
        binding.timeInputContainer.isVisible = true
        binding.isAllDay.isEnabled = true
        binding.repeatCheck.isVisible = true
        binding.repeatSpinner.isVisible = false
        binding.hasSale.isVisible = true
    }

    companion object {
        const val KEY_EVENT_TYPE = "key_event_type"
        const val KEY_PRODUCT_ID = "key_product_id"
        const val TAG = "EventRegisterDialogFragment"
        const val CALLBACK_DATE = "callback_date"
        const val CALLBACK_OPENTIME = "callback_opentime"
        const val CALLBACK_CURTAINTIME = "callback_curtaintime"
        fun newInstance(productId: Long, eventType: EventType) = EventRegisterDialogFragment().apply {
            arguments = bundleOf(
                    KEY_PRODUCT_ID to productId,
                    KEY_EVENT_TYPE to eventType
            )
        }
    }
}
