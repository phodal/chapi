// C# 9 Record types test

namespace RecordTests;

// Simple record with positional parameters
public record Person(string FirstName, string LastName);

// Record with additional members
public record Point
{
    public int X { get; init; }
    public int Y { get; init; }
}

// Record class (explicit)
public record class Employee(int Id, string Name)
{
    public string Department { get; init; }
}

// Record struct (C# 10)
public record struct Temperature(double Celsius)
{
    public double Fahrenheit => Celsius * 9 / 5 + 32;
}

