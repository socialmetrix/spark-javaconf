package json

import org.json4s._
import org.json4s.native.JsonMethods._

object Deserializer {

  implicit val formats = DefaultFormats

  def fromJson[T: Manifest](json: String) = parse(json).extract[T]

}