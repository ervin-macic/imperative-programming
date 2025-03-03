class Node(val datum: Int, var next: Node) {
    override def toString : String = {
        var head = this
        var str = ""
        // I: str is the string "a0 -> a1 -> a2 -> ... ak -> " where ak is the kth node following
        // references from head for which head.datum = a0.toInt
        while(head.next != null) {
            str += s"${head.datum} -> "
            head = head.next
        }
        // I && head.next == null
        str += s"${head.datum}."
        return str
    }
}

var myList = new Node(1, null)
for(i <- 2 to 12) {
    var newHead = new Node(i, null)
    newHead.next = myList
    myList = newHead
}

println("List is "+myList)
// (c) Reverse the linked list in O(n)
var currNode = myList // initial head
var nextNode = myList.next 
// reverse the head:
var prevNode = currNode 
currNode.next = null
currNode = nextNode
// I: (L(currNode) is the collection of all nodes visited)
// && (prevNode.next == currentNode)
while(currNode != null) {
    // save the next node before rewiring so you don't lose the remainder of the linked list
    nextNode = currNode.next 
    // rewire the current node to point to previous node
    currNode.next = prevNode
    // update previous node to the current (now processed) node for the next iteration
    prevNode = currNode
    // update current node for processing to the next node
    currNode = nextNode
}
// here prevNode holds the new head of the linked list, currNode is null
// (I && currNode = null) and since prevNode.next == currentNode, L(prevNode) is the whole list in reverse
// since now prevNode is the tail of the initial linked list

println("List is "+prevNode)