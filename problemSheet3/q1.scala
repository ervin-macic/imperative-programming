/** Find index i s.t. a[0..i) < x <= a[i..N).
// i.e. leftmost element of array that is >= x (except in strange cases)
* Pre: a is sorted. */

object Q1 {
    def search(a: Array[Int], x: Int) : Int = {
        val N = a.size
        // invariant I: a[0..i) < x <= a[j..N) && 0 <= i <= j <= N
        var i = 0; var j = N
        while(i < j){
            val m = (i+j)/2 // i <= m < j
            if(a(m) < x) i = m+1 else j = m
        }
        // I && i = j, so a[0..i) < x <= a[i..N)
        i
    }

    def main(args: Array[String]) : Unit = {
        val A = Array(1,4,5,6,10,12,13)
        val unsortedA = Array(3,4,1,5,2,6,7,2) // N = 8, m = (0+8)/2 = 4, a(4) < 2? yes, i = m+1 = 5, new m = (4+1+8)/2 = 6, a(m) = 7 < 3? no, j = 6, m =(i+j)/2 = 11/2 = 5
        val emptyA = Array[Int]()
        // a(m) < ? 
        println(search(emptyA, 3))
    }
}

/* 
(a) What does this procedure do if the array a is not increasing (not sorted)?
Answer: 
It doesn't do anything useful. If x exists in the array, this method does not guarantee that it will be found.
This can happen if for example a(m) < x but a(m-1) = x for example, then we never consider index (m-1).

(b) What happens if N=0?
Answer:
The procedure immediately returns 0 since i<j is false for 0<0. This is okay behaviour in this case.

(c) Recall that Scala Ints can store only numbers in the range [−2^31..2^31). Consequently
Scala arrays are limited to a maximum size of 2^31 − 1 elements. Does the code work
correctly with very large arrays? If not, suggest a fix (which involves only Ints).
Answer:
This code works with very large arrays that have less than 2^31-1 elements. If for some 
odd reason we needed an array with more than this many elements, we would need dedicated memory
for itas this would be approximately 2.15 billion * 4 bytes = 8GB of memory for a single array.
An issue in this odd case could be that midpoint is calculated as (i+j)/2 which could cause integer 
overflow if (i+j) > 2^31-1 so it is better to calculate it as (i-j)/2+j to avoid adding large numbers.

*/
