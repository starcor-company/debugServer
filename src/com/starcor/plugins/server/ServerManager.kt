package com.starcor.plugins.server

class ServerManager {

    companion object {
        private val server: ServerIface = MyServer(9090)

        fun start() {
            server.deal_startServer()
        }

        fun stop() {
            server.deal_stopServer()
        }
    }
}