object Main{
    def search(a: Array[String], x: String) : Int = {
        // invariant I: a[0..l) < x <= a[r..N) && 0 <= l <= r <= N
        var l = 0; var r = a.length
        while(l < r){
            val m = l+(r-l)/2 // l <= m < r
            if(a(m) < x) l = m+1 else r = m
        }
        // I && l = r, so a[0..l) < x <= a[l..N)
        l
    }
    def main(args: Array[String]) : Unit = {
        val arr = Array("abc", "ac", "add", "ade", "bce", "bdf", "ghi")
        println(search(arr, "aed"))
    }
}
