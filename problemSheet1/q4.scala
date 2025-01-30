object Milk {
    def findSum(a: Array[Int]): Int = {
        val n = a.size
        // I mean technically if no args are passed it returns 0 correctly so I wouldn't require n > 0
        var total = 0 // If the bill is huge, might make sense for total to be a long datatype
        var i = 0
        while (i < n) {
            total += a(i)
            i += 1
        }
        total
    }

    def main(args: Array[String]) = {
        val n = args.size
        // check whether n is at least 1 and print an error message if not
        val a = new Array[Int](n) // redundant due to below comment
        for (i <- 0 until n) a(i) = args(i).toInt // better: args.map(_.toInt), then no need to declare a new array in line 16
        println(findSum(a)) // might be nice to add some gui text like println("The total bill is: " + findSum(a))
    }
}

object BetterMilk {
    def findSum(a: Array[Int]): Int = {
        val n = a.size
        var total = 0L
        var i = 0
        while (i < n) {
            total += a(i)
            i += 1
        }
        total
    }

    def main(args: Array[String]) = {
        val n = args.size
        require(n > 0, "Error: At least one argument is required.")
        val a = args.map(_.toInt)
        println("The total bill is: " + findSum(a))
    }
}