package com.example.multiplelanguage

import android.content.Context
import android.content.res.Resources
import android.os.Build
import android.preference.PreferenceManager
import androidx.core.os.ConfigurationCompat

object Utils {
    val isNougat: Boolean get() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.N

    fun Context.getDefaultLocale(): String? =
        PreferenceManager.getDefaultSharedPreferences(applicationContext)
        .getString("LANG", ConfigurationCompat.getLocales(Resources.getSystem().configuration)[0].toLanguageTag())

    val localeList = arrayOf("en", "vi-VN")
}