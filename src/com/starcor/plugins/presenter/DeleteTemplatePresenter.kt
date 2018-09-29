package com.starcor.plugins.presenter

import com.starcor.plugins.contract.DeleteApiWindowContract
import com.starcor.plugins.contract.DeleteDataTemplateWindowContract
import com.starcor.plugins.model.DataManager

/**
 * author：heng.zhang
 * date：2018/9/28
 * description：
 */
class DeleteTemplatePresenter(override var view: DeleteDataTemplateWindowContract.View) : DeleteDataTemplateWindowContract.Presenter {

    init {
        view.presenter = this
    }
    override fun start() {
        fillTemplateListData()
    }

    override fun fillTemplateListData() {
        val templateNameSet = DataManager.currentApi.dataTemplates.keys
        view.initRefreshData(templateNameSet)
    }

    override fun deleteTemplate(templateName: String) {
        DataManager.deleteDataTemplate(templateName)
    }
}