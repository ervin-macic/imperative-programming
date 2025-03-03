// The interface to the phone book

/** The state of a phone book, mapping names (Strings) to 
  * numbers (also Strings).
  * State: book : String -|-> String
  * Init:  book = {} */
trait Book{
  /** Add the maplet name -> number to the mapping.
    * POST: book = book_0 (+) {name -> number} */
  def store(name: String, number: String) : Unit

  /** Return the number stored against name.
   * PRE: name in dom book
   * POST book = book_0 && returns book(name) */
  def recall(name: String) : String

  /** Does name appear in the book?
    * POST: book = book_0 && returns name in dom book */
  def isInBook(name: String) : Boolean
}

// Representing the phone book using a linked list with a dummy header

class SortedLinkedListHeaderBook extends Book{
  /*
  DTI: (L(list) is finite) && (n1.name < n2.name whenever n1 in L(n2, null))
  ABS: book = {n.name -> n.number | n in L(list.next, null)}
  */
  private var list = new SortedLinkedListHeaderBook.Node("?", "?", null)
  // list represents the mapping composed of (n.name -> n.number)
  // maplets, when n is a node reached by following 1 or more 
  // next references.

  /** Return the node before the one containing name.
    * Post: book = book_0 && returns n s.t. n in L(list) &&
    * (n.next.name=name or n.next=null if no such Node exists)*/
  private def find(name:String) : SortedLinkedListHeaderBook.Node = {
    var n = list
    // I: for all n1 in L(list.next, n.next), n1.name != name
    while(n.next != null && n.next.name != name) n = n.next
    n
  }

  // Modification on find, this either returns the last node of the list or 
  // the node which has n.next.name >= name, which corresponds to the node 
  // after which we should add a new maplet with name (if the names differ)
  private def lowerBoundFind(name: String) : SortedLinkedListHeaderBook.Node = {
    var n = list 
    while(n.next != null && n.next.name < name) n = n.next
    // certainly either n.next == null or n.next.name >= name or both
    return n 
  }

  /** Is name in the book? */
  def isInBook(name: String): Boolean = find(name).next != null

  /** Return the number stored against name */
  def recall(name: String) : String = {
    val n = find(name); assert(n.next != null); n.next.number
  }

  /** Add the maplet name -> number to the mapping */
  def store(name: String, number: String): Unit = {
    val n = lowerBoundFind(name)
    if (n.next != null && n.next.name == name) {
      // Update existing name's number
      n.next.number = number
    } else {
      // Insert new entry
      val newNode = new SortedLinkedListHeaderBook.Node(name, number, n.next)
      n.next = newNode
    }
  }

  /** Delete the number stored against name (if it exists); 
    * return true if the name existed. */
  def delete(name: String): Boolean = {
  val n = lowerBoundFind(name)
    if (n.next != null && n.next.name == name) {
      n.next = n.next.next
      return true
    }
    return false
  }
}

// Companion object
object SortedLinkedListHeaderBook{
  private class Node(var name:String, var number:String, var next:Node)
}