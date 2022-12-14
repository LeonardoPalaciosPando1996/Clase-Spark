package org.datascience.spark.dataframes

import org.apache.spark.sql.SparkSession
import org.apache.log4j.{Level, Logger}
import org.apache.spark.sql.types.{ArrayType, IntegerType, StringType, StructType}

object DataframeInit01 {

    Logger.getLogger("org").setLevel(Level.OFF)
    Logger.getLogger("akka").setLevel(Level.OFF)

  def main(args: Array[String]): Unit = {

    val spark = SparkSession.builder()
      .master("local[1]")
      .appName("DataFrameInit01")
      .getOrCreate()

    spark.sparkContext.setLogLevel("ERROR")

    import spark.implicits._ // ES IMPORTANTE , NO OLVIDAR

    val schema =
      new StructType()
        .add("hometown", StringType)
        .add("id", IntegerType)
        .add("name", StringType)
        .add("year", IntegerType)


    val dataframe = spark.sparkContext.parallelize(
      Seq(
        (10001, "Lima"),
        (10002, "Ica"),
        (10003, "Trujillo"),
        (10004, "Cusco")
      )
    ).toDF("code", "description")

    dataframe.printSchema()

    dataframe.show()

  }


}
