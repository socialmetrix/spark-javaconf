package example3

import java.text.Normalizer
import java.text.Normalizer.Form

case class Tweet(
  user: User,
  entities: Entities,
  retweeted_status: Option[Tweet]) {

  def mentions: Set[User] =
    retweeted_status.map(originalTweet =>
      originalTweet.mentions + originalTweet.user
    ).getOrElse(
      entities.user_mentions
    )

  def hashtags: Set[Hashtag] =
    retweeted_status.map(originalTweet =>
      originalTweet.hashtags
    ).getOrElse(
      entities.hashtags
    )

  def hashtagsByMentions: Set[(User, Hashtag)] = {
    for {
      hashtag <- hashtags
      mention <- mentions
    } yield (mention, hashtag)
  }

}

case class Entities(
  user_mentions: Set[User],
  hashtags: Set[Hashtag])

case class User(screen_name: String) {
  override def toString = s"@$screen_name"
}

case class Hashtag(text: String) {

  private val normalizedText = normalize(text)

  override def toString = s"#$normalizedText"

  override def hashCode: Int = normalizedText.hashCode

  override def equals(obj: Any): Boolean = obj match {
    case other: Hashtag => other.normalizedText == normalizedText
    case _              => false
  }

  private def normalize(text: String) = {
    Normalizer.normalize(text.toLowerCase(), Form.NFD)
      .replaceAll("[^\\x00-\\x7F]", "")
  }

}
