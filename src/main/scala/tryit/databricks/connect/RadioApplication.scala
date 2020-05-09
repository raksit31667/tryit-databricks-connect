package tryit.databricks.connect

import org.apache.spark.sql.SparkSession

class RadioApplication {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder()
      .master("local")
      .getOrCreate()

    RadioNotebook.countLevels(spark)
  }
}
