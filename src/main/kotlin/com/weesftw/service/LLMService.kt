package com.weesftw.service

fun interface LLMService {

    fun question(message: String): String
}