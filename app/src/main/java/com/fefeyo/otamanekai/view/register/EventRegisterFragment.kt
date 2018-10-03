package com.fefeyo.otamanekai.view.register


import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.fefeyo.otamanekai.MainActivity
import com.fefeyo.otamanekai.R
import com.fefeyo.otamanekai.data.model.ProductWork
import com.fefeyo.otamanekai.databinding.FragmentEventRegisterBinding

class EventRegisterFragment : Fragment() {

    lateinit var binding: FragmentEventRegisterBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_event_register, container, false)
        setHasOptionsMenu(false)
        (activity as? MainActivity)?.switchTitle(R.string.event_register_label_title)
        val product = arguments?.get(KEY_PRODUCT) as? ProductWork
        product?.let {
            Log.d("登録する作品", "$it")
        }

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.menu_event_register, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId) {
            R.id.live -> {
                // ライブ・ファンミーティング登録
            }
            R.id.bd -> {
                // BD・DVD・CD発売日
            }
            R.id.broadcast -> {
                // 生放送・ラジオ放送日
            }
            R.id.other -> {
                // その他
            }
        }

        return super.onOptionsItemSelected(item)
    }

    companion object {
        const val KEY_PRODUCT = "product"
    }
}
