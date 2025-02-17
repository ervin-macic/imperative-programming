//> using dep org.scalatest::scalatest:3.2.15
import org.scalatest.funsuite.AnyFunSuite


class TestStrings extends AnyFunSuite {
    val mathematicians = Array[String]("Euclid", "Euler", "Newton", "Leibnitz", "Cauchy", "Fermat", "Gauss", "Jensen")
    test("array should contain at least 6 elements") {
        assert(mathematicians.size >= 6)
    }
    test("array should be sorted after applying sort function") {
        val sortedMathematicians = mathematicians.sorted
        for(i <- 0 until (mathematicians.size-1)) {
            assert(
                sortedMathematicians(i) <= sortedMathematicians(i + 1),
                s"Elements not in order at index $i: ${sortedMathematicians(i)} > ${sortedMathematicians(i + 1)}"
            )
        }
    }
    test("array should be sorted by length after applying sort by length function") {
        val lengthSortedMathematicians = mathematicians.sortBy(_.length)
        for(i <- 0 until (mathematicians.size-1)) {
            assert(
                lengthSortedMathematicians(i).length <= lengthSortedMathematicians(i + 1).length,
                s"Elements not in order by length at index $i: ${lengthSortedMathematicians(i)} > ${lengthSortedMathematicians(i + 1)}"
            )
        }
    }
}

/* 
After running scala-cli test q3.scala 
I get the following:
TestStrings:
- array should contain at least 6 elements
- array should be sorted after applying sort function
- array should be sorted by length after applying sort by length function
Run completed in 179 milliseconds.
Total number of tests run: 3
Suites: completed 1, aborted 0
Tests: succeeded 3, failed 0, canceled 0, ignored 0, pending 0
All tests passed.