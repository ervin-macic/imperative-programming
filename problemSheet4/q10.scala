object Q10{
    /*
    state: bag : Int → Int
    init: {}
    Abs: {c(i) | c(i) == number of occurences of i, 0 <= i < MAX}
    */
    val MAX = 1000
    trait Bag {
        /** Add elem to the set.*/
        def add(elem: Int) : Unit
        /** Get number of copies of element */
        def numCopies(elem: Int) : Int
    }
    object IntBag extends Bag {
        val c = new Array[Int](MAX);
        /** Add elem to the set.
        * pre: elem < MAX
        * post:  */
        def add(elem: Int) : Unit = {
            assert(0 <= elem && elem < MAX)
            c(elem) += 1
        }
        /** Get number of copies of element 
        * pre: elem < MAX
        * post: return # copies of elem */
        def numCopies(elem: Int) : Int = {
            assert(0 <= elem && elem < MAX)
            return c(elem)
        }
    }

    def main(args: Array[String]) : Unit = {
        val a = Array(1,5,3,5,6,6,2,2,10,7,4,2)
        var max_elem = a(0)
        for(i <- 0 until a.size) {
            if(max_elem < a(i)) {
                max_elem = a(i)
            }
            IntBag.add(a(i))
        }
        for(i <- 0 to max_elem) {
            for(j <- 0 to IntBag.numCopies(i)-1) {
                println(i)
            }
        }
        
    }
}
