import scala.collection.mutable.ArrayBuffer
object Reciprocals{
    def calculateM(r: Double) : Int = {
        return math.ceil(1.0/r).toInt
    }
    def getFractions(p: Int, q: Int) : Unit = {
        require(0 < p && p < q, "0 < p < q must hold")
        var r = p*1.0/q
        var m = calculateM(r)
        var d = ArrayBuffer(m)
        while(1.0/m < r - 1e-9) {
            r -= 1.0/m // new p = mp - q < p?, this is equivalent to
            // p(ceil(q/p) - 1) < p* (q/p) = q as ceil(x)-1 < x always.
            // Thus p always strictly decreases
            // As p is a positive integer, this implies this loop terminates
            m = calculateM(r)
            d += m
        }
        print(p + "/" + q + " =")
        for(i <- 0 until d.size) {
            if(i == 0)
                print(" 1/" + d(0))
            else
                print(" + 1/" + d(i))
        }
        print("\n")
        // Array d is strictly increasing proof:
        // Since we always choose the largest possible reciprocal
        // each subsequent reciprocal in the summand must be smaller
        // i.e. the denominator must be larger otherwise we could have chosen
        // a larger denominator for an earlier reciprocal as it would "fit" earlier.
    }
    def main(args: Array[String]) : Unit = {
        getFractions(2,35)
    }

}