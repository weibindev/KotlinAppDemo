package com.wbb.kotlinapp.mvp.presenter

import android.util.Log
import com.wbb.kotlinapp.api.RetrofitUtil
import com.wbb.kotlinapp.mvp.contract.CalentarContract
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

/**
 *
 * @author vico
 * @date 2018-08-17
 */
class CalentarPresenter(val view: CalentarContract.View) : CalentarContract.Presenter {

    var compositeDisposable:CompositeDisposable

    companion object {
        const val TAG = "CalentarPresenter"
    }

    init {
        view.presenter = this
        compositeDisposable= CompositeDisposable()
    }

    override fun subscribe() {

    }

    override fun unsubscribe() {
        compositeDisposable.clear()
    }

    override fun getDayCalentarData(date: String) {
        val disposable = RetrofitUtil
                .retrofitService
                .calenderDay(date, "933dc930886c8c0717607f9f8bae0b48")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ result ->
                    view.showDayCalentarData(result)
                    Log.e(TAG, result.toString())
                }, { error ->
                    view.showError(error.message.toString())
                    Log.e(TAG, error.message.toString())
                })
        compositeDisposable.add(disposable)
    }


}