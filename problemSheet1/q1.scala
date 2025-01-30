/* 
Write short Scala functions which use just standard integer arithmetic
(* + - /) and, on integer input:
(a) square the input;
(b) compute the remainder on dividing the input by 3; and
(c) find the largest perfect square no more than the input.
How would you convince your tutor that you have run your functions and that they give the
correct answers?
*/
object Q1{
    def square(n: Int) : Int = {
        n*n
    }

    def remainder(n: Int) : Int = {
        n%3
    }

    def largestPerfectSquare(n: Int) : Int = {
        var i = 0
        while(i*i <= n){
            i += 1
        }
        (i-1)*(i-1)
    }

    def main(args: Array[String]) : Unit = {
        val n = args(0).toInt
        println("The square of "+n+": "+square(n))
        println("The remainder of "+n+" when divided by 3: "+remainder(n))
        println("The largest perfect square less than or equal to "+n+": "+largestPerfectSquare(n))
    }
}
