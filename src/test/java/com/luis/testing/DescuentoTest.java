package com.luis.testing;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DescuentoTest {

    Descuento descuento = new Descuento();

    // Caso 1: precio=100, descuento=10% → esperado 90
    @Test
    public void testDescuento10Porciento() {
        double resultado = descuento.calcularPrecioFinal(100, 10);
        assertEquals(90.0, resultado);
    }

    // Caso 2: precio=200, descuento=20% → esperado 160
    @Test
    public void testDescuento20Porciento() {
        double resultado = descuento.calcularPrecioFinal(200, 20);
        assertEquals(160.0, resultado);
    }

    // Caso 3: precio=50, descuento=0% → esperado 50 (sin descuento)
    @Test
    public void testSinDescuento() {
        double resultado = descuento.calcularPrecioFinal(50, 0);
        assertEquals(50.0, resultado);
    }

    // Caso 4: precio=500, descuento=50% → esperado 250
    @Test
    public void testDescuento50Porciento() {
        double resultado = descuento.calcularPrecioFinal(500, 50);
        assertEquals(250.0, resultado);
    }

    // Caso 5 (propio): precio=300, descuento=100% → esperado 0
    // Justificación: un descuento del 100% significa producto gratis,
    // el precio final debe ser exactamente 0. Es un caso límite importante
    // para verificar que la fórmula no da valores negativos ni errores.
    @Test
    public void testDescuento100Porciento() {
        double resultado = descuento.calcularPrecioFinal(300, 100);
        assertEquals(0.0, resultado);
    }
}
