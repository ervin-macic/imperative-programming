object Q4{
    def isort(A: Array[Int]) : Unit = {
        for (j <- 0 until A.length-1) { 
            var key = A(j+1)
            // Insert A(j+1) into the sorted sequence A[0.(j+1)).
            var l = 0
            var r = j+1
            // I: A[0..l) < key < A[r..(j+1))
            while(l < r) {
                if(r - l == 1) {
                    if(A(l) >= key) {
                        r = l
                    } else {
                        l = r
                    }
                } else {
                    val m = r+(l-r)/2
                    if(A(m) < key) {
                        l = m+1 
                    } else {
                        r = m
                    }
                }
            }
            // here r == l guaranteed
            /* println("For this array:")
            A.slice(0,j+1).foreach(println)
            println("Index leftmost index with value >=", key, "is", l)*/
            for(i <- j to l by -1) {
                A(i+1) = A(i)
            }
            A(l) = key
        }
    }
    def main(args: Array[String]) : Unit = {
        var A = Array(5,2,4,6,1,3)
        isort(A)
        for(i <- 0 to A.length-1) {
            println(A(i))
        }
    }
}
/*
As N becomes large, what is the order of growth of the number of comparisons of elements of a?

In insertion sort, we compare A(j+1) with A[0..j]. Using the above binary search method, we 
compare A(j+1) with approximately log_2(j) elements. So since we do this for j in [0..N-1)
we will get approximately log_2(1) + log_2(2) + ... + log_2(N-1) comparisons which is 
approximately log_2(N!) which is theta(NlogN) (proved in DAA).

How does the overall running time grow as a function of N?
The real bottleneck in this algorithm is the shifting part which takes O(j) for each j.
Summing over j from 1 to N we get O(N^2).
i.e. T(N) = O(NlogN) + O(N^2) = O(N^2)
*/