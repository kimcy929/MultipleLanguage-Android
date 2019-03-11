package com.kimcy929.localeutils

import android.os.Build

object BuildUtils {
    val isAtLeast24Api: Boolean get() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.N
}
