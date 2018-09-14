package com.baixinbk.cn

import org.apache.commons.lang3.time.FastDateFormat

/**
  * 过滤工具类
  */
object FilterUtils {
  private val fastDateFormat: FastDateFormat =
    FastDateFormat.getInstance("yyyy年MM月dd日,E,HH:mm:ss")

  // 以时间作为过滤条件
  def filterByTime(arr: Array[String], startTime: Long, endTime: Long): Boolean = {
    val time = arr(1)
    val time_long = fastDateFormat.parse(time).getTime
    time_long >= startTime && time_long < endTime
  }

  // 以事件作为过滤条件
  def filterByType(arr: Array[String], eventType: String): Boolean = {
    val _type = arr(0)
    _type.equals(eventType)
  }

  // 以事件类型和时间作为过滤条件
  def filterByTypeAndTime(arr: Array[String], eventType: String,
                          startTime: Long, endTime: Long): Boolean = {
    val _type = arr(0)
    val time = arr(1)
    val time_long = fastDateFormat.parse(time).getTime

    _type.equals(eventType) && time_long >= startTime && time_long < endTime
  }

  // 以多个事件类型作为过滤条件
  def filterByTypes(arr: Array[String], eventTypes: String*): Boolean = {
    val _type = arr(0)
    for (et <- eventTypes) {
      if (_type.equals(et)) {
        return true
      }
    }
    false
  }






}
