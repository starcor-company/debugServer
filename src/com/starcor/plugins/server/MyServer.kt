package com.starcor.plugins.server

import com.starcor.plugins.server.route.RouteManager
import fi.iki.elonen.NanoHTTPD

class MyServer(private val port: Int) : NanoHTTPD(port), ServerIface {

    var isRunning = true

    override fun deal_startServer() {
        if (isRunning) {
            start(NanoHTTPD.SOCKET_READ_TIMEOUT, false)
            println("Already Running! Point your browsers to http://localhost:$port/")
        } else {
            start(NanoHTTPD.SOCKET_READ_TIMEOUT, false)
            println("Start Running! Point your browsers to http://localhost:$port/")
        }
    }

    override fun deal_stopServer() {
        stop()
        println("Stop Running! Point your browsers to http://localhost:$port/")
    }

    override fun serve(session: IHTTPSession?): Response {
        return NanoHTTPD.newFixedLengthResponse(Response.Status.OK, MIMETYPE_XML, "")
    }
}

