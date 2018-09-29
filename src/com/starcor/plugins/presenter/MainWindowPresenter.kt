package com.starcor.plugins.presenter

import com.starcor.plugins.contract.MainWindowContract
import com.starcor.plugins.model.DataManager

/**
 * author：heng.zhang
 * date：2018/9/28
 * description：
 */
class MainWindowPresenter(override var view: MainWindowContract.View) : MainWindowContract.Presenter {
    init {
        view.presenter = this
    }
    override fun start() {
        refreshApiList()
    }

    override fun refreshApiList() {
        val apiNameSet = DataManager.getApiNameSet()
        view.initRefreshData(apiNameSet)
    }
        override fun refreshTemplateList(apiName: String) {
        val api = DataManager.getApiDataByName(apiName)
        DataManager.currentApi = api
        view.refreshDataTemplateModel(api.dataTemplates.keys)
    }

    override fun refreshTemplateData(templateName: String) {
        val templateDataString = DataManager.currentApi.dataTemplates[templateName]
        if (templateDataString != null) {
            DataManager.setCurrentTemplate(templateDataString)
            view.fillTemplateDataString(templateDataString)
        }
    }

    override fun saveTemplateData(templateName : String, templateData : String) {
        DataManager.saveTemplateData(templateName, templateData)
    }
}