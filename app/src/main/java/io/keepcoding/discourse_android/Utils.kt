package io.keepcoding.discourse_android

import io.keepcoding.discourse_android.Data.Models.AppModels.TimeOffset
import java.util.*

class Utils {

    val MINUTE_MILLIS = 1000L * 60
    val HOUR_MILLIS = MINUTE_MILLIS * 60
    val DAY_MILLIS = HOUR_MILLIS * 24
    val MONTH_MILLIS = DAY_MILLIS * 30
    val YEAR_MILLIS = MONTH_MILLIS * 12

    fun getTimeOffset(itemDate: Date, currentDate: Date = Date(), ) : TimeOffset {
        val dateToCompare = currentDate.time
        val current = itemDate.time
        val diff = dateToCompare - current

        val years = diff / YEAR_MILLIS
        if (years > 0) return TimeOffset(
                years.toInt(),
                Calendar.YEAR
        )

        val months = diff / MONTH_MILLIS
        if (months > 0) return TimeOffset(
                months.toInt(),
                Calendar.MONTH
        )

        val days = diff / DAY_MILLIS
        if (days > 0) return TimeOffset(
                days.toInt(),
                Calendar.DAY_OF_MONTH
        )

        val hours = diff / HOUR_MILLIS
        if (hours > 0) return TimeOffset(
                hours.toInt(),
                Calendar.HOUR
        )

        val minutes = diff / MINUTE_MILLIS
        if (minutes > 0) return TimeOffset(
                minutes.toInt(),
                Calendar.MINUTE
        )

        return TimeOffset(
                0,
                Calendar.MINUTE
        )
    }
}