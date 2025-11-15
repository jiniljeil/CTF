package com.linectf2024.g0tchag0tchadoggy.dto

import org.springframework.web.server.ResponseStatusException
import java.util.*

class ApiException(code: Int, message: String) : ResponseStatusException(code, message, null)

data class ResultResponseDto(
    val result: MessageDto,
)

data class MessageDto(
    val id: Long,
    val uuid: UUID,
    val result: Boolean
)

data class ResultDto(
    val userName: String,
    val userNumbers: List<Long>?,
    val gotchaNumbers: List<Long>?,
    val bonusNumber: Long,
    val imageUrl: String,
    val resultMessage: String,
)