val timeEnd:Double = 1.0
val numSteps:Int = 30000000
val timeStep:Double = timeEnd/numSteps
// timeEnd=numSteps*timeStep and numSteps∈ N
var time = 0.0
while (time < timeEnd - 1e-9)
{
// Inv: 0 <= time <= timeEnd and time=k*timeStep for some k∈ N
time += timeStep
}
println(time)
// Inv => time == timeEnd

/*

(a) The loop is intended to be executed numSteps times. Explain why the presence of
rounding errors may lead to the loop actually being executed numSteps+1 times, even if
the rounding errors are very small.

Answer:
It is possible that timeStep won't be precise, i.e. timeEnd/numSteps = 1.0/3 then 
timeStep = 0.33333... and thus time += 3*timeStep is still <1 and thus the while loop will
run 4 times instead of 3.

(b) The postcondition for the loop would be correct if arithmetic were exact, but is actually
incorrect with floating point arithmetic. How inaccurate might time be after the loop?

Answer: 
Taking numSteps = 9 can give 1e-16 inaccuracy, i.e. 1.0000000000000002 instead of 1
Taking a large numSteps like 3 billion gives 1.000000032972873 which is an error 
of the order 1e-9

(c) Suggest improvements to the loop such that the postcondition is correct—as ‘correct’
as possible. How inaccurate might time be after the loop now?

Answer:
We can subtract a small value, like 1e-9 as seen above, to ensure the loop runs for the correct
number of times.
We could also do something like:
var step = 0
while (step < numSteps) {
    time = step * timeStep
    step += 1
}

*/