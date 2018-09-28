package com.starcor.plugins.presenter

/**
 * author：heng.zhang
 * date：2018/7/24
 * description：
 */

interface BasePresenter<T> {
    var view : T

    fun start()
}
