package com.linectf2024.g0tchag0tchadoggy.exception

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(Exception::class)
    fun handleException(e: Exception): ResponseEntity<ApiError> {
        val apiError = ApiError(
            code = "500",
            message = e.message ?: "Internal Server Error"
        )
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(apiError)
    }
}
data class ApiError(
    val code: String,
    val message: String
)