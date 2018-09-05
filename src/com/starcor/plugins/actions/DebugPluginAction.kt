package com.starcor.plugins.actions

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.starcor.plugins.ui.form.MainWindow

/**
 * Copyright (c) 2018, 北京视达科科技有限责任公司 All rights reserved.
 * author：MrLSM
 * date：2018/8/13
 * description：
 */
class DebugPluginAction : AnAction() {
    override fun actionPerformed(anActionEvent: AnActionEvent) {
        MainWindow()
    }
}
