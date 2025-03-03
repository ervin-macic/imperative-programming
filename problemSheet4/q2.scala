class Triangle
class OpaqueTriangle extends Triangle
class Renderer {
    def accept(a: Triangle) = println("Accepted for rendering.")
}
class RayTracingRenderer extends Renderer {
    def accept(a: Triangle) = println("Accepted for ray-trace rendering.2")
    def accept(a: OpaqueTriangle) = println("Accepted for ray-trace rendering.")
}

val a: OpaqueTriangle = new OpaqueTriangle
val r1: Renderer = new RayTracingRenderer
r1.accept(a) // Accepted for rendering.
val r2: RayTracingRenderer = new RayTracingRenderer
r2.accept(a) // Accepted for ray-trace rendering.

/*
========================================
r1 has static type Renderer and dynamic (runtime) type RayTracingRenderer which matters for overriding (only).
The compiler searches Renderer (since static type is Renderer) 
for "accept(a: OpaqueTriangle)" and doesn't find a direct match.
Then the compiler checks the closest match and finds "accept(a: Triangle)" where OpaqueTriangle 
is a subtype of Triangle, which matches.
The compiler takes accept(Triangle)'s bytecode but it's not clear which version will be run (this is best guess). 
At runtime, the JVM looks up r1's runtime (dynamic) class (RayTracingRenderer) and checks the vtable
for overriden accept(Triangle) in RayTracingRenderer but nothing is found so 
ultimately accept(Triangle) from Renderer is called: "Accepted for rendering" is printed.

r2 has both static and dynamic type RayTracingRenderer. 
r2 has static type RayTracingRenderer, so the compiler searches for a method matching accept(OpaqueTriangle) 
inside RayTracingRenderer and finds it, thus "Accepted for ray-tracing rendering." is printed at runtime as 
the JVM checks the vtable of RayTracingRenderer.
*/