import kotlin.wasm.WasmImport
import kotlin.wasm.unsafe.Pointer
import kotlin.wasm.unsafe.UnsafeWasmMemoryApi
import kotlin.wasm.unsafe.withScopedMemoryAllocator

fun main() {
    println("Hello from Kotlin via WASI")
}


// We need it to run WasmEdge with the _initialize function
@WasmExport
fun dummy() {}