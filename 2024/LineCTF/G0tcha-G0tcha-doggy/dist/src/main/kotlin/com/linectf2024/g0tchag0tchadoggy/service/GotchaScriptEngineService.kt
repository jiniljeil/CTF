package com.linectf2024.g0tchag0tchadoggy.service

import com.linectf2024.g0tchag0tchadoggy.exception.CustomException
import org.openjdk.nashorn.api.scripting.NashornException
import org.springframework.stereotype.Service
import java.security.SecureRandom
import javax.script.ScriptContext
import javax.script.ScriptEngine
import java.security.MessageDigest

@Service
class GotchaScriptEngineService(val scriptEngine: ScriptEngine) {

    var secureRandom = SecureRandom()

    fun runJS(input: String) {
        val context = scriptEngine.context
        val variableMap : MutableMap<String, Int> = HashMap()
        variableMap["start_no"] = 0
        variableMap["end_no"] = 20
        context.setAttribute("variables", variableMap, ScriptContext.ENGINE_SCOPE)

        try {
            scriptEngine.eval(input)
        } catch(e: NashornException) {
            throw CustomException("ScriptException while running js code $input")
        }
    }

    fun get(value: String): String{
        return scriptEngine.get(value).toString()
    }
    fun setSecureRandomSeed(userName:String) {
        val md5 = MessageDigest.getInstance("MD5")
        val hashedName = md5.digest(userName.toByteArray()).fold("") { str, byte -> str + "%02x".format(byte) }.filter { it.isDigit() }.takeLast(5).toLong()
        secureRandom.setSeed(((System.currentTimeMillis()+hashedName)/2+1))
        scriptEngine.put("secureRandom", secureRandom)
    }
}