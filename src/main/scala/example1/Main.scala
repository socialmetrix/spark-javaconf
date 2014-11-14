package example1

import org.apache.spark.SparkConf
import org.apache.spark.SparkContext
import org.apache.spark.SparkContext._
import org.apache.spark.rdd.RDD
import json.Deserializer

object Main {

  def main(args: Array[String]) {
    val sc = new SparkContext("local[*]", "Example1", new SparkConf())

    val rddTweets: RDD[Tweet] = sc
      .textFile("twitter.json")
      .map(Deserializer.fromJson[Tweet])

    // ------------

    val combinados = new Metrica().calcular(rddTweets)

    // ------------

    val result = combinados
      .sortBy({
        case (userHashtag, count) => count
      }, false)
      .take(20)

    println(result.toList)
  }

}
