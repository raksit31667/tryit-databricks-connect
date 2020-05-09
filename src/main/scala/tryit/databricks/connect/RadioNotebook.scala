package tryit.databricks.connect

import com.databricks.dbutils_v1.DBUtilsHolder.dbutils
import org.apache.spark.sql.{DataFrame, SparkSession}

object RadioNotebook {

  def countLevels(spark: SparkSession) = {
    val containerName = "radio"
    val storageAccountName = "databricksconnecttest"
    val sas = "<your-sas-token-here>"

    val url = s"wasbs://$containerName@$storageAccountName.blob.core.windows.net/"
    val config = s"fs.azure.sas.$containerName.$storageAccountName.blob.core.windows.net"

    dbutils.fs.mount(
      source = url,
      mountPoint = "/mnt/radio",
      extraConfigs = Map(config -> sas)
    )

    val dataFrame: DataFrame = spark.read.json("/mnt/radio/radio.json")
    val generalInfoDataFrame: DataFrame = dataFrame.select("firstname", "lastname", "gender", "location", "level")
    generalInfoDataFrame.createOrReplaceTempView("generalInfo")
    val levels: DataFrame = spark.sql(
      """
      SELECT level, count(*) as count
      FROM generalInfo
      GROUP BY level
      """)
    levels.write.mode("overwrite").csv("/mnt/radio/levels.csv")
  }
}
