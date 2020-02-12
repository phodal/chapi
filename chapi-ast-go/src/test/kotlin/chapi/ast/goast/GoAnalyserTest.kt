package chapi.ast.goast

import org.junit.jupiter.api.Test

internal class GoAnalyserTest {
    @Test
    fun analysis() {
var helloworld = """
package main
import "fmt"
func main() {
    fmt.Println("hello world")
}
"""
        GoAnalyser().analysis(helloworld)
    }
}
