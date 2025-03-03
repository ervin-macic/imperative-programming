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

class ArrayQueue extends Queue[Int] {
    /*
    DTI: (0 <= size, i, j < MAX) && (variable size is the number of elements in the queue)
    ABS: q = if i <= j then data[i..j) else data[i..MAX) ++ data[0..j) (The queue, of type seq A)
    */
    val MAX = 100 // max number of pieces of data
    private var size = 0
    private var i = 0
    private var j = 0
    val data = new Array[Int](MAX)
    /** Add x to the back of the queue
    * pre: queue is not full
    * post: q = q0 ++ [x]
    */
    def enqueue(x: Int) : Unit = {
        assert(!isFull, "Queue is full!")
        data(j) = x
        j += 1
        j %= MAX
        size += 1
    }
    /** Remove and return the first element of the queue.
    * pre: q =/= []
    * post: q = tail q0 ∧ returns head q0
    * or post: returns x s.t. q0 = [x] ++ q */
    def dequeue() : Int = {
        assert(!isEmpty, "Queue is empty!")
        var x = data(i)
        data(i) = 0
        i += 1
        i %= MAX
        size -= 1
        x
    }
    /* Return true if queue is full */
    def isFull : Boolean = size == MAX
    /* Return true if queue is empty */
    def isEmpty : Boolean = size == 0
}

var q = new ArrayQueue()
for(i <- 0 to 4) {
    q.enqueue(i)
}
while(!q.isEmpty) {
    println(q.dequeue())
}