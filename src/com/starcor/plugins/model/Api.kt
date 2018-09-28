package com.starcor.plugins.model

import java.io.Serializable

/**
 * author：heng.zhang
 * date：2018/9/20
 * description：
 */
data class Api(val name : String, val dataTemplates : HashMap<String, String>, var currentDataTemplate : String) : Serializable