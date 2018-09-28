package com.starcor.plugins.ui

import java.awt.FlowLayout
import java.awt.Font
import java.awt.GridLayout
import javax.swing.*
import java.awt.event.ActionListener
import java.awt.event.ActionEvent
import javax.swing.JButton




/**
 * author：heng.zhang
 * date：2018/9/21
 * description：
 */
class AddDataTemplateWindow {
    private lateinit var jFrame: JFrame

    constructor() {
        initUI()
    }

    private fun initUI() {
        jFrame = JFrame("添加数据模版")
        jFrame.setSize(450, 250)
        jFrame.setLocationRelativeTo(null)
        jFrame.defaultCloseOperation = JFrame.DISPOSE_ON_CLOSE
        jFrame.layout = GridLayout(2,1)

        val commonFont = Font("", 0, 22)

        val nameJPanel = JPanel(FlowLayout())
        val nameLabel = JLabel("template name:    ")
        nameLabel.font = commonFont
        val apiNameJTextField = JTextField(10)
        apiNameJTextField.font = commonFont
        nameJPanel.add(nameLabel)
        nameJPanel.add(apiNameJTextField)
        jFrame.add(nameJPanel, JPanel.CENTER_ALIGNMENT)

        val buttonJPanel = JPanel(FlowLayout())
        val confirmButton = JButton("confirm")
        confirmButton.font  = commonFont
        confirmButton.addActionListener(object : ActionListener {
            override fun actionPerformed(e: ActionEvent) {
                val apiName = apiNameJTextField.text
                //TODO 将添加的数据模版存储
                jFrame.isVisible = false
            }
        })
        buttonJPanel.add(confirmButton, JButton.CENTER)
        jFrame.add(buttonJPanel)
    }

    fun show() {
        jFrame.isVisible = true
    }
}