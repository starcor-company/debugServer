package com.starcor.plugins.view

import com.starcor.plugins.contract.DeleteApiWindowContract
import com.starcor.plugins.model.DataManager
import com.starcor.plugins.presenter.DeleteApiPresenter
import java.awt.Dimension
import java.awt.FlowLayout
import java.awt.Font
import java.awt.GridLayout
import javax.swing.*
import java.awt.event.ActionEvent
import java.awt.event.ActionListener
import javax.swing.JButton




/**
 * author：heng.zhang
 * date：2018/9/21
 * description：
 */
class DeleteApiWindow : DeleteApiWindowContract.View{
    override lateinit var presenter: DeleteApiWindowContract.Presenter
    private lateinit var jFrame: JFrame
    private lateinit var confirmButtonlsr : ApiDeleteListener
    private lateinit var dataTemplateComboBox : JComboBox<String>
    private lateinit var dataTemplateModel: DefaultComboBoxModel<String>

    constructor() {
        initUI()
        DeleteApiPresenter(this)
    }

    override fun initRefreshData(apiNameSet : Set<String>) {
        for (apiName in apiNameSet) {
            dataTemplateModel.addElement(apiName)
        }
    }

    private fun initUI() {
        jFrame = JFrame("删除接口")
        jFrame.setSize(400, 200)
        jFrame.setLocationRelativeTo(null)
        jFrame.defaultCloseOperation = JFrame.DISPOSE_ON_CLOSE
        jFrame.layout = GridLayout(2,1)

        val commonFont = Font("", 0, 22)

        val templateListJPanel = JPanel(FlowLayout())
        dataTemplateComboBox = JComboBox()
        dataTemplateModel = DefaultComboBoxModel()
        dataTemplateComboBox.model = dataTemplateModel
        dataTemplateComboBox.font = Font("", 0, 22)
        templateListJPanel.add(dataTemplateComboBox)
        jFrame.add(templateListJPanel, JPanel.CENTER_ALIGNMENT)

        val buttonJPanel = JPanel(FlowLayout())
        val confirmButton = JButton("confirm")
        confirmButton.font  = commonFont
        confirmButton.addActionListener {
            presenter.deleteApi(dataTemplateComboBox.selectedItem.toString())
            confirmButtonlsr.onApiDeleted()
            jFrame.isVisible = false
        }
        buttonJPanel.add(confirmButton, JButton.CENTER)
        jFrame.add(buttonJPanel)
    }

    fun show() {
        jFrame.isVisible = true
        presenter.start()
    }

    fun addDeletedListener(lsr : ApiDeleteListener) {
        confirmButtonlsr = lsr
    }

    interface ApiDeleteListener {
        fun onApiDeleted()
    }
}