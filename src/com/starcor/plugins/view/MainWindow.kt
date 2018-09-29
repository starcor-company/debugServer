package com.starcor.plugins.view

import com.starcor.plugins.contract.MainWindowContract
import com.starcor.plugins.presenter.MainWindowPresenter
import com.starcor.plugins.server.DebugServer
import java.awt.*
import java.awt.event.*
import javax.swing.*


/**
 * author：heng.zhang
 * date：2018/9/4
 * description：
 */
class MainWindow : MainWindowContract.View{

    override lateinit var presenter: MainWindowContract.Presenter

    private lateinit var jFrame : JFrame
    private lateinit var apiJList : JList<String>
    private lateinit var apiListModel: DefaultListModel<String>
    private lateinit var dataTemplateComboBox : JComboBox<String>
    private lateinit var dataTemplateModel: DefaultComboBoxModel<String>
    private lateinit var textArea : JTextArea
    private lateinit var saveButton : JButton

    constructor() {
        initUI()
        initUISelectedListener()
        MainWindowPresenter(this)
    }

    private fun initUI() {
        jFrame = JFrame("DebugServer")
        jFrame.setSize(1200,900)
        jFrame.setLocationRelativeTo(null)
        jFrame.defaultCloseOperation = WindowConstants.EXIT_ON_CLOSE
        jFrame.layout = GridBagLayout()
        jFrame.addWindowListener(object : WindowAdapter() {
            override fun windowClosing(e: WindowEvent?) {
                System.exit(0)
            }
        })

        //顶部菜单
        val menu = JMenuBar()
        menu.border = BorderFactory.createLoweredSoftBevelBorder()
        val addMenu = JMenu("新建")
        addMenu.font =  Font("", 1, 22)
        val deleteMenu = JMenu("删除")
        deleteMenu.font =  addMenu.font
        menu.add(addMenu)
        menu.add(deleteMenu)
        val addApiMenu  = JMenuItem("添加接口")
        addApiMenu.font = Font("", 1, 20)
        addApiMenu.addActionListener {
            val addApiWindow = AddApiWindow()
            addApiWindow.addActionListener(object : AddApiWindow.ActionListener {
                override fun onConfirmButtonClick() {
                    presenter.refreshApiList()
                }

            })
            addApiWindow.show() }
        val addApiDataMenu  = JMenuItem("添加数据模版")
        addApiDataMenu.font = addApiMenu.font
        addApiDataMenu.addActionListener {
            val window = AddDataTemplateWindow()
            window.addActionListener(object : AddDataTemplateWindow.ConfirmButtonListener {
                override fun onConfirmButtonClick() {
                    if (apiJList.selectedValue != null) {
                        presenter.refreshTemplateList(apiJList.selectedValue)
                    }
                }
            })
            window.show()
        }
        val deleteApiMenu  = JMenuItem("删除接口")
        deleteApiMenu.font = addApiMenu.font
        deleteApiMenu.actionCommand = "delete_api"
        val deleteApiDataMenu  = JMenuItem("删除数据模版")
        deleteApiDataMenu.font = addApiMenu.font
        deleteApiDataMenu.actionCommand = "delete_api_data_template"
        addMenu.add(addApiMenu)
        addMenu.add(addApiDataMenu)
        deleteMenu.add(deleteApiMenu)
        deleteMenu.add(deleteApiDataMenu)
        jFrame.jMenuBar = menu

        val gbc = GridBagConstraints()

        //接口列表容器
        gbc.gridx = 0
        gbc.gridy = 0
        gbc.weightx = 0.15
        gbc.weighty = 1.0
        gbc.fill = GridBagConstraints.BOTH
        val apiListPanel = JPanel()
        apiListPanel.layout = GridLayout(1,1)
        apiListPanel.background = Color.decode("#dddddd")
        apiListPanel.border = BorderFactory.createLoweredBevelBorder()
        jFrame.add(apiListPanel, gbc)

        //接口数据编辑容器
        gbc.gridx = 1
        gbc.gridy = 0
        gbc.weightx = 1.0
        gbc.weighty = 1.0
        gbc.fill = GridBagConstraints.BOTH
        val editPanel = JPanel()
        editPanel.layout = GridBagLayout()
        editPanel.background = Color.decode("#dddddd")
        editPanel.border = BorderFactory.createLoweredBevelBorder()
        jFrame.add(editPanel, gbc)

        //api列表的JList控件
        apiJList = JList()
        apiListModel = DefaultListModel()
        apiJList.model = apiListModel
        apiJList.background = Color.decode("#dddddd")
        apiJList.font = Font("", 0, 22)
        apiJList.selectionMode = ListSelectionModel.SINGLE_SELECTION
        val apiJListScrollPane = JScrollPane(apiJList)
        apiListPanel.add(apiJListScrollPane)

        //api数据列表下拉框
        dataTemplateComboBox  = JComboBox()
        dataTemplateModel = DefaultComboBoxModel()
        dataTemplateComboBox.model = dataTemplateModel
        dataTemplateComboBox.font = Font("", 0, 22)
        gbc.gridx = 0
        gbc.gridy = 0
        gbc.weightx = 1.0
        gbc.weighty = 0.01
        gbc.fill = GridBagConstraints.HORIZONTAL
        editPanel.add(dataTemplateComboBox, gbc)

        //保存按钮
        saveButton = JButton("保存数据")
        saveButton.font = Font("", 0, 22)
        gbc.gridx = 1
        gbc.gridy = 0
        gbc.weightx = 0.1
        gbc.weighty = 0.01
        gbc.fill = GridBagConstraints.CENTER
        editPanel.add(saveButton, gbc)

        //数据编辑控件
        textArea = JTextArea()
        textArea.font = Font("", 0, 20)
        gbc.gridx = 0
        gbc.gridy = 1
        gbc.gridwidth = 2
        gbc.weightx = 1.0
        gbc.weighty = 0.99
        gbc.fill = GridBagConstraints.BOTH
        val textAreaScrollPane = JScrollPane(textArea)
        editPanel.add(textAreaScrollPane, gbc)

        val addressText = JLabel()
        addressText.text = "request host address: " + DebugServer().getHostAddress()
        addressText.font = Font("", 0, 20)
        gbc.gridx = 0
        gbc.gridy = 2
        gbc.weightx = 1.0
        gbc.weighty = 0.01
        gbc.fill = GridBagConstraints.HORIZONTAL
        jFrame.add(addressText, gbc)
    }

    private fun initUISelectedListener() {
        apiJList.addListSelectionListener {
            textArea.text = ""
            if (apiJList.selectedValue != null) {
                presenter.refreshTemplateList(apiJList.selectedValue)
            }
        }
        dataTemplateComboBox.addItemListener { e ->
            if (e?.stateChange == ItemEvent.SELECTED) {
                presenter.refreshTemplateData(dataTemplateComboBox.selectedItem.toString())
            }
        }
        saveButton.addActionListener {
            presenter.saveTemplateData(dataTemplateComboBox.selectedItem.toString(),textArea.text) }
    }
    override fun initRefreshData(apiName: Set<String>) {
        refreshApiListModel(apiName)
    }

    private fun refreshApiListModel(dataSet : Set<String>) {
        apiListModel.clear()
        for (data in dataSet) {
            apiListModel.addElement(data)
        }
        if (apiListModel.size() > 0) {
            apiJList.selectedIndex = 0
        }
    }

    override fun refreshDataTemplateModel(templateSet : Set<String>) {
        dataTemplateModel.removeAllElements()
        for (data in templateSet) {
            dataTemplateModel.addElement(data)
        }
    }

    override fun fillTemplateDataString(templateDataString: String) {
        textArea.text = templateDataString
    }

    fun show() {
        jFrame.isVisible = true
        presenter.start()
    }
}