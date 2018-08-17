package com.wbb.kotlinapp.mvp.contract

import com.wbb.kotlinapp.base.BasePresenter
import com.wbb.kotlinapp.base.BaseView
import com.wbb.kotlinapp.bean.CalentarDayBean
import com.wbb.kotlinapp.bean.CalentarDayData
import com.wbb.kotlinapp.bean.CalentarDayResult

/**
 *
 * @author vico
 * @date 2018-08-17
 */
interface CalentarContract {

    interface View : BaseView<Presenter> {
        fun showDayCalentarData(calentarDayBean: CalentarDayBean<CalentarDayResult<CalentarDayData>>)
        fun showError(errorMsg: String)
    }

    interface Presenter : BasePresenter {
        fun getDayCalentarData(date: String)
    }
}