def main(args: Array[String]) : Unit = {
    val myset = new scala.collection.mutable.HashSet[Int]
    myset.add(1); myset.add(1); myset.add(2)
    assert(myset.contains(3)==false)
}
