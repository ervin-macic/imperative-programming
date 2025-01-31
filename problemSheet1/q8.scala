object GCD{
    def gcd(m: Int, n: Int): Int = {
        if(n == 0) m else gcd(n, m % n)
    }
    def gcd2(m: Int, n: Int): Int = {
        var a = m 
        var b = n
        while(b > 0) {
            if(a > b) a = a - b
            else b = b - a
        }
        return a
    }
    def min(a: Int, b: Int): Int = {
        if(a < b) a else b
    }
    def bezout(m: Int, n: Int): Unit = {
        // Precondition: m and n are positive integers
        require(m > 0 && n > 0, "Error: m and n must be positive integers.")
        var a = m 
        var b = n
        var xa = 1
        var ya = 0
        var xb = 0
        var yb = 1
        // Variant: a+b reevaluated in each iteration
        // Invariant: a = m * xa + n * ya && b = m * xb + n * yb && a >= 0 && b >= 0 && gcd(a,b) = gcd(m,n)
        while(min(a,b) > 0) {
            // I && b > 0
            if(a > b) { 
                a = a - b // Since a > b, a >= 0 is true
                // gcd(a-b,b) = gcd(a,b) = gcd(m,n) is true
                xa = xa - xb
                ya = ya - yb
                // a - b ?==? m * (xa-xb) + n * (ya-yb) = (m * xa + n * ya) - (m * xb + n * yb) = a - b so this is true
            } else {
                b = b - a // Since b >= a, b >= 0 is true
                // gcd(a,b-a) = gcd(a,b) = gcd(m,n) is true
                xb = xb - xa
                yb = yb - ya
                // b - a ?==? m * (xb-xa) + n * (yb-ya) = (m * xb + n * yb) - (m * xa + n * ya) = b - a so this is true
            }
            // Union over cases the invariant holds 
            // I
        }
        // Termination: At each loop iteration, (a+b) decreases by min(a,b) >= 1 and is bounded below by 0
        // Without loss of generality, assume b = 0 here since it is symmetric (swap a and b and nothing changes)
        // (I && b <= 0) => b = 0
        // gcd(a,b) = gcd(m,n) && b = 0 => Postcondition: a = gcd(m,n) && a = m * xa + n * ya
        println("GCD of " + m + " and " + n + " is " + a + ".")
        val xaStr = if (xa < 0) s"($xa)" else xa.toString
        val yaStr = if (ya < 0) s"($ya)" else ya.toString
        println("Bezout's identity holds: " + m + " * " + xaStr + " + " + n + " * " + yaStr + " = " + a)
    }

    def main(args: Array[String]): Unit = {
        bezout(11,13)
    }
}