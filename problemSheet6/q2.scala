/*
Suppose a hash table with N buckets is filled with n items, and assume that the values of
the hash function for the n items are independent random variables uniformly distributed in
[0..N). What is the probability distribution for the number of items that fall in a particular
bucket? What is the expected number of comparisons of keys that are performed when get
is called and the search is (a) successful and (b) unsuccessful?
*/
Consider some bucket. Each item acts as a Bernoulli random variable with p=1/N with respect to
this specific bucket. The number of items in this bucket is a sum of n Bernoulli variables so
the total number of items in the bucket has a pdf Binom(n,1/N). Binomial r.v. has EV=n*1/N

For the second question, when the search is unsuccessful we have to do len key comparisons, i.e.
the number of items in the bucket, i.e. N_k which has pdf Binom(n, 1/N). 
When the search is successful, we expect to find the key after len/2 key comparisons. The remaining 
n-1 items have Binom(n-1, 1/N) pdf to be in this bucket we're searching through so the expected number
of key comparisons will be E[len]/2 = 1 + (n-1)/N * 1/2