object Cipher {
  /** Bit-wise exclusive-or of two characters */
  def xor(a: Char, b: Char): Char = (a.toInt ^ b.toInt).toChar

  /** Print ciphertext in octal */
  def showCipher(cipher: Array[Char]) =
    for (c <- cipher) {
      print(c / 64)
      print(c % 64 / 8)
      print(c % 8)
      print(" ")
    }

  /** Read file into array */
  def readFile(fname: String): Array[Char] =
    scala.io.Source.fromFile(fname).toArray

  /** Read from stdin in a similar manner */
  def readStdin() = scala.io.Source.stdin.toArray

  /** Encrypt plain using key; can also be used for decryption */
  def encrypt(key: Array[Char], plain: Array[Char]): Array[Char] = {
    val N = plain.size
    val K = key.size
    var result = new Array[Char](N)
    for (i <- 0 until N)
      result(i) = xor(plain(i), key(i % K))
    return result
  }

  def checkRepetitionAtPos(keyChars: Array[Char], j: Int): Boolean = {
    val K = keyChars.size
    for (i <- 0 until (K - j))
      if (keyChars(i) != keyChars(i + j)) return false
    return true
  }

  /** Check whether the array repeats in equally spaced intervals */
  def checkRepetition(keyChars: Array[Char]): (Boolean, Int) = {
    val K = keyChars.size
    for (j <- 1 until (K - 1))
      if (checkRepetitionAtPos(keyChars, j))
        return (true, j)
    return (false, -1)
  }

  /** Try to decrypt ciphertext, using crib as a crib */
  def tryCrib(crib: Array[Char], ciphertext: Array[Char]): Unit = {
    val N = ciphertext.size
    val K = crib.size
    for (start <- 0 to (N - K)) {
      var keyChars = new Array[Char](K)

      // Construct keyChars from cipherText and crib
      for (i <- 0 until K)
        keyChars(i) = xor(ciphertext(start + i), crib(i))

      // Check whether corresponding key has repeating pattern
      val ans = checkRepetition(keyChars)

      if (ans._1) {
        val j = ans._2
        var keyStartIndex = 0
        keyStartIndex = if (start % j == 0) 0 else j - (start % j)
        var key = Array[Char](' ')

        // Split into two cases based on whether key is cut off
        if ((K - keyStartIndex) >= j) {
          key = keyChars.slice(keyStartIndex, keyStartIndex + j)
        } else {
          key = keyChars.slice(keyStartIndex, K)
          var remaining = keyChars.slice(K - j, keyStartIndex)
          key = key ++ remaining
        }
        for (c <- key) print(c)
      }
    }
  }

  def countMatches(ciphertext: Array[Char], shift: Int): Int = {
    val N = ciphertext.size
    var numMatches = 0
    for (i <- 0 until (N - shift))
      if (ciphertext(i) == ciphertext(i + shift))
        numMatches += 1
    return numMatches
  }

  /** Guess the length of the key */
  def crackKeyLen(ciphertext: Array[Char]): Unit = {
    for (shift <- 1 to 30) {
      val numMatches = countMatches(ciphertext, shift)
      println(s"$shift: $numMatches")
    }
  }

  def printable(c: Char): Boolean = (c.toInt >= 32 && c.toInt <= 127)

  /** Guess characters of the key. */
  def crackKey(klen: Int, ciphertext: Array[Char]): Unit = {
    val N = ciphertext.size
    for (m <- 1 until N / (2 * klen)) {
      var s = m * klen
      for (i <- 0 until (N - s)) {
        if (ciphertext(i) == ciphertext(i + s)) {
          // assuming plaintext(i) = ' ', what keychar s.t. keychar xor ' ' == ciphertext(i)
          var keyChar = xor(ciphertext(i), ' ')
          var idx = i % klen // might not be correct
          if (printable(keyChar)) println(s"$idx: $keyChar")
        }
      }
    }
  }

  def main(args: Array[String]): Unit = {
    val errString =
      "Usage: scala Cipher (-encrypt|-decrypt) key [file]\n" +
        "     | scala Cipher -crib crib [file]\n" +
        "     | scala Cipher -crackKeyLen [file]\n" +
        "     | scala Cipher -crackKey len [file]"

    // Get the plaintext, either from the file whose name appears in position
    // pos, or from standard input
    def getPlain(pos: Int) =
      if (args.length == pos + 1) readFile(args(pos)) else readStdin()

    // Check there are at least n arguments
    def checkNumArgs(n: Int) = if (args.length < n) { println(errString); sys.exit }

    // Parse the arguments, and call the appropriate function
    checkNumArgs(1)
    val command = args(0)
    if (command == "-encrypt" || command == "-decrypt") {
      checkNumArgs(2); val key = args(1).toArray; val plain = getPlain(2)
      print(new String(encrypt(key, plain)))
    } else if (command == "-crib") {
      checkNumArgs(2); val key = args(1).toArray; val plain = getPlain(2)
      tryCrib(key, plain)
    } else if (command == "-crackKeyLen") {
      checkNumArgs(1); val plain = getPlain(1)
      crackKeyLen(plain)
    } else if (command == "-crackKey") {
      checkNumArgs(2); val klen = args(1).toInt; val plain = getPlain(2)
      crackKey(klen, plain)
    } else println(errString)
  }
}
