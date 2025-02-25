/** state: S : P Int
* init: S = {} */
// DTI: (for all x,y in S, x != y) && (for all x in S, x is an integer)
trait IntSet{
    private val N
    /** Add elem to the set.
    * post: S = S0 ∪ {elem} */
    def add(elem: Int): Unit
    /** Does the set contain elem?
    * post: S = S0 ∧ returns elem ∈ S */
    def contains(elem: Int): Boolean
    /** Remove elem from the set.
    * post: S = S0 − {elem} */
    def remove(elem: Int): Unit
    /** The size of the set.
    * post: S = S0 ∧ returns #S */
    def size: Int
}

/*
(a) Suppose we want to restrict the set to contain elements from [0..N). Sketch the necessary
changes to the specification.
Answer:

/** state: S : P Int
* init: S = {} */
// DTI: (for all x in S, 0 <= x < N) && (for all x,y in S, x != y) 
// && (for all x in S, x is an integer)
trait IntSet{
    private val N
    /** Add elem to the set.
    * pre: 0 <= elem < N
    * post: S = S0 ∪ {elem} */
    def add(elem: Int): Unit
    /** Does the set contain elem?
    * post: S = S0 ∧ returns elem ∈ S */
    def contains(elem: Int): Boolean
    /** Remove elem from the set.
    * post: S = S0 − {elem} */
    def remove(elem: Int): Unit
    /** The size of the set.
    * post: S = S0 ∧ returns #S */
    def size: Int
    /** Is the given element in the given range 0 until N?
    * post: return elem ∈ 0 until N */
    def checkElem(elem: Int) : Boolean
}

(b) One way to implement such a set is to use an array a of booleans such that a(x) is
true precisely if x is in the set. This data structure is often called a bit map, since each
element of the array could be a single bit.
Implement a class BitMapSet following this idea. Write down a suitable abstraction
function and datatype invariant. (We will be seeing BitMapSet in later lectures.)
*/

/* A set of integers in range [0..N) */
// DTI: (for all i in [0..N), a(i) == true iff i is in the set)
// Abs: Set = {i | a(i) == True && 0 <= i < N}
class BitMapSet(val N: Int) {
    private val a = new Array[Boolean](N)
    def add(x: Int) : Unit {
        require((x < 0 && x >= N), s"Number $x is not in range 0 until $N")
        a(x) = x
    }
    def remove(x: Int) : Unit {
        require((x < 0 && x >= N), s"Number $x is not in range 0 until $N")
        a(x) = 0
    }
    def isIn(x: Int) : Boolean {
        if(x < 0 || x >= N) return false
        return a(x)
    }
    def size() : Int {
        var count = 0
        for(i <- 0 until N)
            if(a(i)) count += 1
        return count 
    }
}