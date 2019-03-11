package com.example.multiplelanguage

import android.app.Application
import com.example.multiplelanguage.Utils.getDefaultLocale
import com.example.multiplelanguage.Utils.isNougat
import com.example.multiplelanguage.Utils.localeList
import com.kimcy929.localeutils.LocaleUtils
import java.util.*

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        setLanguage()
    }

    private fun setLanguage() {
        val defaultLocale = getDefaultLocale()
        val locale = if (defaultLocale.isNullOrEmpty() || defaultLocale.isNullOrBlank()) {
            Locale("en")
        } else {
            if (defaultLocale in localeList) {
                val localeCode = defaultLocale.split("-")
                if (localeCode.size > 1) Locale(localeCode[0], localeCode[1]) else Locale(localeCode[0])
            } else {
                Locale("en")
            }
        }

        LocaleUtils.setLocale(locale)

        if (!isNougat) {
            LocaleUtils.updateConfig(this, baseContext.resources.configuration)
        }
    }
}