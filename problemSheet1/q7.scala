object Q7{
    def quotAndRem(x: Int, y: Int) : Unit = {
        // Preconditions: x must be a non-negative integer and y must be a positive integer
        require(x >= 0 && y > 0, "Error: x must be a non-negative integer and y must be a positive integer")
        var r = x
        var q = 0
        // Variant: r
        // Invariant: (x = q * y + r) && (0 <= r <= x)
        // Invariant holds before the loop since (x = 0 * y + x) && (0 <= r <= x)
        while(r >= y) {
            // I && r >= y >= 0 && 0 <= r <= x
            r -= y
            // x = q * y + (r+y) && 0 = (y-y) <= r <= x
            q += 1
            // (x = (q-1) * y + (r+y) => x = q * y + r) 
            // && (0 <= r <= x) 
            // => I
        }
        // Termintation: Loop will terminate since r is strictly decreasing (y is a positive integer) and bounded below by 0
        // (I && (0 <= r < y)) => (x = q * y + r && 0 <= r < y) => (r = x % y && q = x / y) by definition of % and /
        println("Quotient: " + q + " Remainder: " + r)
    }
    def main(args: Array[String]): Unit = {
        var x = args(0).toInt
        var y = args(1).toInt
        quotAndRem(x, y)
    }
}