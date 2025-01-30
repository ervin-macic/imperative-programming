object MilkBill{
    def main(args : Array[String]) : Unit = {
        val len = args.size
        var arr = args.map(_.toInt)
        var sum = 0
        var i = 0
        while(i < len) {
            sum += arr(i)
            i += 1
        }
        print(sum)
    }
}