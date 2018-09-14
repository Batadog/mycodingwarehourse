package com.baixinbk.cn

import java.util.Calendar

import org.apache.commons.lang3.time.FastDateFormat

/**
  * 时间转换工具类
  */
object TimeUtils {
  private val fastDateFormat: FastDateFormat =
    FastDateFormat.getInstance("yyyy-MM-dd HH:mm:ss")

  private val calendar: Calendar = Calendar.getInstance()

  // apply注入方法把String类型的时间转换为Long类型的时间
  def apply(time: String): Long = {
    calendar.setTime(fastDateFormat.parse(time))
    calendar.getTimeInMillis
  }

  // 通过Calendar改变日期
  def updateCalendar(amout: Int): Long = {
    calendar.add(Calendar.DATE, amout)
    val time = calendar.getTimeInMillis
    calendar.add(Calendar.DATE, -amout)
    time
  }


}
