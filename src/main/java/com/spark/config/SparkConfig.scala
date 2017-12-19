package com.spark.config

import org.apache.spark.api.java.JavaSparkContext
import org.apache.spark.sql.SparkSession
import org.apache.spark.{SparkConf, SparkContext}
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.{Bean, Configuration}
import org.springframework.stereotype.Component
import org.springframework.web.servlet.config.annotation.EnableWebMvc
import org.springframework.web.servlet.view.{InternalResourceViewResolver, JstlView}

@EnableWebMvc
@Component
@Configuration
class SparkConfig {
  @Value("${spark.name}")
  var appName = ""
  @Value("${spark.master}")
  var master = ""
  @Value("${spark.loglevel}")
  var logLevel = ""
  @Value("${spark.job.allowMultipleContexts}")
  var allowMultipleContexts=""
  @Bean
  def getSparkContext(): SparkContext ={
    val conf = new SparkConf().setAppName(appName).setMaster(master)
    conf.set("spark.driver.allowMultipleContexts",allowMultipleContexts)
    val spark = SparkSession.builder().config(conf).getOrCreate()
    spark.sparkContext.setLogLevel(logLevel)
    spark.sparkContext
  }

  @Bean
  def getSparkSession():SparkSession={
    val conf = new SparkConf().setAppName(appName).setMaster(master)
    conf.set("spark.driver.allowMultipleContexts",allowMultipleContexts)

    val spark = SparkSession.builder().config(conf).getOrCreate()
    spark.sparkContext.setLogLevel(logLevel)
    spark
  }
  @Bean
  def getJavaSparkContext: JavaSparkContext={
    val conf = new SparkConf().setAppName(appName).setMaster(master)
    conf.set("spark.driver.allowMultipleContexts",allowMultipleContexts)
    val javaSparkContext = new JavaSparkContext(conf)
    javaSparkContext.setLogLevel(logLevel)
    javaSparkContext
  }


  @Bean // 出现问题原因 @bean 忘记添加
  def viewResolver: InternalResourceViewResolver =  {
      val viewResolver: InternalResourceViewResolver = new InternalResourceViewResolver
      viewResolver.setPrefix("/WEB-INF/jsp/")
      viewResolver.setSuffix(".jsp")
      viewResolver.setViewClass(classOf[JstlView])
      return viewResolver
  }
}
