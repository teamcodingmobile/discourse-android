package io.keepcoding.discourse_android

import junit.framework.Assert.assertEquals
import org.junit.Test
import java.text.SimpleDateFormat
import java.util.*

class UtilsTest {
    val utils = Utils()

    @Test
    fun getOffset_isCorrect() {

        val itemDate: Date = formatDate("01/01/2019 10:00:00")
        val currentDate: Date = formatDate("01/01/2020 10:00:00")

        val offSet = utils.getTimeOffset(itemDate, currentDate)
        assertEquals("Amount comparision", 1, offSet.amount)
        assertEquals("Unit comparison", Calendar.YEAR, offSet.unit)
    }

    @Test
    fun getURL_isCorrect(){
        var string = "/user_avatar/mdiscourse.keepcoding.io/system/{size}/1_2.png"

        var url = utils.getURL(string)
        assertEquals("https://mdiscourse.keepcoding.io/user_avatar/mdiscourse.keepcoding.io/system/80/1_2.png", url)
    }

    @Test
    fun formatDate_isCorrect(){
        var date: String = "2019-12-12T01:41:28.809Z"
        var dateFormatted = utils.formatDate(date)

        assertEquals("Thu Dec 12 02:41:28 CET 2019", dateFormatted.toString())
    }

    private fun formatDate(date: String) : Date {
        val formatter = SimpleDateFormat("dd/MM/yyyy hh:mm:ss")
        return formatter.parse(date) ?: throw IllegalArgumentException("Date $date has an incorrect format, try again with format dd/MM/yyyy hh:mm:ss")
    }

}