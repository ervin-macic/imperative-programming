def include(x: Char, ys: List[Char]) : List[List[Char]] = {
    ys match {
        case Nil => return List(List(x))
        case (y::ys) => {
            return (x::y::ys.toList) :: ((include(x, ys.toList)).map((y::_)))
        }
    }
}
def permutations(xs: List[Char]) : List[List[Char]] = {
    xs match {
        case Nil => {
            return List(Nil)
        }
        case (x::xs) => {
            (for {
                ys <- permutations(xs)     
                zs <- include(x, ys)         
            } yield zs)
        }
    }
}

var perms = permutations(List('a', 'b', 'c'))
for(p <- perms) {
    for(c <- p) {
        print(c)
    }
    println()
}