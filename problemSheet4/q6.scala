/*
The Scala Set[A] trait contains an operation
def head : A
The API documentation describes it as
returns the first element of this set.
(a) Comment on the description of the operation.
(b) Write down a suitable specification for the operation (assuming a state as in Question 5).
(c) Give an implementation for this operation for the BitMapSet class of Question 5.
*/

/*
(a) The first element might not exist if the set is empty. Ideally, such a description should
also remark on the behaviour in this case. Also, it is not explicit what "first" element means
for a general set.

(b) Specification:
/** Returns the first element of the set
* pre: |A| >= 1
* post: A = A0 ∧ return (min A0) // here I used min since I assume the head of a set is the min.
def head : A

(c)
/** Returns the first element of the set
* pre: |A| >= 1
* post: A = A0 ∧ return (min A0)
def head : Int = {
    require(a.size >= 1)
    var i = 0
    // I: a(j) = 0 for all j in [0..i) and 0 <= i < N and a.size >= 1
    while(!a(i)) i += 1
    // I && a(i) == 1
    return i
}
*/