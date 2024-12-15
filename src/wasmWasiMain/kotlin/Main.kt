import cpu.factorialSingleWASM
import cpu.fibonacciSingleWASM
import cpu.multiplyMatrices
import cpu.setMemory
import kotlin.time.measureTime

fun main() {
    println("init")

    //Test fibonacci
    var fib: Int?
    val executionTimeFib = measureTime {
        fib = fibonacciSingleWASM(100000000)
    }
    println("Fibonacci 10ˆ8. Value: $fib, executionTime: $executionTimeFib")

    //Test factorial
    var fat: Int?
    val executionTimeFat = measureTime {
        fat = factorialSingleWASM(100000000)
    }
    println("Factorial 10ˆ8. Value: $fat, executionTime: $executionTimeFat")

    //Test Matrix multiplier
    val executionTimeMat = measureTime {
        val aOffset = 0
        val bOffset = 1024
        val cOffset = 2048

        for (i in 0 until 100) {
            for (j in 0 until 100) {
                setMemory(aOffset + (i * 100 + j) * 4, (i + j) % 10)
                setMemory(bOffset + (i * 100 + j) * 4, (i * j) % 10)
            }
        }

        for (i in 0 until 10) {
            multiplyMatrices(aOffset, 100, 100, bOffset, 100, cOffset)
        }
    }
    println("Matrix multiply. ExecutionTime: $executionTimeMat")

    println("end")

}


// We need it to run WasmEdge with the _initialize function
@WasmExport
fun dummy() {
}