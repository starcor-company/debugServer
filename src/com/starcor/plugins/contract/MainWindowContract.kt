package com.starcor.plugins.contract

import com.starcor.plugins.presenter.BasePresenter
import com.starcor.plugins.view.BaseView

/**
 * author：heng.zhang
 * date：2018/9/28
 * description：
 */
class MainWindowContract {
    interface View : BaseView<Presenter> {
        fun initRefreshData(apiNameSet : Set<String>)
        fun refreshDataTemplateModel(apiNameSet : Set<String>)
        fun fillTemplateDataString(templateDataString : String)
    }

    interface Presenter : BasePresenter<View> {
        fun refreshApiList()
        fun refreshTemplateList(apiName : String)
        fun refreshTemplateData(templateName : String)
        fun saveTemplateData(templateName : String, templateData : String)
    }
}