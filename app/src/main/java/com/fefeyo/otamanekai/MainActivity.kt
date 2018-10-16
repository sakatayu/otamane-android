package com.fefeyo.otamanekai

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.fefeyo.otamanekai.databinding.ActivityMainBinding
import com.fefeyo.otamanekai.util.load
import com.fefeyo.otamanekai.view.common.LoadingDialogFragment
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setSupportActionBar(binding.toolbar)
    }

    fun switchTitle(titleRes: Int, subTitleRes: Int? = null) {
        supportActionBar?.apply {
            setTitle(titleRes)
            subTitleRes?.let {
                setSubtitle(subTitleRes)
            } ?: run {
                subtitle = null
            }
        }
    }

    fun switchTitle(title: String, subtitle: String? = null) {
        supportActionBar?.apply {
            setTitle(title)
            subtitle?.let {
                this.subtitle = subtitle
            }
        }
    }

    fun setUpNavigationBack(isShow: Boolean) {
        if (isShow) {
            binding.toolbar.apply {
                setNavigationIcon(R.drawable.ic_arrow_back_white_24dp)
                setNavigationOnClickListener {
                    onBackPressed()
                }
            }
        } else {
            binding.toolbar.navigationIcon = null
        }
    }

}
