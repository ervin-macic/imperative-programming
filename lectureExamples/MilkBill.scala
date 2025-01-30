object MilkBill{
    def main(args : Array[String]) : Unit = {
        val n = args.size
        var arr = args.map(_.toInt)
        var total = 0
        var i = 0
        // Invariant I: total = sum(arr[0..i)) && 0 <= i <= n
        while(i < n) {
            // I && i < n
            total += arr(i)
            // total = sum(arr[0..i+1)) && i < n
            i += 1
            // I
        }
        // I && i = n
        // total = sum(arr[0..n))
        println(total)
    }
}