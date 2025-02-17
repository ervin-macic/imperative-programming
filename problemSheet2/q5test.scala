//> using dep org.scalatest::scalatest:3.2.19
//> using file "q5.scala"
import org.scalatest.funsuite.AnyFunSuite

class TestStrings extends AnyFunSuite {
    val line = "the quick brown fox jumped over the lazy dog.".toCharArray
    test("search finds existing pattern found in the middle of string") {
        assert(Searching.search("fox".toCharArray, line) == true)
    }
    // (a)
    test("search does not give a false positive result when pattern is not present") {
        assert(Searching.search("cat".toCharArray, line) == false)
    }
    // (b)
    test("search checks last possible pattern correctly") {
        assert(Searching.search("dog.".toCharArray, line) == true)
    }
    // (c)
    // Should I catch the exception here?
    test("search stops at the end of the line properly") {
        assert(Searching.search("og.X".toCharArray, line) == false)
    }
    // (d)
    test("search checks match between pattern and line character by character") {
        assert(Searching.search("box".toCharArray, line) == false)
    }
    // (e)
    test("search does not attempt to index out of bounds on pattern") {
        assert(Searching.search("foxY".toCharArray, line) == false)
    }
}

/* 
After running scala-cli test q3.scala 
I get the following:
Compiling project (Scala 3.6.3, JVM (11))
Compiled project (Scala 3.6.3, JVM (11))
TestStrings:
- search finds existing pattern found in the middle of string
- search checks last possible pattern correctly
- search stops at the end of the line properly
- search checks match between pattern and line character by character
- search does not attempt to index out of bounds on pattern
Run completed in 203 milliseconds.
Total number of tests run: 5
Suites: completed 1, aborted 0
Tests: succeeded 5, failed 0, canceled 0, ignored 0, pending 0
*/
/*
After augmenting each change highlighted in (a)...(f), the appropriate
test fails.
*/