package com.linectf2024.g0tchag0tchadoggy.dto

import com.linectf2024.g0tchag0tchadoggy.model.GotchaEntity

fun GotchaEntity.toDto(): MessageDto {
    return MessageDto(
        id = id,
        uuid = uuid,
        result = result,
    )
}


