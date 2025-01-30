object Factorial{
  /** Calculate factorial of n
    * Pre: n >= 0
    * Post: returns n! */
  def fact(n: Int) : BigInt = {
    require(n>=0)
    if(n==0) 1 else fact(n-1)*n
  }

  // Main method
  def main(args: Array[String]) : Unit = { 
    
    val n = args(0).toInt
    if(n>=0){
      val f = fact(n)
      println("The factorial of "+n+" is "+f)
    }
    else println("Sorry, negative numbers aren't allowed")
  }
}

