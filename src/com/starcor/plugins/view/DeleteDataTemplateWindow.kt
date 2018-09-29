package com.starcor.plugins.view

import com.starcor.plugins.contract.DeleteApiWindowContract
import com.starcor.plugins.contract.DeleteDataTemplateWindowContract
import com.starcor.plugins.model.DataManager
import com.starcor.plugins.presenter.DeleteApiPresenter
import com.starcor.plugins.presenter.DeleteTemplatePresenter
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
class DeleteDataTemplateWindow : DeleteDataTemplateWindowContract.View{
    override lateinit var presenter: DeleteDataTemplateWindowContract.Presenter
    private lateinit var jFrame: JFrame
    private lateinit var confirmButtonlsr : DataTemplateDeleteListener
    private lateinit var dataTemplateComboBox : JComboBox<String>
    private lateinit var dataTemplateModel: DefaultComboBoxModel<String>

    constructor() {
        initUI()
        DeleteTemplatePresenter(this)
    }

    override fun initRefreshData(templateNameSet : Set<String>) {
        for (templateName in templateNameSet) {
            dataTemplateModel.addElement(templateName)
        }
    }

    private fun initUI() {
        jFrame = JFrame("删除数据模版")
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
            presenter.deleteTemplate(dataTemplateComboBox.selectedItem.toString())
            confirmButtonlsr.onDataTemplateDeleted()
            jFrame.isVisible = false
        }
        buttonJPanel.add(confirmButton, JButton.CENTER)
        jFrame.add(buttonJPanel)
    }

    fun show() {
        jFrame.isVisible = true
        presenter.start()
    }

    fun addDeletedListener(lsr : DataTemplateDeleteListener) {
        confirmButtonlsr = lsr
    }

    interface DataTemplateDeleteListener {
        fun onDataTemplateDeleted()
    }
}