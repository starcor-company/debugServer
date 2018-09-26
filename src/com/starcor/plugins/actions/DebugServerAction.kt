package com.starcor.plugins.actions

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.starcor.plugins.server.DebugServer
import com.starcor.plugins.server.ServerFactory
import com.starcor.plugins.ui.MainWindow

/**
 * author：MrLSM
 * date：2018/9/26
 * description：
 */
class DebugServerAction : AnAction() {

    override fun actionPerformed(e: AnActionEvent) {
        MainWindow().show()
        ServerFactory.instance.getHttpServer(DebugServer.HOST_NAME).startServer()
    }
}
