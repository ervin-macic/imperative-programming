object Q7{
    val a = Array(5,3,6,4,2,1)
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
    def QSort(l: Int, r: Int) : Unit = {
        // had to reassign l,r since they are treated as val in function body
        var left = l 
        var right = r
        while(right - left > 1) {
            val k = partition(left,right)
            QSort(left, k)
            left = k+1            
        }
    }
    def betterQSort(l: Int, r: Int) : Unit = {
        var left = l 
        var right = r 
        while(right - left > 1) {
            val k = partition(left, right)
            if(k - left < right - (k+1)) {
                betterQSort(left, k)
                left = k+1
            } else {
                betterQSort(k+1, right)
                right = k
            }
        }
    }
    def main(args: Array[String]) : Unit = {
        betterQSort(0, a.size)
        a.foreach(println)
    }
}
/*

(b)

The recursion may reach depth N (the size of the array) in the case where the subarray a[l..k) is all but one element.
For example, if the array is sorted in reverse order then:
val k = partition(left, right) will be N-1. This continues, and each recursive call reduces 
the array size by only 1. Since we're using recursion for the left subarray, the call stack will grow with each call eventaully
reaching depth N:
QSort(0,1)
...
QSort(0,N-1)
QSort(0,N)

(c)
Suggest how to change your program from part (a) that ensures the stack never gets deeper than ceil(log2(N))

Answer:
We can check which subarray is smaller and call QSort recursively on this part. This ensures that we always 
at least halve the size of the subarray we are QSorting, so we couldn't "stack up" more than ceil(log2(N)) 
calls on the call stack.
*/