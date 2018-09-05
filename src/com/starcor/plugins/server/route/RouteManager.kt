package com.starcor.plugins.server.route

import com.starcor.plugins.server.MyResponse
import com.starcor.plugins.server.MySession
import com.starcor.plugins.server.controller.BaseController
import com.starcor.plugins.server.controller.TestController

class RouteManager {

    companion object {
        init {
            add("/", TestController::class.java, "/index")
        }

        val routeMap = mutableMapOf<String, Class<out BaseController>>()

        fun route(session: MySession): MyResponse? {
            return null
        }

        private fun add(route: String, controller: Class<out BaseController>, subRoute: String = route) {
//            routeMap.put()
        }
    }
}

data class Route(val controller: Class<out BaseController>)

