library example;

import 'dart:async';
import 'dart:collection' show LinkedHashMap;
import 'package:flutter/material.dart' as material;

/// A simple Person class
class Person {
  String name;
  int age;
  
  Person(this.name, this.age);
  
  Person.fromJson(Map<String, dynamic> json)
      : name = json['name'],
        age = json['age'];
  
  factory Person.anonymous() {
    return Person('Anonymous', 0);
  }
  
  String greet() {
    return 'Hello, my name is $name';
  }
  
  @override
  String toString() => 'Person($name, $age)';
}

/// A student that extends Person
class Student extends Person with Printable implements Comparable<Student> {
  String school;
  
  Student(String name, int age, this.school) : super(name, age);
  
  @override
  int compareTo(Student other) {
    return name.compareTo(other.name);
  }
  
  void study(String subject) {
    print('$name is studying $subject at $school');
  }
}

/// A mixin for printable objects
mixin Printable {
  void printInfo() {
    print(toString());
  }
}

/// An abstract class
abstract class Animal {
  String get species;
  
  void makeSound();
}

/// An enum
enum Status {
  pending,
  active,
  completed,
  cancelled,
}

/// An extension on String
extension StringExtension on String {
  String capitalize() {
    if (isEmpty) return this;
    return '${this[0].toUpperCase()}${substring(1)}';
  }
}

/// A typedef
typedef JsonMap = Map<String, dynamic>;
typedef Callback<T> = void Function(T value);

/// Top-level function
void doSomething() {
  print('Doing something...');
}

/// Top-level getter
String get appVersion => '1.0.0';

/// Top-level variable
const String appName = 'DartApp';
