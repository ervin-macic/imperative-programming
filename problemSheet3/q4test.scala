//> using dep org.scalatest::scalatest:3.2.19
//> using file "q4.scala"
import org.scalatest.funsuite.AnyFunSuite

class TestInsertionSort extends AnyFunSuite {
    test("isort sorts an empty array correctly") {
        val arr = Array[Int]()
        val expected = arr.sorted
        Q4.isort(arr)
        assert(arr.sameElements(expected))
    }

    test("isort sorts a single-element array correctly") {
        val arr = Array(1)
        val expected = arr.sorted
        Q4.isort(arr)
        assert(arr.sameElements(expected))
    }

    test("isort sorts an already sorted array correctly") {
        val arr = Array(1, 2, 3, 4, 5)
        val expected = arr.sorted
        Q4.isort(arr)
        assert(arr.sameElements(expected))
    }

    test("isort sorts a reverse-sorted array correctly") {
        val arr = Array(5, 4, 3, 2, 1)
        val expected = arr.sorted
        Q4.isort(arr)
        assert(arr.sameElements(expected))
    }

    test("isort sorts an array with duplicates correctly") {
        val arr = Array(3, 1, 4, 1, 5)
        val expected = arr.sorted
        Q4.isort(arr)
        assert(arr.sameElements(expected))
    }

    test("isort sorts an array with all same elements correctly") {
        val arr = Array(2, 2, 2, 2)
        val expected = arr.sorted
        Q4.isort(arr)
        assert(arr.sameElements(expected))
    }

    test("isort sorts a randomly ordered array correctly") {
        val arr = Array(5, 2, 4, 6, 1, 3)
        val expected = arr.sorted
        Q4.isort(arr)
        assert(arr.sameElements(expected))
    }
}
/*
TestInsertionSort:
- isort sorts an empty array correctly
- isort sorts a single-element array correctly
- isort sorts an already sorted array correctly
- isort sorts a reverse-sorted array correctly
- isort sorts an array with duplicates correctly
- isort sorts an array with all same elements correctly
- isort sorts a randomly ordered array correctly
Run completed in 136 milliseconds.
Total number of tests run: 7
Suites: completed 1, aborted 0
Tests: succeeded 7, failed 0, canceled 0, ignored 0, pending 0
All tests passed.
*/