package com.fefeyo.otamanekai

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.fefeyo.otamanekai.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setSupportActionBar(binding.toolbar)
    }

    fun switchTitle(titleRes: Int, subTitleRes: Int = NO_SUBTITLE) {
        supportActionBar?.apply {
            setTitle(titleRes)
            if (subTitleRes != NO_SUBTITLE) {
                setSubtitle(subTitleRes)
            } else {
                subtitle = null
            }
        }
    }

    companion object {
        const val NO_SUBTITLE = 0
    }

}
