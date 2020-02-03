package chapi.ast.goast

import org.junit.jupiter.api.Test

internal class GoIdentAppTest {
    @Test
    fun analysis() {
var helloworld = """
package main
import "fmt"
func main() {
    fmt.Println("hello world")
}
"""
        GoIdentApp().analysis(helloworld)
    }
}
