package com.starcor.plugins.ui

import java.awt.*
import javax.swing.*

/**
 * author：heng.zhang
 * date：2018/9/4
 * description：
 */
class MainWindow {
    private lateinit var jFrame : JFrame
    lateinit var apiItemSelectedLsr : ApiListItemSelectedListener
    lateinit var apiDataItemSelectedLsr : ApiDataListItemSelectedListener
    private lateinit var apiJList : JList<String>
    private lateinit var apiListModel: DefaultListModel<String>
    private lateinit var apiDataComboBox : JComboBox<String>
    private lateinit var apiDataModel: DefaultComboBoxModel<String>

    constructor() {
        initUI()
    }

    private fun initUI() {
        jFrame = JFrame("DebugServer")
        jFrame.setSize(1200,900)
        jFrame.setLocationRelativeTo(null)
        jFrame.defaultCloseOperation = WindowConstants.EXIT_ON_CLOSE
        jFrame.layout = GridBagLayout()

        //顶部菜单
        val menu = JMenuBar()
        menu.border = BorderFactory.createLoweredSoftBevelBorder()
        val addMenu = JMenu("新建")
        addMenu.font =  Font("", 1, 18)
        val deleteMenu = JMenu("删除")
        deleteMenu.font =  addMenu.font
        menu.add(addMenu)
        menu.add(deleteMenu)
        val addApiMenu  = JMenuItem("添加接口")
        addApiMenu.actionCommand = "add_api"
        val addApiDataMenu  = JMenuItem("添加数据模版")
        addApiDataMenu.actionCommand = "add_api_data_template"
        val deleteApiMenu  = JMenuItem("删除接口")
        deleteApiMenu.actionCommand = "delete_api"
        val deleteApiDataMenu  = JMenuItem("删除数据模版")
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
        apiDataComboBox  = JComboBox()
        apiDataModel = DefaultComboBoxModel()
        apiDataComboBox.model = apiDataModel
        apiDataComboBox.font = Font("", 0, 22)
        gbc.gridx = 0
        gbc.gridy = 0
        gbc.weightx = 1.0
        gbc.weighty = 0.01
        gbc.fill = GridBagConstraints.HORIZONTAL
        editPanel.add(apiDataComboBox, gbc)

        //保存按钮
        val saveButton = JButton("保存数据")
        saveButton.font = Font("", 0, 22)
        gbc.gridx = 1
        gbc.gridy = 0
        gbc.weightx = 0.1
        gbc.weighty = 0.01
        gbc.fill = GridBagConstraints.CENTER
        editPanel.add(saveButton, gbc)

        //数据编辑控件
        val textArea = JTextArea()
        textArea.font = Font("", 0, 20)
        gbc.gridx = 0
        gbc.gridy = 1
        gbc.gridwidth = 2
        gbc.weightx = 1.0
        gbc.weighty = 0.99
        gbc.fill = GridBagConstraints.BOTH
        val textAreaScrollPane = JScrollPane(textArea)
        editPanel.add(textAreaScrollPane, gbc)
    }

    fun refreshApiListModel(dataList : List<String>) {
        apiListModel.clear()
        for (data in dataList) {
            apiListModel.addElement(data)
        }
        if (apiListModel.size() > 0) {
            apiJList.selectedIndex = 0
        }
    }

    fun refreshApiDataModel(dataList : List<String>) {
        apiDataModel.removeAllElements()
        for (data in dataList) {
            apiDataModel.addElement(data)
        }
        if (apiDataModel.size > 0) {
            apiDataComboBox.selectedIndex = 0
        }
    }

    fun show() {
        jFrame.isVisible = true
    }

    interface ApiListItemSelectedListener {
        fun onItemSelected()
    }

    interface ApiDataListItemSelectedListener {
        fun onItemSelected()
    }
}