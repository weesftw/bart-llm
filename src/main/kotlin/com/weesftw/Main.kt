package com.weesftw

import com.weesftw.socket.ChatSocket
import io.javalin.Javalin


fun main() {
    Javalin.create { it ->
        it.router.mount {
            it.ws("/chat") { ws ->
                val socket = ChatSocket()
                ws.onConnect { ctx -> socket.connect(ctx) }
                ws.onClose { ctx -> socket.close(ctx) }
                ws.onMessage { ctx -> socket.message(ctx) }
            }
        }
    }.start(8888)
}