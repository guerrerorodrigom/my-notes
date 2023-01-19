package com.rodrigoguerrero.mynotes.utils

import kotlinx.datetime.Clock
import kotlinx.datetime.DatePeriod
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.TimeZone.Companion.currentSystemDefault
import kotlinx.datetime.minus
import kotlinx.datetime.toLocalDateTime
import kotlinx.datetime.todayIn

object DateUtils {

    fun isToday(date: String, timeZone: TimeZone = currentSystemDefault()): Boolean {
        return getDateDifferenceFromNow(date, timeZone).days == 0
    }

    fun isYesterday(date: String, timeZone: TimeZone = currentSystemDefault()): Boolean {
        return getDateDifferenceFromNow(date, timeZone).days == 1
    }

    fun isBeforeThisYear(date: String, timeZone: TimeZone = currentSystemDefault()): Boolean {
        return getDateDifferenceFromNow(date, timeZone).years > 0
    }

    fun formatTimeUnitWithTwoDigits(value: Int): String {
        return if (value.toString().length == 1) {
            "0$value"
        } else {
            "$value"
        }
    }

    private fun getDateDifferenceFromNow(
        date: String,
        timeZone: TimeZone
    ): DatePeriod {
        val nowDateTime = Instant.parse(date).toLocalDateTime(timeZone)
        return Clock.System.todayIn(currentSystemDefault()) - nowDateTime.date
    }
}
