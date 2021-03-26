package com.guiabolso.exception.handler
import com.guiabolso.exception.InvalidMonthException
import com.guiabolso.exception.InvalidUserIdException
import com.guiabolso.exception.InvalidYearException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.MessageSource
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler


@ControllerAdvice
class ExceptionHandler : ResponseEntityExceptionHandler() {

    @Autowired
    private val messageSource: MessageSource? = null

    @ExceptionHandler(InvalidMonthException::class)
    fun handleInvalidMonthException(ex: InvalidMonthException, request: WebRequest?): ResponseEntity<Any> {
        val status = HttpStatus.BAD_REQUEST
        val errorMessage = ErrorMessage("O mês inserido é inválido - tem que estar entre 1 e 12")
        return handleExceptionInternal(ex, errorMessage,HttpHeaders(), status, request!!)
    }

    @ExceptionHandler(InvalidUserIdException::class)
    fun handleInvalidUserIdException(ex: InvalidUserIdException, request: WebRequest?): ResponseEntity<Any> {
        val status = HttpStatus.BAD_REQUEST
        val errorMessage = ErrorMessage("O userId inserido é inválido - tem que estar entre 1.000 e 100.000")
        return handleExceptionInternal(ex, errorMessage,HttpHeaders(), status, request!!)
    }

    @ExceptionHandler(InvalidYearException::class)
    fun handleInvalidYearException(ex: InvalidYearException, request: WebRequest?): ResponseEntity<Any> {
        val status = HttpStatus.BAD_REQUEST
        val errorMessage = ErrorMessage("O ano inserido é inválido - tem que ser positivo")
        return handleExceptionInternal(ex, errorMessage,HttpHeaders(), status, request!!)
    }

}