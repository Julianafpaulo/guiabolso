package com.guiabolso.controller

import com.guiabolso.domain.Transaction
import com.guiabolso.service.UserService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
class UserController(val userService: UserService) {

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}/transacoes/{ano}/{mes}", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getTransactionByPeriod(
        @PathVariable id: String,
        @PathVariable ano: Int,
        @PathVariable mes: Int
    ): ResponseEntity<MutableList<Transaction>> {
        return ResponseEntity.ok(userService.getTransactionByPeriod(id= id, ano= ano, mes= mes))
    }

}