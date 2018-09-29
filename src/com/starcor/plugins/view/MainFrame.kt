package com.starcor.plugins.view

import com.starcor.plugins.server.DebugServer
import java.awt.event.WindowEvent
import javax.swing.JFrame

/**
 * author：MrLSM
 * date：2018/9/29
 * description：
 */
class MainFrame : JFrame() {
    override fun processWindowEvent(e: WindowEvent?) {
        if (e?.id == WindowEvent.WINDOW_CLOSING) {
            this.isVisible = false
            DebugServer.getInstance()?.stopServer()
        }
    }
}