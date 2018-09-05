package com.starcor.plugins.server


const val MIMETYPE_XML = "application/xml"
const val MIMETYPE_JSON = "application/json"

data class MySession(val uri: String, val params: MutableMap<String, MutableList<String>>, val headers: Map<String, String>, val body: String)

data class MyResponse(val code: ResponseCode, val mimeType: String, val body: String)

enum class ResponseCode(code: Int) {
    OK(200), NOT_FOUND(404), INTERNAL_ERROR(500)
}