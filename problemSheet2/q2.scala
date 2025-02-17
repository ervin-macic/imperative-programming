// Consider the program
object SideEffects{
    var x = 3; var y = 5
    def nasty(x: Int) : Int = { y = 1; 2 * x }
    def main(args: Array[String]) = println(nasty(x) + y)
}
/*
First thing to note is that x and y are globally defined.
This means any changes to them inside the SideEffects body are 
propagated. So in the function body of nasty, y=1 will change the
value of the global variable y from 5 to 1 and return 2*x which is 
6. Thus println(nasty(x)+y) should print 6+1=7.

After running the code, I confirmed my answer.

After reading the solution, it's good to mention that "+" is left
associative so nasty(x) runs before y is touched.
*/