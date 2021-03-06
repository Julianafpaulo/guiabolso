package com.guiabolso.service

import com.guiabolso.domain.Transaction
import com.guiabolso.exception.InvalidMonthException
import com.guiabolso.exception.InvalidUserIdException
import com.guiabolso.exception.InvalidYearException
import org.springframework.stereotype.Service

@Service
class UserService (val transactionService: TransactionService) {

    fun getTransactionByPeriod(id: String, ano: Int, mes: Int): MutableList<Transaction> {
        validateParams(id= id.toInt(), ano= ano , mes= mes)
        
        return createTransactionList(id= id, ano= ano, mes= mes)
    }

    private fun createTransactionList(id: String, ano: Int, mes: Int): MutableList<Transaction> {
        val numberOfTransactions = id[0].toString().toInt() * mes
        val transactions = mutableListOf<Transaction>()

        for(i in 1 .. numberOfTransactions){
            val transaction = Transaction(
                data = transactionService.createTransactionData(ano,mes),
                valor = transactionService.createTransactionValue(id.toInt(), mes, i),
                descricao = transactionService.createTransactionDescription(id.toInt(), mes, i)
            )

            transactions.add(i-1,transaction)
        }

        return transactions
    }

    private fun validateParams(id: Int, ano: Int, mes: Int) {
        if(id !in 1000..100000) {
            throw InvalidUserIdException()
        }
        if(mes !in 1 .. 12){
            throw InvalidMonthException()
        }
        if(ano < 0){
            throw InvalidYearException()
        }
    }
}