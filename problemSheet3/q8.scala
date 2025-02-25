object Q8{
    val a = Array(3,2,4,4,3,3,2,3,1)
    def bestPartition(l: Int, r: Int) : (Int,Int) = {
        val x = a(l) // pivot
        // Invariant: a[l..i) < pivot, and a[i..j) = pivot, and a[k..r) > pivot, 1 <= i < j <= k <= r
        var i = l; var j = l+1; var k = r
        while(j < k){
            if(a(i) < x) {
                i += 1
            } else if(a(i) > x) { 
                val t = a(k-1); a(k-1) = a(i); a(i) = t; k -= 1; 
            } else {
                if(a(i) != a(j)) {
                    val t = a(j); a(j) = a(i); a(i) = t;
                }
                j += 1;
            }
        }
        val t = a(l); a(l) = a(i); a(i) = t
        (i,j)
    }
    def ultimateQSort(l: Int, r: Int) : Unit = {
        if(r - l > 1) {
            val (i,j) = bestPartition(l,r)
            ultimateQSort(l,i)
            ultimateQSort(j,r)
        }
    }
    def main(args: Array[String]) : Unit = {
        ultimateQSort(0,9)
        a.foreach(println)
    }
}

/*
(a) What happens if Quicksort is used to sort an array in which all entries are identical?

Answer:
The partition function (the better version that moves each element at most once) would not do
a lot of work as it would return 0 on the first step immediately. It would do N steps of work to check
from right to left whether a(j-1) is ever "<" than x = a(0). 
Then we would try to sort the right part of N-1 identical elements. Again, the partition function would 
do N-1 steps, and so on. In total the runtime would be (N-1)+(N-2)+...+1 = O(N^2).

(b) What happens if Quicksort is used to sort an array in which several identical entries
appear, scattered throughout the array?

Answer:
The real issue is that this leads to unbalanced partitions. The right part will be much larger than the left as
all of the x==x elements will end up in the right half. Thus the quick sort might approach O(N^2) complexity
rather than O(NlogN).

(c) In sorting such an array, profiling reveals that the if statement in partition executes
its else clause very much more frequently than its then clause; why is this? Could the
performance problems be solved by replacing < by <= in the if condition?

Answer:
The "if(a(i) < x)" condition will rarely be true as there are many identical elements in the array and 
"x < x" is false. Thus the "else" condition will fire more often. The performance could not be solved by replacing
< by <= in the if condition as this would ruin the invariant, as a[l..k) < a(k) would not necessarily hold (i.e. the 
weaker inequality would hold now). All x==x elements end up in the left subarray now. 
The left subarray will now be much larger than the right one again causing O(N^2) runtime.
*/