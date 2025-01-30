object Q3{
    def main(args : Array[String]) : Unit = {
        val n = args.size
        var arr = args.map(_.toInt)
        var max = 0
        var i = 0
        // Invariant I: max = max(arr[0..i)) && 0 <= i <= n
        while(i < n) {
            // I && i < n
            if (max < arr(i)) {
                // I && i < n && max < arr(i)
                max = arr(i)
                // max = max(arr[0..i+1)) && i < n
            }
            i += 1
            // I
        }
        // I && i = n
        // max = max(arr[0..n))
        // The variant is i, since it is incremented in each iteration of the loop and the loop terminates when i = n
        println(total)
    }
}