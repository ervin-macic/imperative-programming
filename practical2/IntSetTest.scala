//> using dep org.scalatest::scalatest:3.2.19
//> using file "IntSet.scala"

import org.scalatest.matchers.should.Matchers 
import org.scalatest.funsuite.AnyFunSuite
import scala.collection.mutable.HashSet
import Main._

class IntSetTest extends AnyFunSuite {

  test("1. Add elements and check size") {
    val set = new IntSet
    assert(set.size == 0)
    set.add(5)
    assert(set.size == 1)
    set.add(3)
    assert(set.size == 2)
    set.add(5)
    assert(set.size == 2)
  }

  test("2. Check if set contains elements") {
    val set = new IntSet
    set.add(4)
    set.add(10)
    assert(set.contains(4))
    assert(set.contains(10))
    assert(!set.contains(5))
  }

  test("3. Remove elements from the set") {
    val set = new IntSet
    set.add(2)
    set.add(6)
    assert(set.remove(2))
    assert(!set.contains(2))
    assert(!set.remove(3))
    assert(set.contains(6))
  }

  test("4. Find any element in the set") {
    val set = new IntSet
    set.add(7)
    assert(set.any == 7)
  }

  test("5. Compare two sets for equality") {
    val set1 = new IntSet
    val set2 = new IntSet
    set1.add(1)
    set1.add(2)
    set2.add(1)
    set2.add(2)
    assert(set1 == set2)
  }

  test("6. SubsetOf should return true for valid subsets") {
    val set1 = new IntSet
    val set2 = new IntSet
    set1.add(1)
    set1.add(2)
    set2.add(1)
    set2.add(2)
    set2.add(3)
    assert(set1.subsetOf(set2))
    assert(!set2.subsetOf(set1))
  }

  test("7. Union of two sets should contain all elements") {
    val set1 = new IntSet
    val set2 = new IntSet
    set1.add(1)
    set1.add(3)
    set2.add(2)
    set2.add(3)
    val unionSet = set1.union(set2)
    assert(unionSet.contains(1))
    assert(unionSet.contains(2))
    assert(unionSet.contains(3))
    assert(unionSet.size == 3)
  }

  test("8. Intersection of two sets should return common elements") {
    val set1 = new IntSet
    val set2 = new IntSet
    set1.add(1)
    set1.add(2)
    set1.add(3)
    set2.add(2)
    set2.add(3)
    set2.add(4)
    val intersectSet = set1.intersect(set2)
    assert(intersectSet.contains(2))
    assert(intersectSet.contains(3))
    assert(!intersectSet.contains(1))
    assert(!intersectSet.contains(4))
  }

  test("9. Map function should apply function to all elements") {
    val set = new IntSet
    set.add(1)
    set.add(2)
    set.add(3)
    val mappedSet = set.map(_ * 2)
    assert(mappedSet.contains(2))
    assert(mappedSet.contains(4))
    assert(mappedSet.contains(6))
    assert(!mappedSet.contains(1))
  }

  test("10. Filter function should keep only matching elements") {
    val set = new IntSet
    set.add(1)
    set.add(2)
    set.add(3)
    val filteredSet = set.filter(_ % 2 == 1)
    assert(filteredSet.contains(1))
    assert(filteredSet.contains(3))
    assert(!filteredSet.contains(2))
  }

  test("11. Empty set basic properties") {
    val emptySet = new IntSet
    assert(emptySet.size == 0)
    assert(!emptySet.contains(1))
    assert(!emptySet.remove(1))
  }
  
  test("12. Empty set union operations") {
    val emptySet = new IntSet
    val nonEmptySet = IntSet(1, 2, 3)
    assert(emptySet.union(nonEmptySet) == nonEmptySet)
    assert(nonEmptySet.union(emptySet) == nonEmptySet)
  }
  
  test("13. Empty set intersection operations") {
    val emptySet = new IntSet
    val nonEmptySet = IntSet(1, 2, 3)
    assert(emptySet.intersect(nonEmptySet).size == 0)
    assert(nonEmptySet.intersect(emptySet).size == 0)
  }
  
  test("14. Empty set subset operations") {
    val emptySet = new IntSet
    val nonEmptySet = IntSet(1, 2, 3)
    assert(emptySet.subsetOf(nonEmptySet))
    assert(emptySet.subsetOf(emptySet))
  }
  
  test("15. Empty set map and filter") {
    val emptySet = new IntSet
    assert(emptySet.map(_ * 2).size == 0)
    assert(emptySet.filter(_ > 0).size == 0)
  }

  test("16. Remove elements until empty") {
    val set = IntSet(5, 10, 15, 20)
    assert(set.size == 4)
    
    assert(set.remove(5))
    assert(set.size == 3)
    
    assert(set.remove(10))
    assert(set.size == 2)
    
    assert(set.remove(15))
    assert(set.size == 1)
    
    assert(set.remove(20))
    assert(set.size == 0)
  }
  
  test("17. Verify set is truly empty after removal") {
    val set = IntSet(5, 10, 15, 20)
    set.remove(5)
    set.remove(10)
    set.remove(15)
    set.remove(20)
    
    assert(!set.contains(5))
    assert(!set.contains(10))
    assert(!set.contains(15))
    assert(!set.contains(20))
  }
  
  test("18. Operations on newly emptied set") {
    val set = IntSet(5, 10, 15, 20)
    set.remove(5)
    set.remove(10)
    set.remove(15)
    set.remove(20)
    
    assert(!set.remove(5))
    set.add(25)
    assert(set.size == 1)
    assert(set.contains(25))
  }

  test("19. Add large number of elements") {
    val largeSet = new IntSet
    for (i <- 1 to 100) {
      largeSet.add(i)
    }
    
    assert(largeSet.size == 100)
    assert(largeSet.contains(1))
    assert(largeSet.contains(50))
    assert(largeSet.contains(100))
  }
  
  test("20. Verify large set boundary conditions") {
    val largeSet = new IntSet
    for (i <- 1 to 100) {
      largeSet.add(i)
    }
    
    assert(!largeSet.contains(0))
    assert(!largeSet.contains(101))
  }
  
  test("21. Remove elements from large set") {
    val largeSet = new IntSet
    for (i <- 1 to 100) {
      largeSet.add(i)
    }
    for (i <- 1 to 100 by 2) {
      assert(largeSet.remove(i))
    }
    
    assert(largeSet.size == 50)
    assert(!largeSet.contains(1))
    assert(largeSet.contains(2))
    assert(!largeSet.contains(99))
    assert(largeSet.contains(100))
  }

  test("22. Union followed by intersection") {
    val set1 = IntSet(1, 3, 5, 7, 9)
    val set2 = IntSet(2, 4, 6, 8, 10)
    val set3 = IntSet(1, 2, 3, 4, 5)
    
    val unionSet = set1.union(set2)
    assert(unionSet.size == 10)
    val intersectionWithSet3 = unionSet.intersect(set3)
    assert(intersectionWithSet3.size == 5)
    assert(intersectionWithSet3 == set3)
  }
  
  test("23. Filter followed by map") {
    val set1 = IntSet(1, 3, 5, 7, 9)
    val set2 = IntSet(2, 4, 6, 8, 10)
    
    val unionSet = set1.union(set2)
    val oddNumbers = unionSet.filter(_ % 2 == 1)
    assert(oddNumbers == set1)
    val doubledOdds = oddNumbers.map(_ * 2)
    assert(doubledOdds.contains(2))
    assert(doubledOdds.contains(6))
    assert(doubledOdds.contains(10))
    assert(doubledOdds.contains(14))
    assert(doubledOdds.contains(18))
  }
  
  test("24. Map followed by filter") {
    val set3 = IntSet(1, 2, 3, 4, 5)
    
    val mapped = set3.map(_ * 3)
    assert(mapped.contains(3))
    assert(mapped.contains(6))
    assert(mapped.contains(9))
    assert(mapped.contains(12))
    assert(mapped.contains(15))
    
    val filtered = mapped.filter(_ > 10)
    assert(filtered.size == 2)
    assert(filtered.contains(12))
    assert(filtered.contains(15))
  }

  test("25. Equality with different insertion orders") {
    val set1 = new IntSet
    set1.add(5)
    set1.add(3)
    set1.add(7)
    
    val set2 = new IntSet
    set2.add(3)
    set2.add(7)
    set2.add(5)
    
    assert(set1 == set2)
    assert(set1.size == set2.size)
  }
  
  test("26. Equality after element removal") {
    val set1 = new IntSet
    set1.add(5)
    set1.add(3)
    set1.add(7)
    
    val set2 = new IntSet
    set2.add(3)
    set2.add(7)
    set2.add(5)
    
    set1.remove(3)
    set2.remove(3)
    assert(set1 == set2)
  }
  
  test("27. Equality after adding duplicates") {
    val set1 = new IntSet
    set1.add(5)
    set1.add(3)
    set1.add(7)
    
    val set2 = new IntSet
    set2.add(3)
    set2.add(7)
    set2.add(5)
    
    set1.add(5)
    assert(set1 == set2)
  }

  test("28. Map with collisions") {
    val set = IntSet(1, 2, 3, 4, 5)
    
    val collisionMap = set.map(_ % 3)
    assert(collisionMap.size == 3)
    assert(collisionMap.contains(0))
    assert(collisionMap.contains(1))
    assert(collisionMap.contains(2))
  }
  
  test("29. Map to negative values") {
    val set = IntSet(1, 2, 3, 4, 5)
    
    val negativeMap = set.map(x => -x)
    assert(negativeMap.size == 5)
    assert(negativeMap.contains(-1))
    assert(negativeMap.contains(-5))
  }
  
  test("30. Identity map") {
    val set = IntSet(1, 2, 3, 4, 5)
    
    val identityMap = set.map(x => x)
    assert(identityMap == set)
  }

  test("31. Filter that keeps everything") {
    val set = IntSet(1, 2, 3, 4, 5)
    
    val keepAll = set.filter(_ => true)
    assert(keepAll == set)
  }
  
  test("32. Filter that removes everything") {
    val set = IntSet(1, 2, 3, 4, 5)
    
    val keepNone = set.filter(_ => false)
    assert(keepNone.size == 0)
  }
  
  test("33. Complex filter conditions") {
    val set = IntSet(1, 2, 3, 4, 5)
    
    val complexFilter = set.filter(x => (x * x) % 2 == 1)
    assert(complexFilter.size == 3)
    assert(complexFilter.contains(1))
    assert(complexFilter.contains(3))
    assert(complexFilter.contains(5))
  }

  test("34. Factory method with duplicates") {
    val set = IntSet(5, 3, 7, 3, 5)
    assert(set.size == 3)
    assert(set.contains(3))
    assert(set.contains(5))
    assert(set.contains(7))
  }
  
  test("35. Empty factory method") {
    val emptySet = IntSet()
    assert(emptySet.size == 0)
  }

  test("36. BetterSubsetOf with regular subsets") {
    val set1 = IntSet(1, 3, 5)
    val set2 = IntSet(1, 2, 3, 4, 5)
    
    assert(set1.betterSubsetOf(set2))
    assert(!set2.betterSubsetOf(set1))
  }
  
  test("37. BetterSubsetOf with non-subsets") {
    val set1 = IntSet(1, 3, 5)
    val set3 = IntSet(1, 5, 9)
    
    assert(!set1.betterSubsetOf(set3))
    assert(!set3.betterSubsetOf(set1))
  }
  
  test("38. BetterSubsetOf with empty sets") {
    val set1 = IntSet(1, 3, 5)
    val emptySet = new IntSet
    
    assert(emptySet.betterSubsetOf(set1))
    assert(!set1.betterSubsetOf(emptySet))
    assert(emptySet.betterSubsetOf(emptySet))
  }

  test("39. CopyFrom functionality") {
    val source = IntSet(1, 3, 5)
    val target = IntSet(2, 4, 6, 8)
    
    target.copyFrom(source)
    
    assert(target.contains(1))
    assert(target.contains(3))
    assert(target.contains(5))
    assert(!target.contains(2))
    assert(!target.contains(4))
    
    assert(target.size == source.size)
  }
  test("40. CopyFrom with empty source") {
    val source = new IntSet
    val target = IntSet(2, 4, 6, 8)
    
    target.copyFrom(source)
    
    // Target should now be empty
    assert(target.size == 0)
    assert(!target.contains(2))
    assert(!target.contains(4))
    assert(!target.contains(6))
    assert(!target.contains(8))
  }
  
  test("41. CopyFrom with empty target") {
    val source = IntSet(1, 3, 5)
    val target = new IntSet
    
    target.copyFrom(source)
    
    assert(target.size == 3)
    assert(target.contains(1))
    assert(target.contains(3))
    assert(target.contains(5))
  }
  
  test("42. CopyFrom with partially overlapping sets") {
    val source = IntSet(1, 3, 5)
    val target = IntSet(3, 5, 7)
    
    target.copyFrom(source)
    
    // Target should have only source elements
    assert(target.size == 3)
    assert(target.contains(1))
    assert(target.contains(3))
    assert(target.contains(5))
    assert(!target.contains(7))
  }
  
  test("43. Multiple operations sequence") {
    val set = new IntSet
    
    set.add(10)
    set.add(20)
    set.add(30)
    assert(set.size == 3)
    
    set.remove(20)
    assert(set.size == 2)
    assert(!set.contains(20))
    
    val mappedSet = set.map(_ / 10)
    assert(mappedSet.size == 2)
    assert(mappedSet.contains(1))
    assert(mappedSet.contains(3))
    
    set.add(40)
    assert(set.size == 3)
    assert(mappedSet.size == 2)
  }
  
  test("44. Chained operations performance") {
    val largeSet = new IntSet
    for (i <- 1 to 1000) {
      largeSet.add(i)
    }
    
    // Chain multiple operations
    val result = largeSet
      .filter(_ % 2 == 0)  
      .map(_ * 3)          
      .filter(_ % 6 == 0)
      .intersect(IntSet(6, 12, 18, 24, 30, 36, 42, 48, 54, 60))
    
    assert(result.size == 10)
    assert(result.contains(6))
    assert(result.contains(12))
    assert(result.contains(18))
    assert(result.contains(24))
    assert(result.contains(30))
    assert(result.contains(36))
    assert(result.contains(42))
    assert(result.contains(48))
    assert(result.contains(54))
    assert(result.contains(60))
  }
}