package com.linectf2024.g0tchag0tchadoggy.config

import org.openjdk.nashorn.api.scripting.NashornScriptEngineFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import javax.script.ScriptEngine

@Configuration
class ScriptEngineConfiguration {
    @Bean
    fun scriptEngine(): ScriptEngine {
        return NashornScriptEngineFactory().scriptEngine
    }
}

