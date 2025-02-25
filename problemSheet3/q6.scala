object Q6{
    val a = Array(6,7,8,4,9,3,2)
    /** Partition the segment a[l..r)
    * @return k s.t. a[l..k) < a(k) <= a(k..r) and l <= k < r */
    def partition(l: Int, r: Int) : Int = {
        val x = a(l) // pivot
        // Invariant a[l+1..i) < x = a(l) <= a[j..r) && l < i <= j <= r
        // && a[0..l) = a_0[0..l) && a[r..N) = a_0[r..N)
        // && a[l..r) is a permutation of a_0[l..r)
        var i = l+1; var j = r
        while(i < j){
            if(a(i) < x) i += 1
            else{ val t = a(i); a(i) = a(j-1); a(j-1) = t; j -= 1 }
        }
        // swap pivot into position
        a(l) = a(i-1); a(i-1) = x
        i-1 // position of the pivot
    }

    def betterPartition(l: Int, r: Int) : Int = {
        /** Partition the segment a[l..r)
        return k s.t. a[l..k) < a(k) <= a(k..r) and l <= k < r */
        val x = a(l)
        var i = l+1
        var j = r
        // Invariant a[l+1..i) < x = a(l) <= a[j..r) && l < i <= j <= r
        // && a[0..l) = a_0[0..l) && a[r..N) = a_0[r..N)
        // && a[l..r) is a permutation of a_0[l..r)
        while(i < j) {
            if(a(i) < x) {
                i += 1
            } else {
                // move right pointer j one step at a time to the left until a(j-1) is a sensible swap with a(i)
                // Invariant: a(l) <= a[j..r) 
                while(a(j-1) >= x && i < j) j -= 1
                if(i != j) { 
                    val t = a(i)
                    a(i) = a(j-1)
                    a(j-1) = t
                    j -= 1
                }
            }
        }
        a(l) = a(i-1)
        a(i-1) = x
        i-1
    }

    def main(args: Array[String]) : Unit = {
        //println(partition(0, 6))
        println(betterPartition(0, 6))
    }
    /*

    An example where an element larger than the pivot moves two times:

    Consider 6 7 8 4 9 3 2 where A(l) = 6, A(r) = 2
    We check A(i) = 7 > 6 so swap A(i) with A(j-1), i.e. 7 and 3.
    6 3 8 4 9 7 2 now A(j) = 7.
    Check A(i) = 3 < 6 ok. Move i forward.
    6 3 8 4 9 7 2 now A(i) = 8 > 6 so swap A(i) with A(j-1), i.e. 8 and 9
    6 3 9 4 8 7 2 Uh oh, again A(i) = 9 > 6 so we move the 9 again (this is the redundant work, we 
    should really check whether the swap makes sense, i.e. whether A(j-1) < x otherwise we are bound
    to move the same element twice).
    Swap 9 with 4 
    6 3 4 9 8 7 2
    A(j) is now 9.
    Now A(i) = 4 < 6 ok. Move i forward.
    i == j so stop.
    A(i) = 9 so swap 4 and 6 (as A(i-1) = 4)
    Finally, reach 4 3 6 9 8 7 2 (the 2 at the end we don't care)

    */
}
