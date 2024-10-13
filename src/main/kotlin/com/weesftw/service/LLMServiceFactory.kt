package com.weesftw.service

import com.weesftw.MainConfig
import com.weesftw.model.LLModel

object LLMServiceFactory {

    val service: LLMService = when (MainConfig.llModel) {
        LLModel.OPENAI -> OpenIAService()
    }
}