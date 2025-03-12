/** Each object of this class represents a dictionary, in which 
  * words can be looked up.
  * @param fname the name of a file containing a suitable list 
  * of words, one per line. */
class Dictionary(fname: String) {
    /** A Set object holding the words - internally a hash table */
    private val words = new scala.collection.mutable.HashSet[String]
    /** Initialise dictionary with all words from file fname */
    private def initDict(fname: String) = { 
        val allWords = scala.io.Source.fromFile(fname).getLines()
        def include(w: String) = w.forall(_.isLower)
        for(w <- allWords; if include(w)) words += w
    }
    // Initialise the dictionary now (on constructing this object)
    initDict(fname)
    /** @return true if w is in the dictionary */
    def isWord(w: String) : Boolean = words.contains(w)
}

def include(x: Char, ys: List[Char]) : List[List[Char]] = {
    ys match {
        case Nil => return List(List(x))
        case (y::ys) => {
            return (x::y::ys) :: ((include(x,ys)).map((y::_)))
        }
    }
}
def permutations(xs: List[Char]) : List[List[Char]] = {
    xs match {
        case Nil => {
            return List(Nil)
        }
        case (x::xs) => {
            (for {
                ys <- permutations(xs)     
                zs <- include(x, ys)         
            } yield zs)
        }
    }
}


val dict = new Dictionary("knuth_words.txt")

var givenWord = "break"
val perms = permutations(givenWord.toList)
for(p <- perms) {
    val permutedWord = p.mkString("")
    if(dict.isWord(permutedWord)) {
        println(permutedWord)
    }
}