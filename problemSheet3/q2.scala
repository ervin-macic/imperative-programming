object Q2{
    def sqrt(y: Int): Int = {
    require(y >= 0)
    if (y <= 1) return y
    var l = 0
    var r = y
    // Invariant: l^2 <= y < r^2 and 0 <= l < r
    while (l + 1 < r) {
        if (r - l == 2) {
            // in l^2 <= y < (l+2)^2 case, decide between (l,l+1) and (l+1,l+2)
            // necessary since ternary gets stuck when r-l small
            val mid = l+1
            if (mid * mid <= y) {
                l = mid
            } else {
                r = mid
            }
        } else {
            // really clean conceptually, invariant works like a charm but unfortunately gets stuck when r-l==2
            val m1 = l + (r - l) / 3
            val m2 = r - (r - l) / 3
            if (m2 <= y/m2) {
                l = m2
            } else if (y/m1 < m1) {
                r = m1
            } else {
                l = m1
                r = m2
            }
            // I is maintained in above l,r settings
        }
    }
    // Here r > l and r <= l+1 hence r = l+1 and as I is maintaned, we have l^2 <= y < (l+1)^2 so we're done
    l
}
    def lectureSqrt(y: Int) : Int = {
        require(y >= 0)
        // Deal with y=0 or 1
        if (y <= 1) return y
        // Invariant I: a^2 <= y < b^2 and 0 <= a < b
        var a = 0; var b = y
        while(a+1 < b){
            val m = a+(b-a)/2 // a < m < b
            if(m <= y/m) a = m else b = m
        }
        // a+1 >= b odnosno b = a+1 onda
        // a^2 <= y < (a+1)^2
        a
    }

    def main(args: Array[String]) : Unit = {
        for(i <- 0 to Int.MaxValue) {
            if(lectureSqrt(i) != sqrt(i)) {
                println("DIFFERENT")
                println(i)
                println(lectureSqrt(i))
                println(sqrt(i))
            }
        }
    }
}
/*
(b) When the size of a search interval gets small then does the ternary split ever produce
an empty sub-interval? If so, is there any potential for the next iteration of the loop to
have an empty interval to work with?

Answer: Maybe for some other implementation of the ternary search. Mine always leaves (l,r) not empty.
Maybe I don't understand what sub-interval means here.
If it refers to [l,r] then certainly at all times in the loop this contains at least three elements as r > l+1.
If it refers to [l,m1] or [m2,r] then again in my implementation it always contains at least two elements:
(r+2l)/3 >= (3l+1)/3 = l, (2r+l)/3 <= (3r-1)/3 = r-1 (using loop condition)

(c) I ran it from 0 to Int.MaxValue and it worked, i.e. was the same as the lecture sqrt function (although I had to
fix overflows in the lecture version in the m = (a+b)/2 and m*m <= y part).

*/