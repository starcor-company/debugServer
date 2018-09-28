package com.starcor.plugins.view

import com.starcor.plugins.model.DataManager
import java.awt.FlowLayout
import java.awt.Font
import java.awt.GridLayout
import javax.swing.*
import java.awt.event.ActionListener
import java.awt.event.ActionEvent
import java.awt.event.WindowEvent
import java.awt.event.WindowListener
import javax.swing.JButton




/**
 * author：heng.zhang
 * date：2018/9/21
 * description：
 */
class AddApiWindow {
    private lateinit var jFrame: JFrame

    constructor() {
        initUI()
    }

    private fun initUI() {
        jFrame = JFrame("添加接口")
        jFrame.setSize(450, 250)
        jFrame.setLocationRelativeTo(null)
        jFrame.defaultCloseOperation = JFrame.DISPOSE_ON_CLOSE
        jFrame.layout = GridLayout(3,1)

        val commonFont = Font("", 0, 22)

        val nameJPanel = JPanel(FlowLayout())
        val apiNameLabel = JLabel("api name:    ")
        apiNameLabel.font = commonFont
        val apiNameJTextField = JTextField(10)
        apiNameJTextField.font = commonFont
        nameJPanel.add(apiNameLabel)
        nameJPanel.add(apiNameJTextField)
        jFrame.add(nameJPanel, JPanel.CENTER_ALIGNMENT)

        val functionJPanel = JPanel(FlowLayout())
        val apiFunctionLabel = JLabel("api function: ", JLabel.CENTER)
        apiFunctionLabel.font = commonFont
        val apiFuncJTextField = JTextField(10)
        apiFuncJTextField.font = commonFont
        functionJPanel.add(apiFunctionLabel)
        functionJPanel.add(apiFuncJTextField)
        jFrame.add(functionJPanel, JPanel.CENTER_ALIGNMENT)

        val buttonJPanel = JPanel(FlowLayout())
        val confirmButton = JButton("confirm")
        confirmButton.font  = commonFont
        confirmButton.addActionListener(object : ActionListener {
            override fun actionPerformed(e: ActionEvent) {
                val apiName = apiNameJTextField.text
                val apiFunction = apiFuncJTextField.text
                DataManager.addApi(apiName, apiFunction)
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