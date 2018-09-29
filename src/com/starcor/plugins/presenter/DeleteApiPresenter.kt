package com.starcor.plugins.presenter

import com.starcor.plugins.contract.DeleteApiWindowContract
import com.starcor.plugins.contract.MainWindowContract
import com.starcor.plugins.model.DataManager

/**
 * author：heng.zhang
 * date：2018/9/28
 * description：
 */
class DeleteApiPresenter(override var view: DeleteApiWindowContract.View) : DeleteApiWindowContract.Presenter {

    init {
        view.presenter = this
    }
    override fun start() {
        fillTemplateListData()
    }

    override fun fillTemplateListData() {
        val apiNameSet = DataManager.getApiNameSet()
        view.initRefreshData(apiNameSet)
    }

    override fun deleteApi(apiName: String) {
        DataManager.deletaApi(apiName)
    }
}