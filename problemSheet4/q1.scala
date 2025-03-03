abstract class Shape {
  def resize(factor: Double): Shape
}
class Rectangle(private var width: Double, private var height: Double) extends Shape {
    def setWidth(w: Double) : Unit = { width = w }
    def setHeight(h: Double) : Unit = { height = h }
    def getWidth() : Double = width
    def getHeight() : Double = height
    def isSquare() : Boolean = { width == height }
    override def resize(factor: Double) : Rectangle = new Rectangle(getWidth * factor, getHeight * factor)
}
class Square(private var sideLength: Double) extends Shape {
    def setSide(s: Double): Unit = { sideLength = s }
    def getSide(): Double = sideLength
    override def resize(factor: Double): Square = new Square(getSide * factor)
}
class Ellipse(private var majorAxis: Double, private var minorAxis: Double) extends Shape {
    def setMajorAxis(major: Double) : Unit = { majorAxis = major }
    def setMinorAxis(minor: Double) : Unit = { minorAxis = minor }
    def getMajorAxis() : Double = majorAxis
    def getMinorAxis() : Double = minorAxis
    def isCircle() : Boolean = (majorAxis == minorAxis)
    override def resize(factor: Double) : Ellipse = new Ellipse(majorAxis * factor, minorAxis * factor)
}
class Circle(private var radius: Double) extends Shape{
    def setRadius(r: Double) = { radius = r }
    def getRadius() : Double = { radius }  
    override def resize(factor: Double) : Circle = new Circle(radius * factor)
}

object ShapeUtils {
  def findSquares(shapes: List[Shape]): List[Shape] = {
        shapes.filter {
            case r: Rectangle if r.isSquare() => true
            case _: Square => true
            case _ => false
        }
    }

  def findCircles(shapes: List[Shape]): List[Shape] = {
        shapes.filter {
            case e: Ellipse if e.isCircle() => true
            case _: Circle => true
            case _ => false
        }
    }
}

object Main {
  def main(args: Array[String]): Unit = {
    val shapes: List[Shape] = List(
      new Rectangle(5, 10),
      new Square(7),
      new Ellipse(8, 4),
      new Ellipse(3,3),
      new Circle(6),
      new Rectangle(3, 3)
    )

    val squares = ShapeUtils.findSquares(shapes)
    println(s"Found ${squares.length} squares")
    
    val circles = ShapeUtils.findCircles(shapes)
    println(s"Found ${circles.length} circles")
    
    val resizedCircle = shapes(4).resize(2)
    println(s"Original radius: ${resizedCircle.asInstanceOf[Circle].getRadius()}")
    println(s"Resized radius: ${resizedCircle.asInstanceOf[Circle].getRadius()}")
  }
}

/*
This design accurately captures the properties of the shapes and tries not to model the relationshis between them.
I chose not to derive a square from a rectangle (even though mathematically it makes sense) because calling 
setWidth and setHeight on a square would induce potentially unexpected behaviour (i.e. would either turn a square into a 
rectangle or change both the height and width of the square). Same for ellipse and circle.
I chose to have an abstract class Shape so that I can have lists of these shapes.
*/