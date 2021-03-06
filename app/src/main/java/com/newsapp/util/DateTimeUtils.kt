package com.newsapp.util

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

object DateTimeUtils{
    fun fromString(stringDate: String?): Date? {
        if(stringDate == null)
            return null
        var date: Date? = null
        val format = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault())
        format.timeZone = TimeZone.getTimeZone("Europe/Brussels")
        try {
            date = format.parse(stringDate)
        } catch (e: ParseException) {
            try {
                date = Date(java.lang.Long.valueOf(stringDate) * 1000)
            } catch (ex: Exception) {
                e.printStackTrace()
            }
        }
        return date
    }

    fun toReadableString(date: Date?): String {
        return try {
            val format = SimpleDateFormat("dd MMM yyyy HH:mm", Locale.getDefault())
            format.timeZone = TimeZone.getTimeZone("Europe/Brussels")
            format.format(date!!)
        } catch (e: ParseException) {
            ""
        }
    }
}