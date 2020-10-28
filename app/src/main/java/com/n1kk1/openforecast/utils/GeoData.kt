package com.n1kk1.openforecast.utils

import android.content.Context
import java.util.*

class GeoData(private val context: Context) {
    fun getLanguage(): String {
        return Locale.getDefault().language
    }
}