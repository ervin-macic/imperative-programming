/* A double-ended queue of data of type A 
* state: dq : seq A
* init: dq = [] */
trait Deque[A]{
    /** Is the deque empty?
    * post: dq = dq0 ∧ returns dq = [] */
    def isEmpty: Boolean

    /** Add x to the start of the queue.
    * post: dq = [x] ++ dq0 */
    def addLeft(x: A)

    /** Get and remove element from the start of the queue.
    * pre: dq =/= []
    * post: dq = tail dq0 ∧ returns head dq0 */
    def getLeft(): A

    /* Add x to the end of the queue.
    * post: dq = dq0 ++ [x] */
    def addRight(x: A)

    /** Get and remove element from the end of the queue.
    * pre: dq =/= []
    * post: dq = init dq0 ∧ returns last dq0 (Haskell notation) */
    def getRight(): A
}

class IntDeque extends Deque[Int]{
    /* 
    DTI: (L(dummyHead) is finite) && (size == #L(dummyHead.next))
    ABS: dq = {node.datum | node <- L(dummyHead.next, null)}
    Additionally, the head of dq is dummyHead.next.datum, and the back of dq is last.datum
    */
    private val dummyHead = new IntDeque.Node(Int.MinValue, null, null)
    private var last = dummyHead
    private var size = 0
    /** Is the queue empty? */
    def isEmpty : Boolean = size == 0

    /** Add x to the start of the queue. */
    def addLeft(x:Int) : Unit = {
        val newNode = new IntDeque.Node(x, dummyHead, dummyHead.next)
        if (isEmpty) {
            last = newNode  // Update last if this is the first element
        } else {
            dummyHead.next.prev = newNode  // Update prev of existing first node
        }
        dummyHead.next = newNode
        size += 1
    }
    /** Get and remove element from the start of the queue. */
    def getLeft() : Int = {
        assert(!isEmpty)
        val x = dummyHead.next.datum
        dummyHead.next = dummyHead.next.next
        if (dummyHead.next == null) {
            last = dummyHead
        } else {
            dummyHead.next.prev = dummyHead
        }
        size -= 1
        x
    }
    /** Add element to the end of the queue. */
    def addRight(x: Int) : Unit = {
        val newNode = new IntDeque.Node(x, last, null)
        last.next = newNode
        last = newNode
        size += 1
    }
    /** Get and remove element from the end of the queue. */
    def getRight() : Int = {
        assert(!isEmpty)
        val x = last.datum 
        last.prev.next = null
        last = last.prev
        size -= 1
        if (isEmpty) {
            last = dummyHead  // Reset last if deque becomes empty
        }
        x
    }
}

object IntDeque {
    private class Node(var datum:Int, var prev:Node, var next:Node)
}