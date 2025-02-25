/*
state: bag : Int → Int
init: {}
Abs: {c(i) | c(i) == number of occurences of i, 0 <= i < MAX}
*/
trait Bag {
    val c = new Array[Int](MAX);
    /** Add elem to the set.
    * pre: elem < MAX
    * post:  */
    def add(elem: Int) : Unit {
        assert(0 <= elem && elem < MAX)
        c(elem) += 1
    }
    /** Get number of copies of element 
    * pre: elem < MAX
    * post: return # copies of elem */
    def numCopies(elem: Int) : Int {
        assert(0 <= elem && elem < MAX)
        return c(elem)
    }
}
