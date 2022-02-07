package com.newsapp.util

import androidx.test.platform.app.InstrumentationRegistry
import java.util.*

object Util {
    fun getJson(fileName: Int): String {
        val inputStream = InstrumentationRegistry.getInstrumentation().targetContext.resources.openRawResource(fileName)
        val s = Scanner(inputStream).useDelimiter("\\A")
        return if (s.hasNext()) s.next() else ""
    }
}