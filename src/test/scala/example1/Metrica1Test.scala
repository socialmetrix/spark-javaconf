package example1

import org.scalatest._
import org.apache.spark.SparkContext
import org.apache.spark.SparkConf

class Metrica1Test extends FunSuite with BeforeAndAfterAll {

  var sc: SparkContext = _

  override def beforeAll {
    val conf = new SparkConf()
      .setMaster("local")
      .setAppName("test")
    sc = new SparkContext(conf)
  }

  override def afterAll {
    sc.stop
  }

  val metrica = new Metrica

  val tweet1 = Tweet(
    User("pablo"),
    Entities(
      Set(User("adrian")),
      Set(Hashtag("javaconf"))
    ),
    None
  )

  val tweet2 = Tweet(
    User("pablo"),
    Entities(
      Set(User("adrian")),
      Set()
    ),
    None
  )

  val tweet3 = Tweet(
    User("pablo"),
    Entities(
      Set(),
      Set(Hashtag("spark"))
    ),
    None
  )

  val tweet4 = Tweet(
    User("pablo"),
    Entities(
      Set(User("adrian")),
      Set(Hashtag("spark"), Hashtag("javaconf"))
    ),
    None
  )

  val tweet5 = Tweet(
    User("pedro"),
    Entities(
      Set(User("adrian"), User("pablo")),
      Set(Hashtag("javaconf"))
    ),
    Some(Tweet(
      User("pablo"),
      Entities(
        Set(User("adrian")),
        Set(Hashtag("javaconf"), Hashtag("spark"))
      ),
      None
    ))
  )

  test("extraer combinaciones 'FUNCIONA' (?)") {
    val rdd = sc.parallelize(List(tweet1, tweet2, tweet3, tweet4, tweet5))

    val result = metrica.extraerCombinaciones(rdd).collect.toList

    assert(
      result == List(
        (User("adrian"), Hashtag("javaconf")),
        (User("adrian"), Hashtag("spark")),
        (User("adrian"), Hashtag("javaconf")),
        (User("adrian"), Hashtag("javaconf")),
        (User("pablo"), Hashtag("javaconf")),
        (User("adrian"), Hashtag("spark")),
        (User("pablo"), Hashtag("spark"))
      )
    )
  }

  test("puedo ejecutar calcular con un rdd con tweets") {
    val rdd = sc.parallelize(List(tweet1, tweet2, tweet3, tweet4, tweet5))

    val result = metrica.calcular(rdd).collect.toList

    assert(
      result == List(
        ((User("adrian"), Hashtag("spark")), 2),
        ((User("pablo"), Hashtag("spark")), 1),
        ((User("adrian"), Hashtag("javaconf")), 3),
        ((User("pablo"), Hashtag("javaconf")), 1)
      )
    )
  }

}
