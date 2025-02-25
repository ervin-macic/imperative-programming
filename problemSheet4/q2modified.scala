class Triangle
class OpaqueTriangle extends Triangle
class Renderer {
    def accept(a: Triangle) = println("Accepted for rendering.")
}
class RayTracingRenderer extends Renderer {
    override def accept(a: Triangle) = println("Modified r1.accept(a) call!")
    def accept(a: OpaqueTriangle) = println("Accepted for ray-trace rendering.")
}

val a: OpaqueTriangle = new OpaqueTriangle
val r1: Renderer = new RayTracingRenderer
r1.accept(a) // Accepted for rendering.
val r2: RayTracingRenderer = new RayTracingRenderer
r2.accept(a) // Accepted for ray-trace rendering.

/*
Going through the process for r1.accept(a) again but with this modified version:
The compiler notices r1 has static type Renderer. a has static type OpaqueTriangle which
can do whatever Triangle can and can take its place everywhere in the program.
Now the compiler sees r1.accept(a) and looks straight into the Renderer class since 
r1 has static type Renderer. It doesn't find an exact match but finds an appropriate match
of accept(Triangle) and this is the information it relays to the JVM at runtime.
At runtime, the JVM is searching for accept(Triangle) in the runtime class of r1 which
is RayTracingRenderer. Since this particular method is overridden in this class, it will run
the overridden method and print "Modified r1.accept(a) call!"
*/