package com.weesftw.service

import com.weesftw.client.LLMClient
import com.weesftw.client.LLMClientFactory

internal class OpenIAService : LLMService {

    private val client: LLMClient<*> = LLMClientFactory.INSTANCE

    override fun question(message: String): String {
        val result = this.client.proceed(message)
        return result.content
    }
}