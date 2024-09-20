package com.team1.phinconacademyassignment1

import java.time.LocalDate
import java.time.format.DateTimeFormatter

object Helper {
    val regexFormat = "^[0-9]+$".toRegex()

    fun LocalDate.dateConvertToString():String{
        val formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy")//format pattern untuk convert
        return this.format(formatter) //result format ke bentuk string
    }

}