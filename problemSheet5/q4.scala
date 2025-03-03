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

class LinkedListHeaderBook extends Book{
  private var list = new LinkedListHeaderBook.Node("?", "?", null)
  // list represents the mapping composed of (n.name -> n.number)
  // maplets, when n is a node reached by following 1 or more 
  // next references.

  /** Return the node before the one containing name.
    * Post: book = book_0 && returns n s.t. n in L(list) &&
    * (n.next.name=name or n.next=null if no such Node exists)*/
  private def find(name:String) : LinkedListHeaderBook.Node = {
    var n = list
    // Invariant: name does not appear in the nodes up to and  
    // including n; i.e., 
    // for all n1 in L(list.next, n.next), n1.name != name
    while(n.next != null && n.next.name != name) n = n.next
    n
  }

  /** Is name in the book? */
  def isInBook(name: String): Boolean = find(name).next != null

  /** Return the number stored against name */
  def recall(name: String) : String = {
    val prevNode = find(name); 
    assert(prevNode.next != null); 
    var foundNode = prevNode.next 
    var number = foundNode.number
    prevNode.next = foundNode.next
    foundNode.next = list.next 
    list.next = foundNode
    return number
  }

  /** Add the maplet name -> number to the mapping */
  def store(name: String, number: String): Unit = {
    val n = find(name)
    if(n.next == null){ // store new info in current list header
      list.name = name; list.number = number
      list = new LinkedListHeaderBook.Node("?", "?", list)
    }
    else n.next.number = number
  }

  /** Delete the number stored against name (if it exists); 
    * return true if the name existed. */
  def delete(name: String) : Boolean = {
    val n = find(name)
    if(n.next != null){ n.next = n.next.next; true }
    else false
  }
}