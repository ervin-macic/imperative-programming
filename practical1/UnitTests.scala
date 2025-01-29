object TestingCipher{
  /** Bit-wise exclusive-or of two characters */
  def xor(a: Char, b: Char) : Char = (a.toInt ^ b.toInt).toChar
      /** Encrypt plain using key; can also be used for decryption */
  def encrypt(key: Array[Char], plain: Array[Char]) : Array[Char] = {
    val plainLen = plain.size
    val keyLen = key.size
    var result = new Array[Char](plainLen)
    for(i <- 0 until plainLen) {
      result(i) = xor(plain(i), key(i % keyLen))
    }
    result
  }
  /** The main method just selects which piece of functionality to run */
  def main(args: Array[String]) : Unit = {
    if (args.length < 2) {
      println("Usage: MainApp <key> <plain>")
    } else {
      println(args(0))
      val key = args(0).toCharArray
      val plain = args(1).toCharArray
      val ans = encrypt(key, plain)
      print("ans: ")
      for(c <- ans) {
        print(c.toInt + " ")
      }
      val ans2 = encrypt(key, ans)
      for(c <- ans2) {
        print(c + " ")
      }
    }
  }
}