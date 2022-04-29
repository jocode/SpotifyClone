package com.crexative.spotifyclone.core

import java.util.Calendar

object Utils {

    fun getGreeting(): String {
        val calendar: Calendar = Calendar.getInstance()
        val hourOfDay: Int = calendar.get(Calendar.HOUR_OF_DAY)

        return if (hourOfDay >= 12) {
            "Buenas tardes!"
        } else {
            "Buenos días!"
        }
    }
}