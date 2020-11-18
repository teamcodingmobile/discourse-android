package io.keepcoding.discourse_android

import android.content.Context
import io.keepcoding.discourse_android.Data.Models.AppModels.TimeOffset
import java.text.SimpleDateFormat
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

    fun getURL(forString: String?) : String {
        val sized = forString?.replace(oldValue = "{size}", newValue = "80")
        return "https://mdiscourse.keepcoding.io$sized"
    }

    fun formatDate(date: String?) : Date {
        val dateReplaced = date?.replace("Z", "+0000") ?: ""
        val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ", Locale.getDefault())
        val dateFormatted = dateFormat.parse(dateReplaced) ?: Date()
        return dateFormatted
    }

    fun setTimeOffset(timeOffset: TimeOffset, context: Context) : String {

        val quantityString = when (timeOffset.unit) {
            Calendar.YEAR -> R.plurals.years
            Calendar.MONTH -> R.plurals.months
            Calendar.DAY_OF_MONTH -> R.plurals.days
            Calendar.HOUR -> R.plurals.hours
            else -> R.plurals.minutes
        }

        if (timeOffset.amount == 0) {
            return R.string.minutes_zero.toString()
        } else {
            return context.resources.getQuantityString(
                    quantityString,
                    timeOffset.amount,
                    timeOffset.amount
            )
        }
    }
}