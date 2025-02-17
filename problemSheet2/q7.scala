// This function returns true if a (positive) integer has 3 digits in decimal
val threedigits:(Int=>Boolean) = i => {i >= 100 && i<1000}

def exists(p : Int => Boolean, N : Int): Boolean = {
    // Pre: N integer, p predicate
    // Post: Truth value of "there exists i in [0..N) such that p(i) is true"
    // Restated: OR of p(i) for i in [0..N)
    // Invariant: 0 <= i < N && answer = OR of p(j) for all j in [0..i)
    // Init: i == 0 hence answer = OR of p(j) for all j in [0..0) which is empty set so this is true
    var i = 0
    while(i < N) {
        // I
        if(p(i)) // check i
            return true
        i += 1
        // I for (i+1) holds
    }
    // I && not(while loop test) = I && i >= N
    // i increases by 1 in each loop iteration hence i=N on exit
    // Hence answer = OR of p(j) for all j in [0..N) which is the Post condition.
    return false
}