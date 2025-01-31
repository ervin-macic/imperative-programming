object Fib{
    def fib(n: Int): Int = {
        if (n == 0) 0
        else if (n == 1) 1
        else fib(n - 1) + fib(n - 2)
    }
    def visualFib(n: Int, depth: Int): Int = {
        if (n == 0) {
            println("|" * depth + "fib(0)")
            println("|" * depth + "= 0")
            return 0
        } else if (n == 1) {
            println("|" * depth + "fib(1)")
            println("|" * depth + "= 1")
            return 1
        } else {
            println("|" * depth + "fib(" + n + ")")
            val left = visualFib(n - 1, depth + 1)
            val right = visualFib(n - 2, depth + 1)
            println("|" * depth + "= " + (left + right))
            left+right
        }
    }
    def main(args: Array[String]): Unit = {
        val n = args(0).toInt
        println(fib(n))
        visualFib(n, 0)
    }
}