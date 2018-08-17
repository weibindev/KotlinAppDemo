package com.wbb.kotlinapp.api

import com.wbb.kotlinapp.bean.CalentarDayBean
import com.wbb.kotlinapp.bean.CalentarDayData
import com.wbb.kotlinapp.bean.CalentarDayResult
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

/**
 *
 * @author vico
 * @date 2018-08-17
 */
interface RetrofitService {
    /**
     * 获取当天的详细信息
     */
    @GET("calendar/day")
    fun calenderDay(
            @Query("date") date: String,
            @Query("key") key: String
    ): Observable<CalentarDayBean<CalentarDayResult<CalentarDayData>>>
}