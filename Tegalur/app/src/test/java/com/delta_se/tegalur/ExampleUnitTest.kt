package com.delta_se.tegalur

import org.junit.Test

import org.junit.Assert.*
import java.text.SimpleDateFormat

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }
    @Test
    fun testFormarter(){
//        val date = SimpleDateFormat("dd-MM-yyyy").parse("14-02-2018")
//        println(date.time)
//
//        val dateFormated = SimpleDateFormat("MMMM yyyy").format(date)
//        println(dateFormated)

        println()
        val date = "19/01/2021, 10:51 WIB"
        val clear = date.split("/", ",").toTypedArray()
        val dateFormat = SimpleDateFormat("dd-MM-yyyy").parse(clear[0] + "-" + clear[1] + "-" + clear[2])
        val dateFormated = SimpleDateFormat("dd MMMM yyyy").format(dateFormat)
        println(dateFormated)
    }

}