package com.starcor.plugins.contract

import com.starcor.plugins.presenter.BasePresenter
import com.starcor.plugins.view.BaseView

/**
 * author：heng.zhang
 * date：2018/9/28
 * description：
 */
class DeleteDataTemplateWindowContract {
    interface View : BaseView<Presenter> {
        fun initRefreshData(templateNameSet : Set<String>)
    }

    interface Presenter : BasePresenter<View> {
        fun fillTemplateListData()
        fun deleteTemplate(templateName : String)
    }
}