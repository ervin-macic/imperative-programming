object BinaryTreeBag {
    class Tree(var word: String, var count: Int, var left: Tree, var right: Tree)
    def apply(word: String, count: Int, left: Tree, right: Tree) = new Tree(word, count, left, right)
    def countWords(t: Tree) : Int = {
        if(t == null) {
            return 0
        }
        return t.count + countWords(t.left) + countWords(t.right)
    }
    def itrCountWords(root: Tree) : Int = {
        var total = 0
        var t = root
        var stack = new scala.collection.mutable.Stack[Tree]
        stack.push(t)
        while(!stack.isEmpty) {
            t = stack.pop()
            if(t != null) {
                total += t.count 
                stack.push(t.left)
                stack.push(t.right)
            }
        }
        return total
    }
}

var tr = BinaryTreeBag("three", 3, BinaryTreeBag("four", 4, BinaryTreeBag("five", 5, null, null), BinaryTreeBag("six", 6,
BinaryTreeBag("seven", 7, BinaryTreeBag("one", 1, null, null), null), null)), BinaryTreeBag("two", 2, null, null))

println(BinaryTreeBag.itrCountWords(tr))