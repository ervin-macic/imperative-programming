object Logarithm {
    def floorLog3x(x: Double) : Int = {
        var y = -1
        var pow3 = 1
        while(pow3 <= x) {
            pow3 *= 3
            y += 1
        }
        return y    
    }
    def main(args: Array[String]) : Unit = {
        for(i <- 1 to 30) {
            println(i + ": " + floorLog3x(i))
        }
    }
}