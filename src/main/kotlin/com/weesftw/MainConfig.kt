package com.weesftw

import com.weesftw.model.LLModel
import java.net.URI
import java.util.*

data object MainConfig {

    val llmUrl: URI = URI.create(System.getenv("LLM_URL"))
    val llmApiKey: String = System.getenv("LLM_API_KEY")
    val llModel: LLModel = System.getenv("LLM_TYPE")?.let { LLModel.valueOf(it.uppercase(Locale.getDefault())) } ?: throw IllegalArgumentException("`LLM_TYPE `not found")
}