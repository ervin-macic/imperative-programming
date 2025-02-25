/*The interface for the “phone book” object is given in Figure 1.

(a) Augment the trait with an operation
/** Delete the number stored against name (if it exists) */
def delete(name: String) : Boolean = ...
The procedure should return true precisely if name was previously in the phone book.
Give a specification on the abstract state space in terms of pre- and post-conditions.

(b) One version of the phone book developed in the lectures (which can be downloaded as
ArraysBook.scala) uses a pair of unordered arrays, names and numbers. Implement the
delete operation in this version.*/

/** The state of a phone book, mapping names (Strings) to numbers (also
* Strings).
* state: book : String → String
* init: book = {} */
trait Book{
    /** Add the maplet name -> number to the mapping.
    * post: book = book0 ⊕ {name → number} */
    def store(name: String, number: String) : Unit
    /** Return the number stored against name.
    * pre: name ∈ dom book
    * post: book = book0 ∧ returns book(name) */
    def recall(name: String) : String
    /** Is name in the book?
    * post: book = book0 ∧ returns name ∈ dom book */
    def isInBook(name: String) : Boolean
    /** Delete the number stored against name (if it exists).
    * post: returns (name ∈ dom book0) ∧
    * (name ∈ dom book0 ∧ book = book0 − {name → book0(name)} ∨
    * name not ∈ dom book0 ∧ book = book0) */
    def delete(name: String) : Boolean 
}

/** Delete the number stored against name (if it exists) */
def delete(name: String) : Boolean = {
    val i = find(name)
    if(i != count){
        count = count-1;
        names(i) = names(count)
        numbers(i) = numbers(count)
        return true
    } else { return false }
}