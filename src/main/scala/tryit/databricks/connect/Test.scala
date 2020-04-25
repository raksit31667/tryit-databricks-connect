package tryit.databricks.connect

import org.apache.spark.sql.SparkSession

class Test {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder()
      .master("local")
      .getOrCreate()
    println(spark.range(100).reduce(_ + _))
  }
}
