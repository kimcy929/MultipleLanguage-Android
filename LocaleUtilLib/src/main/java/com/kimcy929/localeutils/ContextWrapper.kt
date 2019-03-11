package com.kimcy929.localeutils

import android.content.Context
import android.os.LocaleList

import java.util.Locale

import timber.log.Timber

class ContextWrapper(base: Context) : android.content.ContextWrapper(base) {
    companion object {

        fun wrap(context: Context, newLocale: Locale): ContextWrapper {
            var context1 = context

            val res = context1.resources
            val configuration = res.configuration

            if (BuildUtils.isAtLeast24Api) {
                configuration.setLocale(newLocale)
                try {
                    val localeList = LocaleList(newLocale)
                    LocaleList.setDefault(localeList)
                    configuration.locales = localeList
                    context1 = context1.createConfigurationContext(configuration)
                } catch (e: NullPointerException) {
                    Timber.e("Error config location -> %s", e.message)
                } catch (e: IllegalArgumentException) {
                    Timber.e("Error config location -> %s", e.message)
                }

            } else {
                configuration.setLocale(newLocale)
                context1 = context1.createConfigurationContext(configuration)
            }

            return ContextWrapper(context1)
        }
    }
}
