object Main{
  
// A class of objects to represent a set

/*
• Do you use a dummy header node?
No, a dummy header node is not necessary to be able to represent an empty 
set.
• Can your linked list store the same integer several times, or do you
avoid repetitions?
I avoid repetition since this should represent a set.
• Do you store the elements of the set in increasing order?
If I use additional memory, I don't need to. But I do store them in increasing order
to ease the operations like union, find etc.
• Do you include anything else in your state, for efficiency?
I include an integer len which keeps track of the number of elements in the set.
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

  /** Convert the set to a string. */
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
  * pre: set != {}
  * post: (prev, curr) where prev is the last node <= x, curr is either null or the first 
  element greater than x. and curr is the node after prev */
  private def find(x: Int) : (Node, Node) = {
    require(!isEmpty, "The set is empty!")
    var prev: Node = null
    var curr: Node = theSet
    // Invariant: prev < x
    // for all n1 in L(list, n), n1.value != x
    while(curr != null && curr.value < x) {
      prev = curr
      curr = curr.next
    }
    // (curr == null xor curr.value >= x) && prev < x
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
        if(prev != null) { // shouldn't be inserted at the front of the list
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
    if(isEmpty) return false
    val (prev, curr) = find(x)
    return (curr != null && curr.value == x)
  }

  /** Return any member of the set. 
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

  /** Creates a deep copy */
  def copyFrom(that: IntSet) : Unit = {
    require(size >= that.size, "Can't copy a larger set into a smaller one")
    var thatCurr: Node = that.theSet 
    var thisCurr: Node = theSet 
    var prev: Node = null
    while (thatCurr != null) {
      thisCurr.value = thatCurr.value 
      prev = thisCurr
      thisCurr = thisCurr.next
      thatCurr = thatCurr.next
    }
    if (prev != null) prev.next = null
  }

def union(that: IntSet): IntSet = {
  if (isEmpty) return that
  if (that.isEmpty) return this
  val union = new IntSet
  var currThisSet: Node = theSet
  var currThatSet: Node = that.theSet
  var lastUnionNode: Node = null
  var unionEmpty = true
  def appendToEnd(value: Int): Unit = {
    val newNode = new Node(value, null)
    if (unionEmpty) {
      union.theSet = newNode
      unionEmpty = false
    } else {
      lastUnionNode.next = newNode
    }
    lastUnionNode = newNode
  }
  // Continue while both lists have elements
  while (currThisSet != null && currThatSet != null) {
    if (currThisSet.value < currThatSet.value) {
      appendToEnd(currThisSet.value)
      currThisSet = currThisSet.next
    } else if (currThisSet.value > currThatSet.value) {
      appendToEnd(currThatSet.value)
      currThatSet = currThatSet.next
    } else { // Equal values
      appendToEnd(currThisSet.value)
      currThisSet = currThisSet.next
      currThatSet = currThatSet.next
    }
  }
  // Process remaining elements if there are any
  while (currThisSet != null) {
    appendToEnd(currThisSet.value)
    currThisSet = currThisSet.next
  }
  while (currThatSet != null) {
    appendToEnd(currThatSet.value)
    currThatSet = currThatSet.next
  }
  union
}

  /** return intersection of this and that.  
    * Post: S = S_0 && returns res s.t. res.S = this intersect that.S */
  def intersect(that: IntSet) : IntSet = ???

  /** map
    * Post: S = S_0 && returns res s.t. res.S = {f(x) | x <- S} */
  def map(f: Int => Int): IntSet = {
    if(isEmpty) return new IntSet
    var newSet = new IntSet
    var lastNode: Node = null
    var currThis: Node = theSet
    while(currThis != null) {
      val newNode = new Node(f(currThis.value), null)
      if(newSet.isEmpty) {
        newSet.theSet = newNode
      } else {
        lastNode.next = newNode
      }

      lastNode = newNode
      currThis = currThis.next
    }
    return newSet
  }

  /** filter
    * Post: S = S_0 && returns res s.t. res.S = {x | x <- S && p(x)} */
  def filter(p : Int => Boolean) : IntSet = {
    if(isEmpty) return new IntSet
    var newSet = new IntSet
    var lastNode: Node = null
    var currThis: Node = theSet
    while(currThis != null) {
      if(p(currThis.value)) {
        val newNode = new Node(currThis.value, null)
        if(newSet.isEmpty) {
          newSet.theSet = newNode
        } else {
          lastNode.next = newNode
        }
        lastNode = newNode
      }
      currThis = currThis.next
    }
    return newSet
  }
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
  println(set.betterSubsetOf(set2))
  println(set2.betterSubsetOf(set))
  println(set)
  println(set2)
  println(set.union(set2))
}
}