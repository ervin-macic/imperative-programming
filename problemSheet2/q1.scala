// What is the effect of the following procedure?
def swap(x: Int, y: Int) = { val t = x; x = y; y = t }
/*
Arguments of functions are not mutable inside the function body except for arrays.
So in effect, x,y are val here so doing x=y causes an error.

After running the code, I get:
q1.scala:2: error: reassignment to val
def swap(x: Int, y: Int) = { val t = x; x = y; y = t }
*/
//What is the effect of the following procedure?
def swapEntries(a: Array[Int], i: Int, j: Int) = {
val t = a(i); a(i) = a(j); a(j) = t
}
/*
This swaps the values inside the ith and jth elements of an array.
This is possible since arrays are considered vars inside function body.

I forgot to consider whether i and j are in bounds of a. If they aren't
then indexing a at these indices will cause an index out of bounds error.
*/
def main(args: Array[String]): Unit = {
    var a = 5
    var b = 10
    println(s"Before swap: a = $a, b = $b")
    swap(a, b)
    println(s"After swap: a = $a, b = $b")
}

