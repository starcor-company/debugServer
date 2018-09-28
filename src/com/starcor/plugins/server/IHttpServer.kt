package com.starcor.plugins.server

/**
 * author：MrLSM
 * date：2018/9/25
 * description：
 */
interface IHttpServer {

    fun startServer()

    fun stopServer()

    fun isHttpServerRunning() : Boolean

}