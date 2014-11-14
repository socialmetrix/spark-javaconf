package live

import org.apache.spark.SparkContext._
import org.apache.spark.rdd.RDD

class Metrica {

  def calcular(rdd: RDD[Tweet]): RDD[((User, Hashtag), Int)] = {
    val combinaciones = extraerCombinaciones(rdd)

    val result = combinaciones
      .map(userHashtag => (userHashtag, 1))
      .reduceByKey((a, b) => a + b)

    return result
  }

  def extraerCombinaciones(rdd: RDD[Tweet]): RDD[(User, Hashtag)] = {
    return rdd.flatMap { tweet =>

      // este "for" combina a todos los hashtags 
      // del tweet contra las menciones del mismo
      for {
        hashtag <- {
          if (tweet.retweeted_status.isDefined)
            tweet.retweeted_status.get.entities.hashtags
          else
            tweet.entities.hashtags
        }
        mention <- {
          if (tweet.retweeted_status.isDefined)
            tweet.retweeted_status.get.entities.user_mentions +
              tweet.retweeted_status.get.user
          else
            tweet.entities.user_mentions
        }
      } yield (mention, hashtag)

    }
  }

}
