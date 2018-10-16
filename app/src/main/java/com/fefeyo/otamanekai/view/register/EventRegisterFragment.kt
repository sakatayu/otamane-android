package com.fefeyo.otamanekai.view.register


import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.fefeyo.otamanekai.MainActivity
import com.fefeyo.otamanekai.R
import com.fefeyo.otamanekai.data.model.ProductWork
import com.fefeyo.otamanekai.databinding.FragmentEventRegisterBinding
import com.fefeyo.otamanekai.util.dataenum.EventType
import com.leinardi.android.speeddial.SpeedDialActionItem

class EventRegisterFragment : Fragment() {

    lateinit var binding: FragmentEventRegisterBinding
    val product by lazy {
        arguments?.get(KEY_PRODUCT) as ProductWork
    }
    private val viewModel by lazy {
        ViewModelProviders.of(this)[EventRegisterViewModel::class.java]
    }

    override fun onStart() {
        super.onStart()

        viewModel.eventList.observe(this, Observer {
            binding.eventList.events = it
        })

        viewModel.load(product.id)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_event_register, container, false)
        binding.setLifecycleOwner(this)
        binding.viewmodel = viewModel
//        TODO: 仕様決める
//        setHasOptionsMenu(true)
        (activity as? MainActivity)?.apply {
            switchTitle(getString(R.string.event_register_label_title, product.name))
            setUpNavigationBack(true)
        }
        setUpSpeedDial()

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.menu_event_register, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.submit -> {

            }
        }

        return super.onOptionsItemSelected(item)
    }

    private fun setUpSpeedDial() {
        binding.speedDial.apply {
            addActionItem(
                    generateSpeedDialActionItem(
                            R.id.fab_other,
                            R.drawable.other,
                            getString(R.string.event_register_label_other)
                    )
            )
            addActionItem(
                    generateSpeedDialActionItem(
                            R.id.fab_broadcast,
                            R.drawable.broadcast,
                            getString(R.string.event_register_label_broadcast)
                    )
            )
            addActionItem(
                    generateSpeedDialActionItem(
                            R.id.fab_bd,
                            R.drawable.bd,
                            getString(R.string.event_register_label_bd)
                    )
            )
            addActionItem(
                    generateSpeedDialActionItem(
                            R.id.fab_live,
                            R.drawable.live,
                            getString(R.string.event_register_label_live)
                    )
            )
        }.apply {
            setOnActionSelectedListener {
                val eventType = when (it.id) {
                    R.id.fab_live -> EventType.LIVE
                    R.id.fab_bd -> EventType.BD
                    R.id.fab_broadcast -> EventType.BROADCAST
                    R.id.fab_other -> EventType.OTHERS
                    else -> EventType.LIVE
                }
                EventRegisterDialogFragment.newInstance(product.id, eventType)
                        .show(childFragmentManager, EventRegisterDialogFragment.TAG)
                false
            }
        }
    }

    private fun generateSpeedDialActionItem(fabId: Int, fabIcon: Int, fabLabel: String) =
            SpeedDialActionItem.Builder(fabId, fabIcon)
                    .setLabel(fabLabel)
                    .setLabelClickable(true)
                    .create()

    companion object {
        const val KEY_PRODUCT = "product"
    }
}
