import scala.Int.MinValue
object Q9{
    def hits(a: Array[Int]): Int = {
        if(a.length == 0) return 0
        var hits = 0
        var i = 0
        var currentMax = MinValue
        // define max(a[0..i)) = max(a[0..i-1)) if a[i] <= max(a[0..i-1)) else a[i]
        // define max(a[0..0)) = minimum Int value
        // define |{j | 0 <= j < i && a[j] > max(a([0..j))}| for i = 0 as 0
        // Invariant I: currentMax = max(a[0..i)) && hits = |{j | 0 <= j < i && a[j] > max(a([0..j))}| && 0 <= i <= a.length
        // Holds at the start of the loop since a[0..0) =  and hits = 1
        // Variant: a.length - i
        while(i < a.length) {
            // I && i < a.length
            if(i == 0) {
                hits += 1 // hits = |{j | 0 <= j < 1 && a[j] > max(a[0..j))}| = 1
                currentMax = a(0) // max(a[0..i+1)) = a[0]
            } else {
                if(a(i) > currentMax) {
                    hits += 1 // hits = |{j | 0 <= j < (i+1) && a[j] > max(a([0..j))}| = |{j | 0 <= j < i && a[j] > max(a[0..j))}| + 1 since a[i] > max(a[0..i))
                    currentMax = a(i) // currentMax = max(a[0..i+1))
                }
            }
            i += 1 // i <= a.length
            // I holds for next iteration when case splitting above conditional expression
        }
        // Terminates: a.length - i is strictly decreasing and is bounded below by 0
        // (I && i = a.length) => hits = |{j | 0 <= j < a.length && a[j] > max(a[0..j))}| which is our Postcondition
        return hits
    }
    def main(args: Array[String]): Unit = {
        println(hits(Array(10, 1, 3, 7, 9, 9, 10, 11, 12, 13, 14, 15)))
    }
}