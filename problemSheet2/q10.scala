object Polynomials{
    def eval(a: Array[Double], x: Double) : Double = {
        val n = a.size
        var y = a(n-1)
        // a(0) + x(a(1) + x(a(2)+x(... +x(a(n-1)))))
        // a(0) + x(a(1) + x(a(2)))
        for(i <- 1 to (n-1)) {
            y *= x
            y += a(n-i-1)
        }
        return y
    }
    def main(args: Array[String]) {
        var a = Array[Double](1,2,1)
        // x^2+2x+1 at x=3 so y=16
        println(eval(a, 2.00))
    }
}