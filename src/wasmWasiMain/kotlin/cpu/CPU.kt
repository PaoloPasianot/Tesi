package cpu

/**
 * Nth number of the Fibonacci sequence
 * @param n Number position in the sequence
 */
@WasmExport
fun fibonacciSingleWASM(n: Int): Int {
    if (n <= 1) return n
    var a = 0
    var b = 1
    var result = 0
    for (i in 2..n) {
        result = a + b
        a = b
        b = result
    }
    return result
}

/**
 * Calculate the factorial of the number N
 * @param n Number on which the factual is calculated
 */
@WasmExport
fun factorialSingleWASM(n: Int): Int {
    if (n < 0) return -1 // Error
    if (n == 0) return 1 // 0! = 1
    var result = 1
    for (i in 1..n) {
        result *= i
    }
    return result
}

private val localMemory = IntArray(4096) //(4k integers = 16KB)

@WasmExport
fun multiplyMatrices(
    aOffset: Int, aRows: Int, aCols: Int,
    bOffset: Int, bCols: Int,
    cOffset: Int
) {
    val a = IntArray(aRows * aCols)
    val b = IntArray(aCols * bCols)
    val c = IntArray(aRows * bCols)

    for (i in 0 until aRows) {
        for (j in 0 until aCols) {
            a[i * aCols + j] = getMemory(aOffset + (i * aCols + j) * 4)
        }
    }

    for (i in 0 until aCols) {
        for (j in 0 until bCols) {
            b[i * bCols + j] = getMemory(bOffset + (i * bCols + j) * 4)
        }
    }

    for (i in 0 until aRows) {
        for (j in 0 until bCols) {
            var sum = 0
            for (k in 0 until aCols) {
                sum += a[i * aCols + k] * b[k * bCols + j]
            }
            c[i * bCols + j] = sum
        }
    }

    for (i in 0 until aRows) {
        for (j in 0 until bCols) {
            setMemory(cOffset + (i * bCols + j) * 4, c[i * bCols + j])
        }
    }
}

@WasmExport
fun setMemory(offset: Int, value: Int) {
    val index = offset / 4 // Ogni 4 byte corrisponde a 1 Int
    if (index < 0 || index >= localMemory.size) return // Evita out-of-bounds
    localMemory[index] = value
}

@WasmExport
fun getMemory(offset: Int): Int {
    val index = offset / 4 // Ogni 4 byte corrisponde a 1 Int
    if (index < 0 || index >= localMemory.size) return -1 // Evita out-of-bounds
    return localMemory[index]
}