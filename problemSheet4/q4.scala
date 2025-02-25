/*
* init: {}
*/
trait Stack[A] {
  /**
   * Push a value onto the stack.
   * pre: S_0 && x is the value to push
   * post: S = S_0 ++ [x]
   */
  def push(x: A): Stack[A]
  
  /**
   * Remove and return the top value from the stack.
   * pre: S_0
   * post (S_0.last, S_0.init) (or maybe (x, S_0-{x}) where x = S_0.last) (return a tuple containing the popped value and the resulting stack)
   * throw NoSuchElementException if the stack is empty
   */
  def pop(): (A, Stack[A])
  
  /**
   * Test whether the stack is empty.
   * post: S=S_0 && return true if the stack is empty, false otherwise
   */
  def isEmpty: Boolean
}