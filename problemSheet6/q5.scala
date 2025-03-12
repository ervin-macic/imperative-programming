
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
    def flip() : Unit = {
        var t = this
        val oldTree =  new Tree(t.word, t.left, t.right)
        t.left = flipTree(oldTree.right)
        t.right = flipTree(oldTree.left)
    }
    def flipTree(t: Tree) : Tree = {
        if(t == null) {
            return null
        } else {
            return new Tree(t.word, flipTree(t.right), flipTree(t.left))
        }
    }
}
def Tree(word: String, left: Tree, right: Tree) = new Tree(word, left, right)
var tr = Tree("three", Tree("four", Tree("five", null, null), Tree("six",
Tree("seven", Tree("one", null, null), null), null)), Tree("two", null, null))

tr.flip()
tr.printBag()