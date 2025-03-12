class HashBag{
    // DTI: (size_ represents the total number of elements in the bag) && (every element of the table is either node with a word and count >= 1 or an empty element of the table 
    // with null or <DELETED> as the word and count = 0)
    private val MAX = 100
    private var size_ = 0
    private def hash(word: String) : Int = {
        var e = 1
        for(c <- word) e = (e*41 + c.toInt) % MAX
        e
    }
    /*
    post: null if word is not present in the table, 
    otherwise, return index at which it was found using
    linear probing
    */
    // if it found it I want the index of where it was found
    private def find(word: String) : (Boolean, Option[Int]) = {
        var h = hash(word)
        // this could never terminate if table is full and doesn't contain the word
        // so keep track of how many loops are done
        var counter = 0
        while(table(h) != null && table(h).word != null && table(h).word != word && counter < MAX) { //space already taken by something else
            h = (h+1) % MAX
            counter += 1
        }
        if(table(h) != null && table(h).word == word) {
            return (true, Some(h))
        } else if (table(h) == null || table(h).word == null) {
            return (false, Some(h))
        } else { // word not found and table full
            return (false, None)
        }
    }
    def add(word: String) = {
        val h = hash(word)
        val n = find(word)
        n._2 match {
            case Some(index) => {
                if(n._1) { // if found the word, increment count
                    table(index).count += 1
                } else { // found a free space
                    if (table(index) == null) {
                        table(index) = new HashBag.Element(word, 1)
                    } else {
                        table(index).word = word
                        table(index).count = 1
                    }
                }
                size_ += 1
            }
            case None => { // word not found and table full
                throw new Exception("Table full!")
            }
        }
    }
    def count(word: String) : Int = {
        val h = hash(word)
        val n = find(word)
        n._2 match {
            case Some(index) => {
                if(n._1) {
                    return table(index).count 
                } else {
                    return 0
                }
            }
            case None => { // word not found and table full
                return 0
            }
        }
    }
    private val DELETED = "<DELETED>"
    def delete(word: String) : Boolean = {
        val h = hash(word)
        val n = find(word)
        n._2 match {
            case Some(index) => {
                if(n._1) { // word found 
                    if(table(index).count > 1) {
                        table(index).count -= 1
                    } else {
                        // Using a special marker instead of null to prevent breaking the probe chain
                        table(index).count = 0
                        table(index).word = DELETED
                        size_ -= 1  
                    }
                    return true
                }
                return false // word not found
            }
            case None => return false
        }
    }
    private val table = new Array[HashBag.Element](MAX)
}
object HashBag {
    class Element(var word: String, var count: Int)
}