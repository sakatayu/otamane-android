package com.fefeyo.otamanekai.view.register


import android.os.Bundle
import android.view.*
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import com.fefeyo.otamanekai.MainActivity
import com.fefeyo.otamanekai.R
import com.fefeyo.otamanekai.databinding.FragmentChooseProductBinding


class ChooseProductFragment : Fragment() {

    lateinit var binding: FragmentChooseProductBinding
    private val viewModel by lazy {
        ViewModelProviders.of(this)[ChooseProductViewModel::class.java]
    }
    var columnNum = TWIN_COLUMN

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        (activity as? MainActivity)?.switchTitle(R.string.choose_product_label_title)
        setHasOptionsMenu(true)
        binding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_choose_product, container, false)
        binding.setLifecycleOwner(this)
        binding.recycler.layoutManager = GridLayoutManager(context, columnNum)
        binding.recycler.onItemClick = {
            Navigation.findNavController(binding.root)
                    .navigate(R.id.action_chooseProductFragment_to_eventRegisterFragment,
                            bundleOf(EventRegisterFragment.KEY_PRODUCT to it))
        }

        viewModel.load()
        viewModel.productList.observe(this, Observer {
            binding.recycler.products = it
        })

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.menu_choose_product, menu)
    }

    override fun onPrepareOptionsMenu(menu: Menu?) {
        menu?.findItem(R.id.line)?.title = when (columnNum) {
            TWIN_COLUMN -> getString(R.string.choose_product_label_triple)
            TRIPLE_COLUMN -> getString(R.string.choose_product_label_twin)
            else -> getString(R.string.choose_product_label_triple)
        }
    }


    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.line -> {
                // 3列表示
                columnNum = if (columnNum == TWIN_COLUMN) TRIPLE_COLUMN else TWIN_COLUMN
                binding.recycler.layoutManager = GridLayoutManager(context, columnNum)
            }
            R.id.favorite -> {
                // お気に入り表示
            }
            R.id.register_num -> {
                // 登録件数順
            }
            R.id.add -> {
                AddProductDialogFragment.newInstance()
                        .show(childFragmentManager, AddProductDialogFragment.TAG)
            }
        }
        activity?.invalidateOptionsMenu()

        return super.onOptionsItemSelected(item)
    }

    companion object {
        const val TWIN_COLUMN = 2
        const val TRIPLE_COLUMN = 3
    }
}
