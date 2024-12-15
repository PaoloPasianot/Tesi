# C version

``` bash
   emcc main.c -o fibonacci.wasm -O3 -s WASM=1 -s STANDALONE_WASM
```

### Test

Eseguzione della sequenza in: 0.000013 secondi