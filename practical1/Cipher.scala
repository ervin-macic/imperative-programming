object Cipher{
  /** Bit-wise exclusive-or of two characters */
  def xor(a: Char, b: Char) : Char = (a.toInt ^ b.toInt).toChar

  /** Print ciphertext in octal */
  def showCipher(cipher: Array[Char]) =
    for(c <- cipher){ print(c/64); print(c%64/8); print(c%8); print(" ") }

  /** Read file into array */
  def readFile(fname: String) : Array[Char] = 
    scala.io.Source.fromFile(fname).toArray

  /** Read from stdin in a similar manner */
  def readStdin() = scala.io.Source.stdin.toArray

  /* ----- Functions below here need to be implemented ----- */

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
  def checkRepetitionAtPos(keyChars: Array[Char], j: Int) : Boolean = {
  val K = keyChars.size
    for(i <- 0 until (K-j)) {
    println("accessing index")
    println(i)
    println(j)
      if(keyChars(i) != keyChars(i+j)) false
    }
    true
  }
  def checkRepetition(keyChars: Array[Char]) : (Boolean, Int) = {
    val K = keyChars.size
    for(j <- 0 until (K-1)) {
      if(checkRepetitionAtPos(keyChars, j)) (true, j)
    }
    (false, -1)
  }
  /** Try to decrypt ciphertext, using crib as a crib */
  def tryCrib(crib: Array[Char], ciphertext: Array[Char]) : Unit = {
    val N = ciphertext.size
    val K = crib.size
    println(N)
    println(K)
    for(start <- 0 to (N-K)) {
     	var keyChars = new Array[Char](K)
    	for(i <- start until (start+K)){
    	  keyChars(i) = xor(ciphertext(i), crib(i))
    	}
    	println("Do i ever get here?")
    	val ans = checkRepetition(keyChars)
    	println("Do i pass to here?")
    	if(ans._1) {
    	  val j = ans._2
    	  val keyLen = j
    	  var keyStartIndex = 0
    	  if (start % j == 0) {
    	    keyStartIndex = 0
    	  } else {
    	    keyStartIndex = j - (start % j)
    	  }
    	  if((K - keyStartIndex) >= j) {
    	    var key = keyChars.slice(keyStartIndex, keyStartIndex+j)
    	    for(c <- key) print(c)
    	  } else { 
    	    var key = keyChars.slice(keyStartIndex, K) 
    	    for(c <- keyChars.slice(K-j, keyStartIndex)) key :+ c
    	    for(c <- key) print(c)
    	  }
    	  // uzmi od keyStartIndexa desno j stvari. Ako preletis K-1 (inclusive, onda kreni od K-j (inclusive) uzimaj nadalje dok ne ispunis kvotu od j.
    	  // keyChars[K-j, K) DOLFRUDOL K=9, K-j = 3 [0,3) = [6,9) start = 7 i tu je 0 1 2 3 4 5 6 7 start % j so U je na indeksu 1 u kljucu
    	  // nadji prvi indeks poslije start koji je djeljiv sa j. odatle ti pocinje kljuc. kupi iz keyChars odatle nadalje i udari krug ako ima potrebe
    	}
    }
  }
  
  /** The first optional statistical test, to guess the length of the key */
  def crackKeyLen(ciphertext: Array[Char]) : Unit = ???

  /** The second optional statistical test, to guess characters of the key. */
  def crackKey(klen: Int, ciphertext: Array[Char]) : Unit = ???

/** The main method just selects which piece of functionality to run */
  def main(args: Array[String]) = {
    // string to print if error occurs
    val errString = 
      "Usage: scala Cipher (-encrypt|-decrypt) key [file]\n"+
      "     | scala Cipher -crib crib [file]\n"+
      "     | scala Cipher -crackKeyLen [file]\n"+
      "     | scala Cipher -crackKey len [file]"

    // Get the plaintext, either from the file whose name appears in position
    // pos, or from standard input
    def getPlain(pos: Int) = 
      if(args.length==pos+1) readFile(args(pos)) else readStdin()

    // Check there are at least n arguments
    def checkNumArgs(n: Int) = if(args.length<n){println(errString); sys.exit}

    // Parse the arguments, and call the appropriate function
    checkNumArgs(1)
    val command = args(0)
    if(command=="-encrypt" || command=="-decrypt"){
      checkNumArgs(2); val key = args(1).toArray; val plain = getPlain(2)
      print(new String (encrypt(key,plain)))
    }
    else if(command=="-crib"){
      checkNumArgs(2); val key = args(1).toArray; val plain = getPlain(2)
      tryCrib(key, plain)
    }
    else if(command=="-crackKeyLen"){
      checkNumArgs(1); val plain = getPlain(1)
      crackKeyLen(plain)
    }      
    else if(command=="-crackKey"){
      checkNumArgs(2); val klen = args(1).toInt; val plain = getPlain(2)
      crackKey(klen, plain)
    }
    else println(errString)
  }
}
