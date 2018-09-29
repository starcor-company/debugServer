package com.starcor.plugins.view

import com.starcor.plugins.model.DataManager
import java.awt.FlowLayout
import java.awt.Font
import java.awt.GridLayout
import javax.swing.*
import java.awt.event.ActionEvent
import javax.swing.JButton




/**
 * author：heng.zhang
 * date：2018/9/21
 * description：
 */
class AddApiWindow {
    private lateinit var jFrame: JFrame
    private lateinit var confirmButtonlsr : ActionListener
    constructor() {
        initUI()
    }

    private fun initUI() {
        jFrame = JFrame("添加接口")
        jFrame.setSize(400, 200)
        jFrame.setLocationRelativeTo(null)
        jFrame.defaultCloseOperation = JFrame.DISPOSE_ON_CLOSE
        jFrame.layout = GridLayout(2,1)

        val commonFont = Font("", 0, 22)

        val nameJPanel = JPanel(FlowLayout())
        val apiNameLabel = JLabel("api name:    ")
        apiNameLabel.font = commonFont
        val apiNameJTextField = JTextField(10)
        apiNameJTextField.font = commonFont
        nameJPanel.add(apiNameLabel)
        nameJPanel.add(apiNameJTextField)
        jFrame.add(nameJPanel, JPanel.CENTER_ALIGNMENT)

        val buttonJPanel = JPanel(FlowLayout())
        val confirmButton = JButton("confirm")
        confirmButton.font  = commonFont
        confirmButton.addActionListener {
            val apiName = apiNameJTextField.text
            DataManager.addApi(apiName)
            confirmButtonlsr.onConfirmButtonClick()
            jFrame.isVisible = false
        }
        buttonJPanel.add(confirmButton, JButton.CENTER)
        jFrame.add(buttonJPanel)
    }

    fun show() {
        jFrame.isVisible = true
    }

    fun addActionListener(lsr : ActionListener) {
        confirmButtonlsr = lsr
    }
    interface ActionListener {
        fun onConfirmButtonClick()
    }
}