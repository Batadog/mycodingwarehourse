package com.baixinbk.cn

import redis.clients.jedis.{JedisPool, JedisPoolConfig}

object JedisConnectionPool {
  private val config = new JedisPoolConfig

  // 设置最大连接数
  config.setMaxTotal(10)
  // 最大空闲连接数据
  config.setMaxIdle(2)
  // 获取连接时是否检查有效性
  config.setTestOnBorrow(true)

  private val jedisPool = new JedisPool(config, "node01", 6379)

  def getConnection = jedisPool.getResource


  def main(args: Array[String]): Unit = {
    val conn = JedisConnectionPool.getConnection
    val str = conn.ping()
    println(str)
  }

}
