//> using dep org.scalatest::scalatest:3.2.19
//> using file "q3.scala"

import org.scalatest.matchers.should.Matchers 
import org.scalatest.funsuite.AnyFunSuite
import scala.collection.mutable.HashSet

class TestQ3 extends AnyFunSuite {
    val testSet = HashSet("Pikachu", "Charmander", "Bulbasaur", "Squirtle")
    test("Add behaves as expected") {
        var set = testSet.clone()
        set.add("Mew")
        assert(set.contains("Mew"))
    }

    test("Remove behaves as expected") {
        var set = testSet.clone()
        set.remove("Pikachu")
        assert(!set.contains("Pikachu"))
    }

    test("Contains behaves as expected") {
        var set = testSet.clone()
        assert(set.contains("Pikachu") && set.contains("Charmander") && set.contains("Bulbasaur") && set.contains("Squirtle"))
    }

    test("Size behaves as expected") {
        var set = testSet.clone()
        assert(set.size == 4)
    }

    test("isEmpty behaves as expected") {
        var set = testSet.clone()
        assert(!set.isEmpty)
    }

    test("isEmpty behaves as expected on empty set") {
        val set = HashSet[String]()
        assert(set.isEmpty)
    }
    test("Add behaves as expected when adding element already in the set") {
        var set = testSet.clone()
        set.add("Pikachu")
        assert(set.contains("Pikachu"))
    }
    test("Add behaves as expected when removing element immediately after adding") {
        var set = testSet.clone()
        set.add("Pikachu")
        set.remove("Pikachu")
        assert(!set.contains("Pikachu")) // it's a set so it shouldn't duplicate elements
    }
    test("Remove behaves as expected when attempting to remove an element not in the set") {
        var set = testSet.clone()
        assert(!set.remove("Mew"))
    }
    test("Attempting to get maximum of an empty hash set throws UnsupportedOperationException") {
        var set = HashSet[String]()
        intercept[UnsupportedOperationException] {
            set.max
        }
    }

}
