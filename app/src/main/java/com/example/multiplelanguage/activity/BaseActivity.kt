package com.example.multiplelanguage.activity

import android.annotation.SuppressLint
import android.content.Context
import android.view.MenuItem
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import com.example.multiplelanguage.R
import com.example.multiplelanguage.Utils.isNougat
import com.kimcy929.localeutils.ContextWrapper
import com.kimcy929.localeutils.LocaleUtils

@SuppressLint("Registered")
open class BaseActivity : AppCompatActivity() {

    init {
        if (!isNougat) {
            @Suppress("LeakingThis")
            LocaleUtils.updateConfig(this)
        }
    }

    override fun attachBaseContext(newBase: Context) {
        if (isNougat) {
            super.attachBaseContext(ContextWrapper.wrap(newBase, LocaleUtils.getsLocale()!!))
        } else {
            super.attachBaseContext(newBase)
        }
    }

    protected fun showBackButton() {
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }
    }

    protected fun setActivityTitle(@StringRes id: Int) {
        supportActionBar?.title = getString(id)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == android.R.id.home) {
            finish()
        }
        return true
    }
}