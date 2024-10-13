package com.weesftw.socket

import com.weesftw.service.LLMServiceFactory
import io.javalin.websocket.WsCloseContext
import io.javalin.websocket.WsConnectContext
import io.javalin.websocket.WsMessageContext
import org.slf4j.LoggerFactory

class ChatSocket {

    companion object {
        private val log = LoggerFactory.getLogger(ChatSocket.toString())
    }

    fun connect(ctx: WsConnectContext) {
        log.info("Connected: ${ctx.session}")
    }

    fun close(ctx: WsCloseContext) {
        log.info("Closed: ${ctx.session}")
    }

    fun message(ctx: WsMessageContext) {
        log.info("Message: ${ctx.message()}")
        try {
            ctx.send(LLMServiceFactory.service.question(ctx.message()))
        } catch (e: Exception) {
            log.error("Error: ${e.message}")
        }
    }
}