class Tree(var word: String, var left: Tree, var right: Tree) {
    def printBag() = printTree(this, 0)
    private def printTree(t: Tree, depth: Int) : Unit = {
        if(t != null) {
            println((". " * depth) + t.word)
            printTree(t.left, depth+1)
            printTree(t.right, depth+1)
        } else {
            println((". " * depth) + "null")
        }
    }
    def itrPrintBag() = itrPrintTree(this)
    private def itrPrintTree(root: Tree) : Unit = {
        val t = root
        var depth = 0
        val stack = new scala.collection.mutable.Stack[(Tree, Int)]
        stack.push((t,0))
        // Invariant: We still need to print the top; and for each tree t1
        // in the stack, we still need to print the data in the left tree
        // , and the data in the nodes of the right subtree
        // (in the order of the stack).
        while(!stack.isEmpty) {
            var (t,d) = stack.pop()
            if(t != null) {
                println((". " * d) + t.word)
                stack.push((t.left, d+1))
                stack.push((t.right, d+1))
                depth = d+1
            } else {
                println((". " * depth) + "null")
            }
        }
    }
}
def Tree(word: String, left: Tree, right: Tree) = new Tree(word, left, right)

var tr = Tree("three", Tree("four", Tree("five", null, null), Tree("six",
Tree("seven", Tree("one", null, null), null), null)), Tree("two", null, null))

tr.itrPrintBag()

