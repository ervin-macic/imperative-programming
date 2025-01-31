object Fib{
    def fib(n : Int) : Int = {
        var a = 0
        var b = 1
        var i = n
        if(n == 0) return a
        if(n == 1) return b
        // Invariant I: a = fib(n - i) and b = fib(n - i + 1) and n >= i >= 0
        while(i > 0){
            val c = a + b
            a = b
            b = c
            // I: a = fib(n - i + 1) and b = fib(n - i + 2)
            i -= 1
            // a = fib(n - i) and b = fib(n - i + 1) and n >= i >= 0
        }
        // I and not (test condition) implies a = fib(n - 0) and b = fib(n - 1) and i = 0
        a
    }
    def main(args: Array[String]): Unit = {
        var n = args(0).toInt
        println(fib(n))
    }
}