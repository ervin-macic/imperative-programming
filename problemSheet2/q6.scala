object Repetition{
  def checkRepetitionAtPos(s: Array[Char], n: Int): Boolean = {
    val N = s.size
    for (i <- 0 until (N - n))
      if (s(i) != s(i + n)) return false
    return true
  }

  def checkRepetition(s: Array[Char]): Int = {
    val N = s.size
    if(N == 0) throw new Exception("String must contain at least some characters")
    for (n <- 1 until N)
      if (checkRepetitionAtPos(s, n))
        return n
    return N
  }

  def main(args: Array[String]) : Unit = {
    println(checkRepetition("ThisisrepeatingThisisrep".toCharArray))
    // total 24 chars in the string N = 24
    // thisisrepeating contains 15 chars tbus n = 15 so N-n = 9
  }
}
