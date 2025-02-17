
object Searching{
/** Does patt appear as a substring of line? */
def search(patt: Array[Char], line: Array[Char]) : Boolean = {
    val K = patt.size; val N = line.size
    // Invariant: I: found = (line[i..i+K) = patt[0..K) for
    // some i in [0..j)) and 0 <= j <= N-K
    var j = 0; var found = false
    while(j <= N-K && !found){
        // set found if line[j..j+K) = patt[0..K)
        // Invariant: line[j..j+k) = patt[0..k)
        var k = 0
        while(k<K && line(j+k)==patt(k)) k = k+1
        found = (k>=K)
        j = j+1
    }
    // I && (j=N-K+1 || found)
    // found = ( line[i..i+K) = patt[0..K) for some i in [0..N-K+1) )
    found
}
}

/*
(a) On line 6, replace false by true.
Answer:
This would always return true inconsiderate of whether
patt actually appears in the line.
(b) On line 7, replace <= by <.
Answer:
This would not check the last possible substring that could be 
equal to our pattern, namely it wouldn't check line[N-K..N) == patt[0..K)
(c) On line 7, replace N-K by N-K+1.
Answer:
This would cause an index out of bounds error since if line[N-K+1..N) = patt[0..K-1) then
the program would attempt to compare line[N] with patt[K-1] which would be out of bounds 
since line is of size N.
(d) On line 10, replace 0 by 1.
Answer:
This would already assume that the first character of line is the same 
as the first character of patt, i.e. we would get false positives.
(e) On line 11, replace < by <=.
Answer:
This would also potentially cause an index out of bounds error for patt
since we would potentially compare a value in line with patt[K] which is 
out of bounds for patt.
(f) On line 12, replace == by >=.
Answer:
Nothing changes in this case (assuming this is the only change that has 
been made in the code).
*/