package com.starcor.plugins.presenter

import com.starcor.plugins.contract.DeleteApiWindowContract
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
        fillApiListData()
    }

    override fun fillApiListData() {
        val apiNameSet = DataManager.getApiNameSet()
        view.initRefreshData(apiNameSet)
    }

    override fun deleteApi(apiName: String) {
        DataManager.deleteApi(apiName)
    }
}