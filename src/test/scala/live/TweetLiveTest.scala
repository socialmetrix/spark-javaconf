package live

import org.scalatest.FunSuite

class TweetLiveTest extends FunSuite {

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

  //  test("retweet mentions agrega al usuario original") {
  //    assert(
  //      tweet5.mentions == Set(User("pablo"), User("adrian"))
  //    )
  //  }
  //
  //  test("tweet mentions las retorna sin cambios") {
  //    assert(
  //      tweet4.mentions == Set(User("adrian"))
  //    )
  //  }
  //
  //  test("retweet hashtags retorna los del tweet original") {
  //    assert(
  //      tweet5.hashtags == Set(Hashtag("javaconf"), Hashtag("spark"))
  //    )
  //  }
  //
  //  test("tweet hashtags los retorna sin cambios") {
  //    assert(
  //      tweet3.hashtags == Set(Hashtag("spark"))
  //    )
  //  }
  //
  //  test("retweet hashtagsByMentions combina cada hashtag con cada usuario mencionado del tweet original") {
  //    assert(
  //      tweet5.hashtagsByMentions == Set(
  //        (User("pablo"), Hashtag("spark")),
  //        (User("pablo"), Hashtag("javaconf")),
  //        (User("adrian"), Hashtag("spark")),
  //        (User("adrian"), Hashtag("javaconf"))
  //      )
  //    )
  //  }
  //
  //  test("tweet hashtagsByMentions combina cada hashtag con cada usuario mencionado del tweet original") {
  //    assert(
  //      tweet4.hashtagsByMentions == Set(
  //        (User("adrian"), Hashtag("spark")),
  //        (User("adrian"), Hashtag("javaconf"))
  //      )
  //    )
  //  }
  //
  //  test("tweet hashtagsByMentions sin menciones da set vacio") {
  //    assert(
  //      tweet3.hashtagsByMentions == Set()
  //    )
  //  }

}
