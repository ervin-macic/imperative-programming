// Abstract class representing a generic Shape
abstract class Shape {
  def area: Double // Abstract method
}

// Trait for objects that can fly
trait Flyable {
  def fly(): Unit
}

// Base class Animal with a method that can be overridden
class Animal(val name: String) {
  def speak(): Unit = println("Some generic animal sound")
}

// Derived class Dog that extends Animal and overrides speak
class Dog(name: String) extends Animal(name) {
  override def speak(): Unit = println(s"$name says: Woof!")
}

// Companion object for Animal (Factory pattern)
object Animal {
  def apply(name: String, isDog: Boolean = false): Animal =
    if (isDog) new Dog(name) else new Animal(name)
}

// Class with primary and secondary constructors
class Car(val brand: String, val model: String, val year: Int) {
  def this(brand: String, model: String) = this(brand, model, 2024) // Default year
  def info(): String = s"$brand $model ($year)"
}

// Circle extends Shape and provides an implementation for area
class Circle(val radius: Double) extends Shape {
  override def area: Double = Math.PI * radius * radius
}

// Bird extends Animal and implements Flyable
class Bird(name: String) extends Animal(name) with Flyable {
  override def speak(): Unit = println(s"$name chirps!")
  def fly(): Unit = println(s"$name is flying high!")
}

// Main object to run the program
object Main {
  def main(args: Array[String]): Unit = {
    // Using a basic class
    val person = new Person("Alice", 25)
    person.greet()

    // Using a class with public fields
    val car1 = new Car("Toyota", "Corolla", 2020)
    val car2 = new Car("Honda", "Civic") // Uses secondary constructor
    println(car1.info())
    println(car2.info())

    // Using a companion object to create Animals
    val genericAnimal = Animal("Mystery")
    val dog = Animal("Buddy", isDog = true)

    genericAnimal.speak() // Some generic animal sound
    dog.speak()           // Buddy says: Woof!

    // Using an abstract class
    val circle = new Circle(5)
    println(s"Circle area: ${circle.area}")

    // Using a trait
    val bird = new Bird("Sparrow")
    bird.speak()
    bird.fly()
  }
}

// Basic Person class with a method
class Person(val name: String, val age: Int) {
  def greet(): Unit = println(s"Hello, my name is $name and I am $age years old.")
}
