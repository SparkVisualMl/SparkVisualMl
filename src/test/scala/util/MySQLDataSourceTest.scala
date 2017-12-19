package util

import org.apache.spark.SparkConf
import org.apache.spark.sql.{Row, SparkSession,Dataset}

class MySQLDataSourceTest {



}
object MySQLDataSourceTest{

  def main (args: Array[String] ): Unit = {
    val conf = new SparkConf().setMaster("local[3]").setAppName("TEST")
    val spark = SparkSession.builder().config(conf).getOrCreate()
    val jdbcDF = spark.read.format("jdbc")
                .option("driver", "com.mysql.jdbc.Driver")
                .option("url", "jdbc:mysql://120.76.245.1:3306/test")
                .option("dbtable", "dui_ma")
                .option("user", "root")
                .option("password", "")
                .load();
    jdbcDF.rdd.collect().foreach(println)

  }
}

