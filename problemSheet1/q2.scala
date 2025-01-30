object Q2{
    def main(args : Array[String]) : Unit = {
        val n = args.size
        var arr = args.map(_.toInt)
        var total = 0
        var i = n
        // Invariant I: total = sum(arr[i..n)) && n >= i >= 0
        while(i > 0) {
            // I && i > 0
            i -= 1
            // total = sum(arr[(i+1)..n)) && n >= i >= 0
            total += arr(i)
            // I
        }
        // I && i = 0
        // total = sum(arr[0..n))
        // Variant was i since it decreases by 1 on integers, eventually must be <= 0 so while loop terminates
        println(total)
    }
}