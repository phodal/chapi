// Dart 3 Features Example
library dart3_features;

// Sealed class example
sealed class Shape {
  const Shape();
}

final class Circle extends Shape {
  final double radius;
  const Circle(this.radius);
  
  double get area => 3.14159 * radius * radius;
}

final class Rectangle extends Shape {
  final double width;
  final double height;
  
  const Rectangle(this.width, this.height);
  
  double get area => width * height;
}

base class Vehicle {
  final String brand;
  
  Vehicle(this.brand);
  
  void move() {
    print('$brand is moving');
  }
}

// Pattern matching with switch expression
String describeShape(Shape shape) {
  return switch (shape) {
    Circle(radius: var r) => 'Circle with radius $r',
    Rectangle(width: var w, height: var h) => 'Rectangle ${w}x$h',
  };
}

// Records
(int, String) getUserInfo() {
  return (42, 'John Doe');
}

({String name, int age}) getNamedRecord() {
  return (name: 'Jane', age: 25);
}

// Record patterns
void processRecord() {
  var (id, name) = getUserInfo();
  print('User: $name (ID: $id)');
  
  var (:name, :age) = getNamedRecord();
  print('$name is $age years old');
}

// Class modifiers
interface class Flyable {
  void fly();
}

mixin class Swimmable {
  void swim() {
    print('Swimming');
  }
}

base mixin Walkable {
  void walk() {
    print('Walking');
  }
}

// Multiple capabilities
class Duck extends Vehicle with Walkable, Swimmable implements Flyable {
  Duck() : super('Duck Brand');
  
  @override
  void fly() {
    print('Duck is flying');
  }
}

// Extension type
extension type UserId(int id) {
  UserId.fromString(String value) : id = int.parse(value);
  
  bool get isValid => id > 0;
}

// Async generators
Stream<int> countStream(int max) async* {
  for (var i = 1; i <= max; i++) {
    await Future.delayed(Duration(milliseconds: 100));
    yield i;
  }
}

// Sync generators  
Iterable<int> getRange(int start, int end) sync* {
  for (var i = start; i <= end; i++) {
    yield i;
  }
}
