/*
Question 1
Each bucket in a hash table represents a mapping from certain keys (all those that have a
certain hash code) to values. In our implementation of hash tables, each bucket is represented
as an unordered linked list, with a time for lookup of O(N), where N is the length of the list.
Wouldn’t it be a good idea to replace these linked lists with some other data structure that
represents a mapping with better asymptotic complexity?
*/

Call the functions find/add/count lookups since they look for a specific value in the hash table and 
possibly do something else.

A linked list might be useful if we implement the following heuristic: when an entry is needed, it is moved
to the front of the concrete sequence of entries/linked list. This speeds up future calls to lookup 
operations given that recent targets are more likely than others. 
This doesn't improve asymptotic complexity though.

If we use an ordered linked list then the lookup operations will be faster as find can finish searching 
once the remaining words are greater than the word we're searching for (lexicographically greater). 
This also doesn't improve asymptotic complexity though.

Another good choice would be to hold a balanced (red-black) search tree for each bucket which enables O(log(len)) lookup where len
is the number of values inside the bucket corresponding to the specific hash code. The criteria for the tree 
could be lexicographical size. 
This has higher memory usage but does improve asymptotic complexity.

The point of a hash table should be to have as few as possible collisions so the size of "len" here should be small. 
Thus it shouldn't matter too much whether we do O(len) or O(log(len)) since at some point we should resize the hash table rather than 
create more complex data structures for handling collisions.