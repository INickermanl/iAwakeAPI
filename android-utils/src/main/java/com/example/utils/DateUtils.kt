package com.example.utils

import com.example.utils.DateUtils.formatDate
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

const val DATE_VIEW_FORMAT = "dd.MM.yyyy"
const val FULL_DATE_VIEW_FORMAT = "dd MMM yyyy"

@Suppress("unused")
object DateUtils {
    fun formatDate(date: Date?, format: String = DATE_VIEW_FORMAT): String? {
        return if (date != null) {
            SimpleDateFormat(format, Locale.ENGLISH).format(date)
        } else null
    }

    fun parseDate(date: String, format: String = DATE_VIEW_FORMAT): Date {
        try {
            return SimpleDateFormat(format, Locale.ENGLISH).parse(date)
                ?: throw IllegalArgumentException("Wrong date format: $date")
        } catch (e: ParseException) {
            throw IllegalArgumentException("Wrong date format: $date")
        }

    }

    fun convertFromDefaultToFullFormat(date: String?): String? {
        if (date == null) return null
        val source = SimpleDateFormat(DATE_VIEW_FORMAT, Locale.getDefault()).parse(date) ?: return null
        return SimpleDateFormat(FULL_DATE_VIEW_FORMAT, Locale.getDefault()).format(source)
    }

    fun parseDateOrNull(date: String?, format: String = DATE_VIEW_FORMAT): Date? {
        return try {
            SimpleDateFormat(format, Locale.ENGLISH).parse(date ?: "")
        } catch (e: ParseException) {
            null
        }
    }

    fun addDays(date: Date, days: Int): Date {
        val cal = Calendar.getInstance()
        cal.time = date
        cal.add(Calendar.DATE, days) //minus number would decrement the days
        return cal.time
    }
}

fun Date.toCalendar(): Calendar {
    val cal = Calendar.getInstance()
    cal.time = this
    return cal
}

fun createCalendar(year: Int, month: Int, dayOfMonth: Int): Calendar {
    return Calendar.getInstance().apply {
        set(Calendar.YEAR, year)
        set(Calendar.MONTH, month)
        set(Calendar.DAY_OF_MONTH, dayOfMonth)
        set(Calendar.MILLISECOND, 0)
        set(Calendar.SECOND, 0)
        set(Calendar.MINUTE, 0)
        set(Calendar.HOUR, 0)
    }
}

fun Date.format(format: String = DATE_VIEW_FORMAT): String = formatDate(this, format) ?: ""