# Scala


## Play API Routes

[RoutesFileParser.scala](https://github.com/playframework/playframework/blob/main/dev-mode/routes-compiler/src/main/scala/play/routes/compiler/RoutesFileParser.scala)



```scala
private[routes] class RoutesFileParser extends JavaTokenParsers {
  override def skipWhitespace = false
  override val whiteSpace     = """[ \t]+""".r

  def EOF: util.matching.Regex = "\\z".r

  def namedError[A](p: Parser[A], msg: String): Parser[A] = Parser[A] { i =>
    p(i) match {
      case Failure(_, in) => Failure(msg, in)
      case o              => o
    }
  }

  def several[T](p: => Parser[T]): Parser[List[T]] = Parser { in =>
    import scala.collection.mutable.ListBuffer
    val elems = new ListBuffer[T]
    def continue(in: Input): ParseResult[List[T]] = {
      val p0 = p // avoid repeatedly re-evaluating by-name parser
      @scala.annotation.tailrec
      def applyp(in0: Input): ParseResult[List[T]] = p0(in0) match {
        case Success(x, rest) =>
          elems += x; applyp(rest)
        case Failure(_, _) => Success(elems.toList, in0)
        case err: Error    => err
      }
      applyp(in)
    }
    continue(in)
  }

```
