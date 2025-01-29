object InputOutput {
    def main(args : Array[String]) = {
        var arr = args.map(_.toInt)
        var charArr = Array[Char](65, 66, 67) // legal, conversion happens
        var xs = 1 to 10 // this is legal
    }
    
}