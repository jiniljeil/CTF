package com.linectf2024.g0tchag0tchadoggy.controller

import com.linectf2024.g0tchag0tchadoggy.*
import com.linectf2024.g0tchag0tchadoggy.dto.*
import com.linectf2024.g0tchag0tchadoggy.exception.CustomException
import com.linectf2024.g0tchag0tchadoggy.service.GotchaService
import com.linectf2024.g0tchag0tchadoggy.util.GotchaValidator
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.validation.BindingResult
import org.springframework.web.bind.WebDataBinder
import java.util.*


@RestController
@RequestMapping("/api/gotcha")
class GotchaController(
    private val gotchaService: GotchaService,
    private val gotchaValidator: GotchaValidator,
) {
    @InitBinder
    fun initBinder(binder: WebDataBinder){
        binder.addValidators(gotchaValidator)
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun saveMessage(
        @Valid @RequestBody request: GotchaRequest,
        bindingResult: BindingResult,
    ): ResponseEntity<Any> {
        try {
            if (bindingResult.hasErrors()) {
                val errors = bindingResult.fieldErrors.associateBy({ it.field }, { it.defaultMessage })
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors)
            }
            return ResponseEntity.status(HttpStatus.CREATED).body(
                ResultResponseDto(
                    gotchaService.saveGotcha(request.getEscapedUserName(), request.sanitizeUserNumbers(), request.getEscapedDateTime()).toDto()
                )
            )
        } catch (e: Exception){
            throw CustomException(e.toString())
        }
    }

    @GetMapping("/{uuid}")
    fun getMessage(@PathVariable uuid: UUID): ResponseEntity<ResultDto> {
        try{
            return ResponseEntity.ok(gotchaService.getGotcha(uuid))
        } catch (e: Exception){
            throw CustomException("Something wrong!")
        }

    }


}

