package util

import java.sql.DriverManager

import org.apache.spark.rdd.JdbcRDD
import org.apache.spark.{SparkConf, SparkContext}


object JdbcTest {
  def main(args: Array[String]) {
    val conf = new SparkConf()
    val sc = new SparkContext(conf)

    val rdd = new JdbcRDD(
      sc,
      () => {
        Class.forName("oracle.jdbc.driver.OracleDriver").newInstance()
        DriverManager.getConnection("jdbc:oracle:thin:@172.16.222.112:1521:pms", "scyw", "scyw")
      },
      "SELECT * FROM MW_APP.CMST_AIRPRESSURE WHERE 1 = ? AND rownum < ?",
      1, 10, 1,
      r => (r.getString(1),r.getString(2),r.getString(5)))
    rdd.collect().foreach(println)
    sc.stop()
  }
}