package com.example.demo.service

import org.springframework.stereotype.Service
import java.sql.Timestamp
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.YearMonth

@Service
class TransactionService {

    fun createTransactionDescription(id: Int, mes: Int, i: Int): String {

        val consonants = "bc dfg hjklmn pqrst vwxyz "
        val vogals = "aeiou"

        var string = ""
        for (y in 1..i+10) {
            val silable = consonants[(y + id - mes) % 26].toString() + vogals[(y + id - mes) % 5]

            string+=silable
            if(string.length == 60){
                return string
            }
        }
        return string
    }

    fun createTransactionValue(id: Int, mes: Int, i: Int): Int {
        return ((id * mes) % i+1) * 99
    }

    fun createTransactionData(ano: Int, mes: Int): Long {
       val date = date(ano,mes)

        val dateTime = LocalDateTime.of(date, LocalTime.now())
        val timestamp = Timestamp.valueOf(dateTime)

        return (timestamp.time.toString().toLong())
    }

    private fun date(ano: Int, mes: Int): LocalDate {
        val yearMonthObject = YearMonth.of(ano, mes)
        val daysInMonth = yearMonthObject.lengthOfMonth()
        val day = getRandomDay(1, daysInMonth)
        return LocalDate.of(ano, mes, day)
    }

    fun getRandomDay(min: Int, max: Int): Int {
        return (Math.random() * (max - min) + min).toInt()
    }
}