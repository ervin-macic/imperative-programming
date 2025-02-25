object Main{
  
// A class of objects to represent a set

/*
• Do you use a dummy header node?
Yes, a dummy header node is necessary to be able to represent an empty 
set.
• Can your linked list store the same integer several times, or do you
avoid repetitions?
I avoid repetition since this should represent a set.
• Do you store the elements of the set in increasing order?
If I use additional memory, I don't need to.
• Do you include anything else in your state, for efficiency?
*/

/**
Abs:
DTI:  
*/
class IntSet{
  // State: S : P(Int) (where "P" represents power set)
  private var len: Int = 0
  // The following lines just define some aliases, so we can subsequently
  // write "Node" rather than "IntSet.Node".
  private type Node = IntSet.Node
  // Constructor
  private def Node(value: Int, next: Node) = new IntSet.Node(value, next)

  // Init: S = {}
  private var theSet : Node = null // or however empty set is represented

  /** Convert the set to a string.
    * (The given implementation is not sufficient.) */
  override def toString : String = {
    var st = "{"
    var curr: Node = theSet
    while(curr != null) {
      val nextNode = curr.next
      if(nextNode != null) {
        st += curr.value.toString + ", "
      } else {
        st += curr.value.toString
      }
      curr = nextNode
    }
    st+"}"
  }

  /** Check whether the set is empty
    * Post: S = S_0 && returns (#S == 0) */
  private def isEmpty = len == 0
  /** Length of the list
    * Post: S = S_0 && returns #S */
  def size : Int = len

  /** Find the node before where x should be inserted.
  * post: (prev, curr) where prev is the last node <= x
  * and curr is the node after prev */
  private def find(x: Int) : (Node, Node) = {
    if(isEmpty) return (null, null)
    var prev: Node = null
    var curr: Node = theSet
    // Invariant: prev <= x
    // for all n1 in L(list, n), n1.value != x
    while(curr != null && curr.value < x) {
      prev = curr
      curr = curr.next
    }
    // curr == null or curr.value >= x here
    (prev, curr)
  }

  /** Add element x to the set
    * Post: S = S_0 U {x} */
  def add(x: Int) : Unit = {
    if(isEmpty) { 
      theSet = Node(x, null)
      len += 1
    } else {
      val (prev, curr) = find(x)
      if(curr == null || curr.value != x) { // only insert if x is not present
        val newNode = new Node(x, curr)
        if(prev != null) {
          prev.next = newNode
        } else { // otherwise insert at head
          theSet = newNode
        }
      }
      len += 1
    }
  }

  /** Does the set contain x?
    * Post: S = S_0 && returns (x in S) */
  def contains(x: Int) : Boolean = {
    val (prev, curr) = find(x)
    return (curr != null && curr.value == x)
  }

  /** Return any member of the set.  (This is comparable to the operation
    * "head" on scala.collection.mutable.Set, but we'll use a name that does
    * not suggest a particular order.)
    * Pre: S != {}
    * Post: S = S_0 && returns e s.t. e in S */
  def any : Int = {
    require(!isEmpty, "The set is empty; you're silly for trying this")
    return theSet.value
  }
  /** Compare two sets for equality
    * Post: return true if sets have identical elements */
  def compareSets(s: IntSet) : Boolean = {
    if(size != s.size) return false
    var thisSet: Node = theSet
    var thatSet: Node = s.theSet
    while(thisSet != null) {
      if(thisSet.value != thatSet.value) return false
      thisSet = thisSet.next 
      thatSet = thatSet.next
    }
    return true
  }
  /** Does this equal that?
    * Post: S = S_0 && returns that.S = S */
  override def equals(that: Any) : Boolean = that match {
    case s: IntSet => compareSets(s)
    case _ => false
  }

  /** Remove x from the set; result says whether x was in the set initially
    * Post S = S_0 - {x} && returns (x in S_0) */
  def remove(x: Int) : Boolean = {
    val (prev, curr) = find(x)
    if(isEmpty || curr == null || curr.value != x) return false 
    // curr >= x, (prev < x or prev == null)
    if(prev == null) {
      theSet = theSet.next
    } else {
      prev.next = curr.next
    }
    len = len - 1
    return true
  }
    
  /** Test whether this is a subset of that.
    * Post S = S_0 && returns S subset-of that.S */
  def subsetOf(that: IntSet) : Boolean = {
    var curr: Node = theSet
    while(curr != null) {
      if(!that.contains(curr.value)) return false
      curr = curr.next
    }
    return true
  }
  
  def betterSubsetOf(that: IntSet) : Boolean = {
    var thatSet: Node = that.theSet
    var thisSet: Node = theSet
    while(thatSet != null && thisSet != null) {
      if(thisSet.value < thatSet.value) return false
      if(thatSet.value != thisSet.value) {
        thatSet = thatSet.next
      } else {
        thisSet = thisSet.next
      }
    }
    if(thisSet == null) return true 
    return false
  }

  // ----- optional parts below here -----

  /** return union of this and that.  
    * Post: S = S_0 && returns res s.t. res.S = this U that.S */
  def union(that: IntSet) : IntSet = {
    var union: Node = theSet 
    var thatSet: Node = that.theSet 
    var thisSet: Node = theSet 
    while(thatSet != null && thisSet != null) {

    }
    return null
  }

  /** return intersection of this and that.  
    * Post: S = S_0 && returns res s.t. res.S = this intersect that.S */
  def intersect(that: IntSet) : IntSet = ???

  /** map
    * Post: S = S_0 && returns res s.t. res.S = {f(x) | x <- S} */
  def map(f: Int => Int) : IntSet = ???

  /** filter
    * Post: S = S_0 && returns res s.t. res.S = {x | x <- S && p(x)} */
  def filter(p : Int => Boolean) : IntSet = ???
}


// The companion object
object IntSet{
  /** The type of nodes defined in the linked list */
  private class Node(var value: Int, var next: Node)

  /** Factory method for sets.
    * This will allow us to write, for example, IntSet(3,5,1) to
    * create a new set containing 3, 5, 1 -- once we have defined 
    * the main constructor and the add operation. 
    * post: returns res s.t. res.S = {x1, x2,...,xn}
    *       where xs = [x1, x2,...,xn] */
  def apply(xs: Int*) : IntSet = {
    val s = new IntSet; for(x <- xs) s.add(x); s
  }
}
def main(args: Array[String]) : Unit = {
  val set = IntSet(10,2,4,2,5,2)
  println(set)
  set.remove(10)
  println(set)
  set.add(10)
  println(set)
  println(set.contains(17))
  println(set.contains(2))
  set.remove(2)
  set.remove(4)
  set.remove(5)
  set.remove(10)
  println(set)
  set.add(10)
  println(set)
  set.add(20)
  set.add(30)
  set.add(40)
  val set2 = IntSet(10,30)
  println(set.subsetOf(set2))
  println(set2.subsetOf(set))

}
}