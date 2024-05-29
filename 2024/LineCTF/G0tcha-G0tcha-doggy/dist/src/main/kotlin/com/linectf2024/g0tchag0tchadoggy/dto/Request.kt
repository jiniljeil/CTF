package com.linectf2024.g0tchag0tchadoggy.dto

import org.springframework.web.util.HtmlUtils
import java.net.URLDecoder

data class GotchaRequest(
    val userName: String,
    val userNumbers: List<Any>,
    val dateTime: String
){
    fun getEscapedUserName(): String {
        val decodedMessage = urlDecoded(userName)
        val regex = Regex("[^A-Za-z0-9]")
        val nonAlphanumericChars = regex.findAll(userName)
            .map { it.value }
            .toList()
        if (nonAlphanumericChars.isNotEmpty()) {
            throw IllegalArgumentException("IllegalArgumentException : $nonAlphanumericChars")
        }
        if (decodedMessage != userName) return decodedMessage
        return HtmlUtils.htmlEscape(userName)
    }
    fun sanitizeUserNumbers(): List<Long> {
        return sanitize(userNumbers)
    }
    fun getEscapedDateTime(): String {
        val decodedMessage = urlDecoded(dateTime)
        if (decodedMessage != dateTime) return decodedMessage
        return HtmlUtils.htmlEscape(dateTime)
    }
    private fun urlDecoded(str: String): String {
        return  URLDecoder.decode(str, "UTF-8")
    }
}


fun sanitize(list: List<Any>): List<Long> {
    val sanitizedList = mutableListOf<Long>()
    for (item in list) {
        if (item is Int) {
            sanitizedList.add(item.toLong())
        }
    }
    return sanitizedList
}