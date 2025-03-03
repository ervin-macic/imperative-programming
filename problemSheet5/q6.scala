/** A queue of data of type A.
* state: q : seq A
* init: q = [] */
trait Queue[A]{
/** Add x to the back of the queue.
* post: q = q0 ++ [x] */
def enqueue(x: A)

/** Remove and return the first element of the queue.
* pre: q =/= []
* post: q = tail q0 ∧ returns head q0
* or post: returns x s.t. q0 = [x] ++ q */
def dequeue(): A

/** Is the queue empty?
* post: q = q0 ∧ returns q = [] */
def isEmpty: Boolean
}

class IntQueue extends Queue[Int] {
    /* 
    DTI: (L(list) is finite) && (size == #L(list.next))
    ABS: q = {node.datum | node <- L(list.next, null)}
    Additionally, the head of q is list.next.datum, and the back of q is last.datum
    */
    private var list = new IntQueue.Node(Int.MinValue, null) // dummy head node
    private var last = list
    private var size = 0

    /** Add x to the back of the queue. */
    def enqueue(x: Int) = {
        val newNode = new IntQueue.Node(x, null)
        last.next = newNode 
        last = newNode
        size += 1
    }

    /** Remove and return the first element of the queue. */
    def dequeue(): Int = {
        assert(!isEmpty)
        val x = list.next.datum 
        list.next = list.next.next
        size -= 1
        x
    }

    /** Is the queue empty? */
    def isEmpty: Boolean = size == 0
}
object IntQueue{
  private class Node(var datum:Int, var next:Node)
}

