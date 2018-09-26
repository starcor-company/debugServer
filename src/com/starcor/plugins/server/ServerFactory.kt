package com.starcor.plugins.server

import com.starcor.plugins.utils.Utils
import java.util.HashMap

/**
 * author：MrLSM
 * date：2018/9/26
 * description：
 */
class ServerFactory private constructor() {

    private val mHttpServers: MutableMap<Int, IHttpServer>

    init {
        mHttpServers = HashMap<Int, IHttpServer>()
    }

    companion object {
        val instance = ServerFactory()
    }

    fun getHttpServer(hostName: String) : IHttpServer{
        return getHttpServer(hostName, DebugServer.PORT)
    }

    fun getHttpServer(hostName: String, type: Int): IHttpServer {
        var server: IHttpServer? = mHttpServers[Integer.valueOf(type)]
        if (server == null) {
            server = DebugServer(hostName, DebugServer.PORT)
            mHttpServers.put(Integer.valueOf(type), server)
        }
        return server
    }
}
