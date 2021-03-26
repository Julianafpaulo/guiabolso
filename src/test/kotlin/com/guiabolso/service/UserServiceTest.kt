package com.guiabolso.service

import com.guiabolso.exception.InvalidMonthException
import com.guiabolso.exception.InvalidUserIdException
import com.guiabolso.exception.InvalidYearException
import org.junit.Test
import org.junit.jupiter.api.fail
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.ArgumentMatchers.eq
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class UserServiceTest {

     final val transactionService: TransactionService = mock(TransactionService::class.java)

     @Autowired
     val userService = UserService(transactionService)

    @Test
    fun `should generate correct number of transactions`(){
        `when`(transactionService.createTransactionData( eq(2020), eq(2))).thenReturn(1212)
        `when`(transactionService.createTransactionDescription(eq(1000),eq(2), anyInt())).thenReturn("description")
        `when`(transactionService.createTransactionValue(eq(1000),eq(2), anyInt())).thenReturn(100)

        val result =  userService.getTransactionByPeriod("1000",2020,2)

        assert(result.size == 2)
    }

    @Test
    fun `should throw invalidMonthException`(){
        try {
            userService.getTransactionByPeriod("1000", 2020, 13)
            fail("Mês tem que estar entre 1 e 12")
        }catch (e: InvalidMonthException){
            print(e)
        }
    }

    @Test
    fun `should throw invalidYearException`(){
        try {
            userService.getTransactionByPeriod("1000", -1, 12)
            fail("Ano tem ser maior que zero")
        }catch (e: InvalidYearException){
            print(e)
        }
    }

    @Test
    fun `should throw invalidUserIdException`(){
        try {
            userService.getTransactionByPeriod("100", 2020, 12)
            fail("UserId tem que estar entre 1.000 e 100.000")
        }catch (e: InvalidUserIdException){
            print(e)
        }
    }

}