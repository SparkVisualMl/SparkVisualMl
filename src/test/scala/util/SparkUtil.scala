package util
import org.apache.spark.{SparkConf, SparkContext}
class SparkUtil {

}
object SparkUtil{


  def main(args:Array[String]): Unit ={
    val conf = new SparkConf()
    conf.setAppName("test").setMaster("local[3]")
    val sc = new SparkContext(conf)
    test1(sc)
  }
  def test1(sc:SparkContext): Unit ={
    val data = Array("fsdf,dsfs,fsdf,dfsf,fdsf","dsfs,ddd,ddd,ddd,dfdfs,dddd")
    val lineRdd = sc.parallelize(data)
    lineRdd.flatMap(_.split(",")).map(w=>(w,1)).reduceByKey(_+_).collect().foreach(println)
  }


}
