package com.linectf2024.g0tchag0tchadoggy.service

import com.linectf2024.g0tchag0tchadoggy.dto.ResultDto
import com.linectf2024.g0tchag0tchadoggy.exception.CustomException
import com.linectf2024.g0tchag0tchadoggy.model.GotchaEntity
import com.linectf2024.g0tchag0tchadoggy.repository.GotchaRepository
import kotlinx.serialization.json.Json
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.LocalTime
import java.util.*
import kotlin.Exception
import kotlin.concurrent.thread
import kotlin.text.StringBuilder


@Service
class GotchaService(private val gotchaRepository: GotchaRepository,
                  @Autowired private var scriptEngineService: GotchaScriptEngineService
) {
    fun saveGotcha(userName:String, userNumbers: List<Long>, dateTime: String) : GotchaEntity {

        var rouletteA: Thread?
        var rouletteB: Thread?
        var bonusNumber: Long
        var result = false
        try {

            synchronized(this){
                rouletteB = thread(false) {
                    val dangerCommands = listOf("java", "eval", "util", "for", "while", "function", "const", "=>" )
                    val isDanger = dangerCommands.any { dateTime.contains(it) }
                    if (isDanger) {
                        throw CustomException("No Hack")
                    }

                    val script = Script.Builder()
                    .script("for(var tempvariable=0;tempvariable<5;tempvariable++){ bonus_number=Math.floor(secureRandom.nextDouble()*value)+1;java.lang.Thread.sleep(2);}")
                    .value(dateTime)
                    .tempVariable( variableBuiler() )
                    .dynamicVariable(StringBuilder().append(variableBuiler()).append(System.currentTimeMillis()).toString())
                    .build()
                    scriptEngineService.setSecureRandomSeed(userName)
                    scriptEngineService.runJS(script.script.toString())
                }

                rouletteA = thread(false) {
                    val value = dateTime.replace(Regex("^(\\d{1,3}).*"), "$1")
                    val script = Script.Builder()
                        .script("var end_no=variables.get('end_no');var start_no=variables.get('start_no');var tmp=[];for(var tempvariable=start_no;tempvariable<end_no;tempvariable++){tmp.push(Math.floor(secureRandom.nextDouble()*value)+1);Java.type('java.lang.Thread').sleep(50);}var agent_a_array=JSON.stringify(tmp);")
                        .value(value)
                        .tempVariable( variableBuiler() )
                        .dynamicVariable(StringBuilder().append(variableBuiler()).append(System.currentTimeMillis()).toString())
                        .build()
                        scriptEngineService.setSecureRandomSeed(userName)
                        scriptEngineService.runJS(script.script.toString())

                }
            }
            rouletteB?.start()
            rouletteA?.start()
            while(rouletteB?.isAlive == true){
                Thread.sleep(100)
            }
            while(rouletteA?.isAlive == true){
                Thread.sleep(100)
            }

            bonusNumber = scriptEngineService.get("bonus_number").toDouble().toLong()
            var agentAScore: List<Long> =
                Json.decodeFromString<List<Long>>(Json.parseToJsonElement(scriptEngineService.get("agent_a_array")).toString())

            val userBonus = userNumbers.maxOf { it }
            val userNumberExceptLast = userNumbers.dropLast(1)


            if( userNumberExceptLast == agentAScore.sorted() && userBonus == bonusNumber) {
                result = true
            }

            var gotchaEntity = GotchaEntity(userName = userName, userNumbers = userNumbers.sorted(), gotchaNumbers = agentAScore.sorted(), bonusNumber = bonusNumber, result = result)
            return gotchaRepository.save(gotchaEntity)
        } catch (e: Exception){
            throw CustomException(e.toString())
        }

    }

    fun getGotcha(uuid: UUID) : ResultDto? {
        try{
            var image = "gotchafail.jpg"
            var resultMessage : String
            val result = gotchaRepository.findByUuid(uuid) ?: throw CustomException("Message with id $uuid not found")

            val userBonus = result.userNumbers.maxOf { it }
            val userNumberExceptLast = result.userNumbers.dropLast(1)

            if (userNumberExceptLast == result.gotchaNumbers && userBonus == result.bonusNumber)
            {
                val gotChaBaby : List<Long> = listOf(5,20)
                val gotChaHack : List<Long> = listOf(5,5,5)
                val gotChaPark : List<Long> = listOf(6,6,6)
                val gotChaKing : List<Long> = listOf(7,7,7)
                val gotChaTazza : List<Long> = listOf(8,8,8)
                val gotChaMaster : List<Long> = listOf(9,9,9)

                if( result.userNumbers == gotChaBaby){
                    resultMessage = "Gotcha baby!"
                    image = loadImage("flag.jpg")
                }else if( result.userNumbers == gotChaHack){
                    image = loadImage("flag.jpg")
                    resultMessage = "Gotcha hack"
                }else if( result.userNumbers == gotChaPark){
                    image = loadImage("flag.jpg")
                    resultMessage = "Gotcha Park!"
                }else if( result.userNumbers == gotChaKing){
                    image = loadImage("flag.jpg")
                    resultMessage = "Gotcha King"
                }else if( result.userNumbers == gotChaTazza){
                    image = loadImage("flag.jpg")
                    resultMessage = "Gotcha Tazza"
                }else if( result.userNumbers == gotChaMaster){
                    image = loadImage("flag.jpg")
                    resultMessage = "You are Master!!@#!@#!@#!@#!@#"
                }else {
                    resultMessage = "Luck is skill"
                }

            }else {
                resultMessage = "Time is running out. Do not DoS!"
            }
            return ResultDto(userName = result.userName, userNumbers = result.userNumbers, gotchaNumbers = result.gotchaNumbers, bonusNumber=result.bonusNumber, imageUrl = image, resultMessage = resultMessage )

        } catch (e: Exception){
            throw CustomException(e.toString())
        }
    }

    fun loadImage(imagePath: String): String {
        val inputStream = this::class.java.getResourceAsStream("/static/images/$imagePath")
        return StringBuilder().append("data:image/png;base64,")
            .append(Base64.getEncoder().encodeToString(inputStream?.readAllBytes() ?: ByteArray(0))).toString()
    }
}

data class DeleteMessage(
    val message: String = "All Message has been deleted",
    var status: Boolean = false
)

class Script(
    val script: String?,
    val value: String?,
) {
    data class Builder(
        var script: String? = null,
        var value: String? = null,
        var tempVariable: String? = null,
        var dynamicVariable: String? = null

        ) {
        fun script(script: String) = apply { this.script = script }
        fun value(value: String) = apply { this.value = value }
        fun tempVariable(tempVariable: String) = apply { this.tempVariable = tempVariable }
        fun dynamicVariable(dynamicVariable: String) = apply { this.dynamicVariable = dynamicVariable }
        fun build() = Script(script?.replace("value", value.toString())?.replace("tempvariable", tempVariable.toString())?.replace("dynamicvariable", dynamicVariable.toString()), value)
    }
}

fun variableBuiler() : String {
    val minute = LocalTime.parse(LocalTime.now().toString()).minute
    val tmpVal = minute % 12
    val tmpStr = minute % 26
    val alphabets = listOf("a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l","m","n","o","p","q","r","s","t","u","v","w","x","y","z" )
    return StringBuilder().append(alphabets.get(tmpStr))
        .append(tmpVal)
        .append(tmpStr)
        .toString()
}

