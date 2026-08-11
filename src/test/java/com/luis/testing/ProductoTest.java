package com.luis.testing;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProductoTest {

    // Escenario A: Prueba Exitosa
    // Precio = 50, Cantidad = 3 → Resultado esperado = 150
    @Test
    public void testCalcularTotal() {
        Producto producto = new Producto(50, 3);
        double resultado = producto.calcularTotal();
        assertEquals(150.0, resultado);
    }
}
