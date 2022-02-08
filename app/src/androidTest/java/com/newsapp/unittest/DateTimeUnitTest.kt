package com.newsapp.unittest

import com.newsapp.util.DateTimeUtils.fromString
import com.newsapp.util.DateTimeUtils.toReadableString
import org.junit.Test

import org.junit.Assert.*
import java.util.*

/**
 * Unit test for DateTime Utils
 */
class DateTimeUnitTest {

    /**
     * NOT FINISHED
     *
     * check if we can have a correct Date from the API call
     * INPUT "2022-02-07T10:00:36Z"
     * EXPECTED
     * year = 2022
     * month = 2
     * day =7
     * hours= 10
     * minutes= 0
     * */
    @Test
    fun fromString_isCorrect() {
        assertEquals(4, 2 + 2)

        val dateStringFormat = "2022-02-07T10:00:36Z"

        val date = fromString(dateStringFormat)!!

        assertEquals(2022, date.year)
        assertEquals(2, date.month)
        assertEquals(7, date.day)
        assertEquals(10, date.hours)
    }
    /**
     * NOT FINISHED
     *
     * check if we can a correct Date from the API call
     * INPUT: Date(2022,2,7,10,0,0)
     * EXPECTED: dd MMM yyyy HH:mm
     * */
    @Test
    fun toReadableString_isCorrect() {
        val date = Date(2022,2,7,10,0,0)
        val result = toReadableString(date)
        assertEquals("", result)
    }
}