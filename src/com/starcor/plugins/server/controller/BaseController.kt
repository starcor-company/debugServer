package com.starcor.plugins.server.controller

import com.starcor.plugins.server.MyResponse
import com.starcor.plugins.server.MySession

abstract class BaseController {

    fun filter(session: MySession): Pair<Boolean, String> {
        return Pair(true, "")
    }

    abstract fun deal(session: MySession): MyResponse
}