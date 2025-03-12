/** Each object of this class represents a dictionary, in which 
  * words can be looked up.
  * @param fname the name of a file containing a suitable list 
  * of words, one per line. */
import scala.collection.mutable.{HashSet, ArrayBuffer}
class AnagrammaticalDictionary(fname: String) {
    /** A Set object holding the words - internally a hash table */
    private val words = new HashSet[String]
    private var pairedWords = new ArrayBuffer[(String, String)]()
    /** Initialise dictionary with all words from file fname */
    private def initDict(fname: String) = { 
        val allWords = scala.io.Source.fromFile(fname).getLines()
        def include(w: String) = w.forall(_.isLower)
        for(w <- allWords; if include(w)) { 
            words += w
            pairedWords += ((w.sorted.toString, w.toString))
        }
        pairedWords = pairedWords.sortBy(_._1)
    }
    def printHead() : Unit = {
        var counter = 0
        for((sortedW, w) <- pairedWords; if counter < 10) {
            println((sortedW, w))
            counter += 1
        }
    }
    def findAnagrams(word: String) : Option[ArrayBuffer[String]]= {
        var wordSorted = word.sorted
        var i = 0
        var j = pairedWords.size
        var ans = new ArrayBuffer[String]()
        // invariant I: a[0..i) < x <= a[j..N) && 0 <= i <= j <= N
        var m = 0
        while(i < j) {
            m = (i+j)/2
            if(pairedWords(m)._1 < wordSorted) i = m+1 else j = m
        }
        // I && i = j, so a[0..i) < x <= a[i..N)
        m = i
        if(pairedWords(m)._1 == wordSorted) {
            // Take all anagrams to the left and to the right
            var mLeft = m 
            var mRight = m + 1
            while(mLeft > 0 && pairedWords(mLeft)._1 == wordSorted) {
                ans += pairedWords(mLeft)._2
                mLeft -= 1
            }
            while(mRight < pairedWords.size && pairedWords(mRight)._1 == wordSorted) {
                ans += pairedWords(mRight)._2 
                mRight += 1
            }
            return Some(ans)
        } else {
            return None
        }
    }
    def findLongestAnagrams() : (String, String) = {
        var maxSizeSoFar = 0
        var maxAnagramsSoFar = ("", "")
        for(i <- 1 until pairedWords.size) {
            var current = pairedWords(i)
            var previous = pairedWords(i-1)
            if(current._1 == previous._1 && maxSizeSoFar < current._1.size) {
                maxSizeSoFar = current._1.size
                maxAnagramsSoFar = (current._2, previous._2)
            } 
        }
        return maxAnagramsSoFar
    }
    def findLargestAnagramSet() : ArrayBuffer[String] = {
        var maxSizeSoFar = 0
        var maxSetSoFar = new ArrayBuffer[String]()
        var i = 0
        var currentSortedChars = pairedWords(0)._1
        while(i < pairedWords.size) {
            currentSortedChars = pairedWords(i)._1
            var currentSetSize = 0
            var currentSet = new ArrayBuffer[String]()
            while(i < pairedWords.size && pairedWords(i)._1 == currentSortedChars) {
                currentSet += pairedWords(i)._2
                currentSetSize += 1
                if(currentSetSize > maxSizeSoFar) {
                    maxSizeSoFar = currentSetSize
                    maxSetSoFar = currentSet
                }
                i += 1
            }
        }
        return maxSetSoFar
    }
    // Initialise the dictionary now (on constructing this object)
    initDict(fname)
    /** @return true if w is in the dictionary */
    def isWord(w: String) : Boolean = words.contains(w)
}
val dict = new AnagrammaticalDictionary("knuth_words.txt")
// dict.printHead()
val word = "break"
val anagrams = dict.findAnagrams(word)
anagrams match {
    case None => println(word + " has no anagrams in our dictionary!")
    case Some(ans) => {
        println("The anagrams of " + word + " are: ")
        for(w <- ans) {
            println(w)
        } 
    }
}
val longestAnagramPair = dict.findLongestAnagrams()
print("The longest anagram pair is: ")
println(longestAnagramPair)
val largestAnagramSet = dict.findLargestAnagramSet()
println("The largest anagram set is: ")
largestAnagramSet.foreach(println)
/*
The anagrams of break are: 
baker
brake
break
The longest anagram pair is: (physiopathological,pathophysiological)
The largest anagram set is: 
least
setal
slate
stale
steal
stela
tales
teals
tesla
*/