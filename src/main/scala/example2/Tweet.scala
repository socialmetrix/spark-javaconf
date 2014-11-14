package example2

case class Tweet(
  user: User,
  entities: Entities,
  retweeted_status: Option[Tweet]) {

  def hashtags: Set[Hashtag] = {
    if (retweeted_status.isDefined)
      retweeted_status.get.entities.hashtags
    else
      entities.hashtags
  }

  def mentions: Set[User] = {
    if (retweeted_status.isDefined)
      retweeted_status.get.entities.user_mentions +
        retweeted_status.get.user
    else
      entities.user_mentions
  }

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
  override def toString = s"#$text"
}
