package com.example.demo.controller

import com.example.demo.domain.Transaction
import com.example.demo.service.UserService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*

@RestController
class UserController(val userService: UserService) {

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}/transacoes/{ano}/{mes}", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getTransactionByPeriod(
            @PathVariable id: String,
            @PathVariable ano: Int,
            @PathVariable mes: Int
    ): ResponseEntity<MutableList<Transaction>> {
        return ResponseEntity.ok(userService.getTransactionByPeriod(id=id, ano=ano, mes=mes))
    }

}