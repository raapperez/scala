package example

object Main extends  App{
  println(Lists.max(List(10, 200, -200)))
  println(Lists.sum(200::500::600::Nil))
}
