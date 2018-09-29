package com.starcor.plugins.view

import com.starcor.plugins.model.DataManager
import java.awt.FlowLayout
import java.awt.Font
import java.awt.GridLayout
import javax.swing.*
import javax.swing.JButton




/**
 * author：heng.zhang
 * date：2018/9/21
 * description：
 */
class AddDataTemplateWindow {
    private lateinit var jFrame: JFrame
    private lateinit var confirmButtonlsr : ConfirmButtonListener

    constructor() {
        initUI()
    }

    private fun initUI() {
        jFrame = JFrame("添加数据模版")
        jFrame.setSize(400, 200)
        jFrame.setLocationRelativeTo(null)
        jFrame.defaultCloseOperation = JFrame.DISPOSE_ON_CLOSE
        jFrame.layout = GridLayout(2,1)

        val commonFont = Font("", 0, 22)

        val nameJPanel = JPanel(FlowLayout())
        val nameLabel = JLabel("template name:    ")
        nameLabel.font = commonFont
        val templateNameJTextField = JTextField(10)
        templateNameJTextField.font = commonFont
        nameJPanel.add(nameLabel)
        nameJPanel.add(templateNameJTextField)
        jFrame.add(nameJPanel, JPanel.CENTER_ALIGNMENT)

        val buttonJPanel = JPanel(FlowLayout())
        val confirmButton = JButton("confirm")
        confirmButton.font  = commonFont
        confirmButton.addActionListener {
            val templateName = templateNameJTextField.text
            DataManager.addDataTemplate(templateName)
            confirmButtonlsr.onConfirmButtonClick()
            jFrame.isVisible = false
        }
        buttonJPanel.add(confirmButton, JButton.CENTER)
        jFrame.add(buttonJPanel)
    }

    fun show() {
        jFrame.isVisible = true
    }
    fun addActionListener(lsr : ConfirmButtonListener) {
        confirmButtonlsr = lsr
    }
    interface ConfirmButtonListener {
        fun onConfirmButtonClick()
    }
}