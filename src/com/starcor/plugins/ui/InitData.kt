package com.starcor.plugins.ui

import java.awt.*

/**
 * author：MrLSM
 * date：2018/5/4
 * description：
 */
object InitData {
    //展示比例
    private var SHOW_SCALE = 0.8

    var MAIN_WINDOW_WIDTH: Int = 0
    var MAIN_WINDOW_HEIGHT: Int = 0
    var mainWindowPoint: Point? = null

    var CONNECT_WINDOW_WIDTH: Int = 0
    var CONNECT_WINDOW_HEIGHT: Int = 0
    var connectWindowPoint: Point? = null

    fun initMainWindowSize() {
        val scrSize = Toolkit.getDefaultToolkit().screenSize
        MAIN_WINDOW_WIDTH = (scrSize.getWidth() * SHOW_SCALE).toInt()
        MAIN_WINDOW_HEIGHT = (scrSize.getHeight() * SHOW_SCALE).toInt()
        val x = (scrSize.getWidth() * (1 - SHOW_SCALE) / 2).toInt()
        val y = (scrSize.getHeight() * (1 - SHOW_SCALE) / 2).toInt()
        mainWindowPoint = Point(x, y)
    }

    fun initConnectWindowSize() {
        val scrSize = Toolkit.getDefaultToolkit().screenSize
        CONNECT_WINDOW_WIDTH = (scrSize.getWidth() * 0.22).toInt()
        CONNECT_WINDOW_HEIGHT = (scrSize.getHeight() * 0.2).toInt()
        val x = (scrSize.getWidth() * (1 - 0.3) / 2).toInt()
        val y = (scrSize.getHeight() * (1 - 0.2) / 2).toInt()
        connectWindowPoint = Point(x, y)
    }

    fun setShowScale(scale: Double) {
        if (scale > 0 && scale <= 1) {
            SHOW_SCALE = scale
        }
    }

    fun initConnectInfo() {

    }
}
