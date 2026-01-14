// C# 10 Global using test

global using System;
global using System.Collections.Generic;
global using System.Linq;

namespace MyApp;

public class Program
{
    public static void Main()
    {
        var list = new List<string> { "Hello", "World" };
        Console.WriteLine(string.Join(" ", list));
    }
}

