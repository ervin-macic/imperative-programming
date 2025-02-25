object Q3 {
    def tooBig(y: BigInt, X: Int) : Boolean = {
        if(y > X) return true 
        return false
    }
    var maxFuncCalls = 0
    def guessX(X: Int) : Int = {
        require(X <= 1000 && 1 <= X)
        var funcCalls = 0
        var l = 1
        var r = 1000
        while(l < r) {
            val m = r+(l-r)/2
            if(tooBig(m, X)) {
                funcCalls += 1
                r = m-1
            } else {
                l = m
            }
        }
        if(X != l) {
            println("DIFFERENT")
            println(X)
        }
        if(maxFuncCalls < funcCalls) {
            maxFuncCalls = funcCalls
        }
        l
    }
    // (b)
    /* 
    Idea: set l = 2^floor(log_2(X)) and r = 2^(floor(log_2(X))+1)
    This first part takes (floor(log_2(X)))+1 steps
    After this is set, do binary search on this range (of length approximately X)
    This part takes log_2(X) steps
    In total, this program takes proportional to log_2(X) time
    */
    def guessXNoInfo(X: Int) : Int = {
        var l = 1
        var r = 2
        //if(!tooBig(1, X)) return 1
        while(!tooBig(r, X)) {
            r *= 2
            l *= 2
        }
        while(l < r) {
            val m = r+(l-r)/2
            if(tooBig(m, X)) {
                r = m-1
            } else {
                l = m
            }
        }
        if(X != l) {
            println("DIFFERENT")
            println(X)
        }
        l
    }
    def main(args: Array[String]) : Unit = {
        for(X <- 1 to 1000) {
            guessX(X)
        }
        // println(maxFuncCalls)
        // println("Next is part (b) with no upper bound on X")
        for(X <- 1 to 10000000) {
            guessXNoInfo(X)
        }
    }
}
/* 
(a) Maximal function calls is 9 = floor(log_2(1000))

(c)
Analyse the above method:
1st part: Finding l = 2^(floor(log_2(x))) <= x < 2^(floor(log_2(x)))+1) = r
Here r is multiplied by 2 starting from 2 until it reaches 2^(floor(log_2(x)))+1).
Looking at the exponent, r starts with 1 and each time calling tooBig for 1,2,...,floor(log_2(x))+1
so in total tooBig is called exactly floor(log_2(X))+1 times in this part.

2nd part:
Once l, r are set, by running binary search on this range, to determine X it will take less than
floor(log_2(X))+2 calls to tooBig.

In total, the number of calls to tooBig is bounded above by 2*floor(log_2(X))+2

This is bad. Not good enough for general small epsilon.

The only part that can be improved upon is the finding l,r initially. This can be done in the following manner:
Let eps be arbitrary > 0. Instead of multiplying by 2 each time, multiply by 2^(1/eps). This ensures we 
get 2^(m/eps) > X in eps*floor(log_2(X)) steps which gives the correct answer. If the desired eps = 1/4 for example, then we are 
multiplying by 16 each time. So we get 16^(m-1) < X < 16^m and now we can do binary search on 16^m-16^(m-1) elements
which gives log_2(15*2^(4(m-1))) = log_2(15) + 4(m-1) approx. = 4m (in general case this would be m/eps). Now 
from the first part we know m < eps*log_2(X)+2 hence the binary search still takes m/eps < log_2(X)+2 steps
hence in total combining both steps we get (1+eps)log_2(X)+constants
*/