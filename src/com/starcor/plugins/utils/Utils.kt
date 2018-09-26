package com.starcor.plugins.utils

import java.text.SimpleDateFormat
import java.util.*

/**
 * author：MrLSM
 * date：2018/9/26
 * description：
 */
object Utils {
    private val dateFormat = SimpleDateFormat("yyyy_MM_dd:HH_mm_ss")

    fun getCurrentTime(): String {
        return dateFormat.format(Date())
    }

    fun formatDate(t: Date): String {
        return dateFormat.format(t)
    }
}