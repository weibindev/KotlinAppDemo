package com.wbb.kotlinapp.bean

/**
 *
 * @author vico
 * @date 2018-08-17
 */
data class CalentarDayBean<T>(val reason: String, val result: T, val error_code: Int)
