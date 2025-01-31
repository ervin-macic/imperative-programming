object Fib{
    def fib(n : Int) : Int = {
        // Precondition: n >= 0
        // Postcondition: a = fib(n)
        require(n >= 0, "Error: n must be a non-negative integer")
        
        // Invariant I: a = fib(n - i) && b = fib(n - i + 1) && n >= i >= 0
        var a = 0
        var b = 1
        var i = n // Variant: i
        /* Case split:
           If n == 0 then a = fib(0) = 0 && b = fib(1) = 1
           If n == 1 then a = fib(0) = 0 && b = fib(1) = 1
           else we have an additional condition n >= 2 and enter the loop below
        */
        if(n == 0) return a
        if(n == 1) return b
        // I && n >= 2
        while(i > 0) {
            // I && i > 0
            val c = a + b
            a = b
            b = c
            // a = fib(n - i + 1) && b = fib(n - i + 2)
            i -= 1
            // a = fib(n - i) && b = fib(n - i + 1) && n >= i >= 0
        }
        // I && !(test condition) => (a = fib(n - 0) && b = fib(n - 1) && i = 0)
        // Terminates: Since i is strictly decreasing and bounded below by 0
        a // a = fib(n)
    }
    def main(args: Array[String]): Unit = {
        var n = args(0).toInt
        println(fib(n))
    }
}