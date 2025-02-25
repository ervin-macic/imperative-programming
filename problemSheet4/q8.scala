object Main{
    /** The state of a phone book, mapping names (Strings) to
    * numbers (also Strings).
    * state: book : String → String
    * init: book = {} */
    trait Book{
        /** Store number against name in the phone book.
        * post: book = book0 ⊕ {name → number} */
        def store(name: String, number: String) : Unit

        /** The number stored against name.
        * pre: name ∈ dom book
        * post: book = book0 ∧ returns book(name) */
        def recall(name: String) : String

        /** Does name appear in the book?
        * post: book = book0 ∧ returns name ∈ dom book */
        def isInBook(name: String) : Boolean
    }

    object SortedNamesBook extends Book{
        private val MAX = 1000 // max number of names we can store
        private val names = new Array[String](MAX)
        private val numbers = new Array[String](MAX)
        private var count = 0
        // Abs: book = {names(i) → numbers(i) | i ∈ [0..count)}
        // DTI: 0 ≤ count ≤ MAX ∧
        // ∀i, j ∈ [0..count)(i != j ⇒ names(i) != names(j))

        /** Return the index i<count s.t. names(i) = name; or
        * return count if no such index exists */
        private def find(name: String) : Int = {
            var l = 0; var r = count
            while(l < r){
                val m = l+(r-l)/2 
                if(names(m) == null || names(m) < name) l = m+1 else r = m
            }
            if(l == count || names(l) != name) count else l
        }
        
        /** Binary search */
        private def search(name: String) : Int = {
            var l = 0; var r = count
            while(l < r){
                val m = l+(r-l)/2
                if(names(m) == null || names(m) < name) l = m+1 else r = m
            }
            l
        }

        /** Is name in the book? */
        def isInBook(name: String): Boolean = find(name) < count

        /** Return the number stored against name */
        def recall(name: String) : String = {
            val i = find(name)
            assert(i < count)
            numbers(i)
        }

        /** Add the maplet name -> number to the mapping */
        def store(name: String, number: String): Unit = {
            val i = search(name)
            if(i == count){
                assert(count < MAX); 
                names(i) = name;
                numbers(i) = number;
                count += 1
            } else if (names(i) == name) {
                numbers(i) = number
            } else {
                var j = count
                // I: names[j..count] = names[j-1..count-1]
                while(j > i) {
                    names(j) = names(j-1)
                    numbers(j) = numbers(j-1)
                    j -= 1
                }
                names(i) = name
                numbers(i) = number
                count += 1
            }
        }

        /** Delete the number stored against name (if it exists) */
        def delete(name: String) : Boolean = {
            val i = find(name)
            if(i < count){
                var j = i
                // I: names[i..j) = names[i+1..j+1)
                while(j < count - 1) {
                    names(j) = names(j+1)
                    numbers(j) = numbers(j+1)
                    j += 1
                }
                names(count - 1) = null
                numbers(count - 1) = null
                count -= 1
                true
            } else false
        }
    }

    def main(args: Array[String]): Unit = {
        SortedNamesBook.store("Pikachu", "12345")
        SortedNamesBook.store("Charmander", "67890")
        SortedNamesBook.store("Bulbasaur", "54321")

        println("Pikachu's number: " + SortedNamesBook.recall("Pikachu"))
        println("Charmander's number: " + SortedNamesBook.recall("Charmander"))
        println("Bulbasaur's number: " + SortedNamesBook.recall("Bulbasaur"))

        println("Is Pikachu in book? " + SortedNamesBook.isInBook("Pikachu"))
        println("Is Squirtle in book? " + SortedNamesBook.isInBook("Squirtle"))

        SortedNamesBook.store("Pikachu", "99999")
        println("Pikachu's updated number: " + SortedNamesBook.recall("Pikachu"))

        println("Deleting Charmander: " + SortedNamesBook.delete("Charmander"))
        println("Is Charmander in book? " + SortedNamesBook.isInBook("Charmander"))

        println("Deleting Squirtle: " + SortedNamesBook.delete("Squirtle"))
    }
}
