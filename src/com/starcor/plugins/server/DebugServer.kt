package com.starcor.plugins.server

import com.starcor.plugins.utils.Utils
import fi.iki.elonen.NanoHTTPD
import java.io.IOException


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
        var PORT: Int = 66668
        var HOST_NAME: String = "127.0.0.1"
    }

    constructor(hostName: String, port: Int) : super(hostName, port) {
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
        msg.append("<html><body><h1>Hello server</h1>\n")
        msg.append(session.toString())
        return NanoHTTPD.newFixedLengthResponse(msg.toString())
    }
}