use std::time::Instant;
use wasm_bindgen::prelude::wasm_bindgen;

// Funzione iterativa per calcolare il n-esimo numero di Fibonacci
#[wasm_bindgen]
pub fn fibonacci(n: u32) -> u32 {
    if n <= 1 {
        return n;
    }
    let mut a = 0;
    let mut b = 1;
    for _ in 2..=n {
        let temp = a + b;
        a = b;
        b = temp;
    }
    b
}

fn main() {

    let n: u32 = 2147483647;

    // Avvio del cronometro
    let start = Instant::now();

    // Stampa dei termini della sequenza
    println!("Sequenza di Fibonacci fino a {} termini:", n);
    for i in 0..n {
        fibonacci(i);
    }

    // Fine del cronometro
    let duration = start.elapsed();
    println!("\nTempo di esecuzione: {:.6} secondi", duration.as_secs_f64());
}
