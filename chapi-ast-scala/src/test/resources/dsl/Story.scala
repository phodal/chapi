// https://gist.github.com/nicerobot/4189552
object Story {
  object Once { def upon = (x: a.type) => x }
  object a { def time = (x: languages.type) => x }
  object languages { def were = (x: inflexible.type) => x }
  object inflexible { def along = (x: came.type) => x }
  object came { def Scala = (x: inflexible.type) => x }
  implicit def string(s : String) : inflexible.type = inflexible

  def main(args: Array[String]): Unit = {
    Once upon a time languages were inflexible; "Then " along came Scala ". The End!"
  }
}
