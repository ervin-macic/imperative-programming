object InputOutput {
    def main(args : Array[String]) = {
        print("Input a number: ")
        var n = scala.io.StdIn.readInt()
        var arr = new Array[Int](n) // [0,0,0...,0] n times
        var check = Array[Int](3) // wtf, this is [3]??
        var wtf = Array[Int](7,5)
        var noType = Array(1,4.2,9,16)
        var arr2 = args.map(_.toInt)
        var charArr = Array[Char](65, 66, 67) // legal, conversion happens
        var xs = 1 to 10 // this is legal, 1 to 10 inclusive
        println(check(0)) // 3
        println(wtf(0))   // 7
        println(wtf(1))   // 5
        println(noType(3))// 9.0
        println(arr(0))   // 0
    }
    def runFunc(n : Int) : Int = {
        if (n == 5) then 7
        else 6
    }
    def matchCase(n : Int) : String = {
        n match {
            case 0 => "It's zero"
            case x if x % 2 == 0 => "It's even"
            case _ => "It's odd"
        }
    }
}