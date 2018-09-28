package com.starcor.plugins.server

import com.starcor.plugins.model.Api
import com.starcor.plugins.model.DataManager
import com.starcor.plugins.utils.Utils
import fi.iki.elonen.NanoHTTPD
import java.io.IOException
import java.net.InetAddress


/**
 * author：MrLSM
 * date：2018/9/26
 * description：
 */

class DebugServer : NanoHTTPD, IHttpServer {

    private var mRun: Boolean = false
    private var mStartTime: String = ""
    private var mStopTime: String = ""

    companion object {
        var PORT: Int = 7777
        var HOST_NAME: String = InetAddress.getLocalHost().hostAddress
    }

    fun getHostAddress(): String {
        return "http://$HOST_NAME:$PORT"
    }

    constructor() : this(HOST_NAME, PORT)

    constructor(port: Int) : this(HOST_NAME, port){
        PORT = port
    }

    private constructor(hostName: String, port: Int) : super(hostName, port) {
        println("DebugServer: HOST_NAME = $hostName, PORT = $port")
        setAsyncRunner(NanoHTTPD.DefaultAsyncRunner())
        startServer()
    }

    override fun startServer() {
        if (!mRun) {
            mStartTime = Utils.getCurrentTime()
            try {
                start()
                mRun = true
            } catch (e: IOException) {
                e.printStackTrace()
            }

        }
    }

    override fun toString(): String {
        return "ThreadedServer [mRun=$mRun, startTime=$mStartTime, stopTime=$mStopTime, httpPort=$PORT]"
    }

    override fun stopServer() {
        mRun = false
        mStopTime = Utils.getCurrentTime()
        stop()
    }

    override fun isHttpServerRunning(): Boolean {
        return mRun
    }

    override fun serve(session: IHTTPSession?): Response {
        val msg = StringBuilder()
        msg.append("<?xml version=\"1.0\" encoding=\"utf-8\" ?>")
        val name: String = session?.queryParameterString?.substring(8) ?: ""
        val api : Api = DataManager.getApiDataByName(name)
        msg.append(api.currentDataTemplate)
//        msg.append("<epg><l><il></il></l><result><state>0</state><reason>OK</reason></result></epg>")
        return NanoHTTPD.newFixedLengthResponse(msg.toString())
    }
}