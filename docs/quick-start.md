---
layout: default
title: Quick Start
nav_order: 2
permalink: /quick-start
---

# Quick Start

Maven version: ![Maven Central](https://img.shields.io/maven-central/v/com.phodal.chapi/chapi-domain)

## 1. Declare Dependencies

Declare the dependencies in your build.gradle file

```groovy
dependencies {
    implementation 'com.phodal.chapi:chapi-ast-java:2.3.6'
    implementation 'com.phodal.chapi:chapi-domain:2.3.6'
}
```

build.gradle.kts

```kotlin
dependencies {
    implementation("com.phodal.chapi:chapi-ast-java:2.3.6")
    implementation("com.phodal.chapi:chapi-domain:2.3.6")
}
```

Languages:

Sure, here is the information in tabular form:

| Language   | Repository               | Production ready |
|------------|--------------------------|------------------|
| Java       | `chapi-ast-java`         | ✳️               |
| Kotlin     | `chapi-ast-kotlin`       | ✳️               |
| JavaScript | `chapi-ast-typescript`   | ✳️               |
| TypeScript | `chapi-ast-typescript`   | ✳️               |
| Python     | `chapi-ast-python`       | ✴️               |
| Rust       | `chapi-ast-rust`         | ✴️               |
| Go         | `chapi-ast-go`           | ✴️               |
| C          | `chapi-ast-c`            | ✴️               |
| Scala      | `chapi-ast-scala`      	 | 🔨               |
| C++        | `chapi-ast-cpp`        	 | 🔨               |
| C#         | `chapi-ast-csharp`     	 | 🔨               |

## 2. Parse Source Code

Use the following code to parse the source code:

```java
import chapi.ast.javaast.JavaAnalyser;

// main
public static void main(String[] args) {
    JavaAnalyser analyser = new JavaAnalyser();
    analyser.parse("public class HelloWorld { public static void main(String[] args) { System.out.println(\"Hello, World\"); } }", "HelloWorld.java");
}
```

## 3. Use the AST

Use the following code to use the AST:

```java
import chapi.ast.javaast.JavaAnalyser;

// main
public static void main(String[] args) {
    JavaAnalyser analyser = new JavaAnalyser();
    List<CodeContainer> containerList= analyser.parse("public class HelloWorld { public static void main(String[] args) { System.out.println(\"Hello, World\"); } }", "HelloWorld.java");
    // handle the containerList
```
