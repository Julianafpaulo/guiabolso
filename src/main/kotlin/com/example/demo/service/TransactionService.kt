package com.example.demo.service

import org.springframework.stereotype.Service
import java.sql.Timestamp
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.YearMonth
import java.util.concurrent.ThreadLocalRandom

@Service
class TransactionService {

    fun createTransactionDescription(id: Int, mes: Int, i: Int): String {

        return buildString {
            append("Numbers: ")
            for (i in 1..3) {
                // 'this' can be omitted

                append(i)
            }
        }
    }

    fun createTransactionValue(id: Int, mes: Int, i: Int): Int {
        return (id * mes) % i
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