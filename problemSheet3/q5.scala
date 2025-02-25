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
/*
The code developed in the lecture for Quicksort partition (Figure 2) always uses the leftmost
element a(l) of a sub-array as the pivot. For what inputs might this be a bad idea and what
advantage is gained by choosing the pivot at random? If the input were to consist of distinct
elements and the pivot were choose at random, what (approximately) is the expected size of
the larger segment that results from partitioning and how does this effect Quicksort?

Answers:
Choosing a(l) as the pivot might be bad if the array is sorted in reverse order. Then a(l) would be the largest 
element in the array leaving the part a(k..r) empty and thus not having balanced left and right subarrays for further
recursive sorting. Ideally, we would want these two subarrays to be as close in size as possible.
Choosing a pivot at random will on average achieve balance between the two subarrays. The expected size of the larger segment 
is 3N/4 (by the stick problem). So the complexity would be T(N) = T(N/4) + T(3N/4) + O(N) which, after fiddling around online
turns out to be O(NlogN) as expected.
*/
