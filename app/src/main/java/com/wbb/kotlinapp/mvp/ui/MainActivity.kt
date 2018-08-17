package com.wbb.kotlinapp.mvp.ui

import android.os.Build
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.wbb.kotlinapp.R
import com.wbb.kotlinapp.bean.CalentarDayBean
import com.wbb.kotlinapp.bean.CalentarDayData
import com.wbb.kotlinapp.bean.CalentarDayResult
import com.wbb.kotlinapp.mvp.contract.CalentarContract
import com.wbb.kotlinapp.mvp.presenter.CalentarPresenter
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.toast

class MainActivity : AppCompatActivity(), CalentarContract.View {

    override lateinit var presenter: CalentarContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        presenter = CalentarPresenter(this)

        selectButton.setOnClickListener {
            titleTextView.visibility = View.GONE
            selectButton.visibility = View.GONE
            contentTextView.visibility = View.GONE
            datePicker.visibility = View.VISIBLE
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            datePicker.setOnDateChangedListener { view, year, month, day ->
                var date: String = "${year}-${month+1}-${day}"
                presenter.getDayCalentarData(date)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        presenter.subscribe()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.unsubscribe()
    }

    override fun showDayCalentarData(calentarDayBean: CalentarDayBean<CalentarDayResult<CalentarDayData>>) {
        datePicker.visibility=View.GONE
        titleTextView.visibility = View.VISIBLE
        selectButton.visibility = View.VISIBLE
        contentTextView.visibility = View.VISIBLE
        titleTextView.text=calentarDayBean.result.data.date
        contentTextView.text = calentarDayBean.result.data.toString()
    }

    override fun showError(errorMsg: String) {
        toast(errorMsg)
    }
}
