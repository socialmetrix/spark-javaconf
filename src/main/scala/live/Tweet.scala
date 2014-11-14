package live

case class Tweet(
  user: User,
  entities: Entities,
  retweeted_status: Option[Tweet])

case class Entities(
  user_mentions: Set[User],
  hashtags: Set[Hashtag])

case class User(screen_name: String) {
  override def toString = s"@$screen_name"
}

case class Hashtag(text: String) {
  override def toString = s"#$text"
}
