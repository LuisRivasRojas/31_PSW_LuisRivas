package com.luis.testing;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class CalificacionTest {

    Calificacion calificacion = new Calificacion();

    // ─── calcularPromedio() ───────────────────────────────────────────

    // Caso 1: 15, 14, 16 → esperado 15
    @Test
    public void testPromedio_15_14_16() {
        double resultado = calificacion.calcularPromedio(15, 14, 16);
        assertEquals(15.0, resultado);
    }

    // Caso 2: 10, 12, 14 → esperado 12
    @Test
    public void testPromedio_10_12_14() {
        double resultado = calificacion.calcularPromedio(10, 12, 14);
        assertEquals(12.0, resultado);
    }

    // Caso 3: 20, 18, 16 → esperado 18
    @Test
    public void testPromedio_20_18_16() {
        double resultado = calificacion.calcularPromedio(20, 18, 16);
        assertEquals(18.0, resultado);
    }

    // ─── estaAprobado() ──────────────────────────────────────────────

    // Caso 4: promedio 15 → true (aprobado)
    @Test
    public void testAprobado_promedio15() {
        assertTrue(calificacion.estaAprobado(15));
    }

    // Caso 5: promedio 13 → true (límite exacto)
    @Test
    public void testAprobado_promedio13_limite() {
        assertTrue(calificacion.estaAprobado(13));
    }

    // Caso 6: promedio 12 → false (reprobado)
    @Test
    public void testReprobado_promedio12() {
        assertFalse(calificacion.estaAprobado(12));
    }

    // Caso 7: promedio 5 → false (reprobado)
    @Test
    public void testReprobado_promedio5() {
        assertFalse(calificacion.estaAprobado(5));
    }

    // ─── Caso 8: RETO ────────────────────────────────────────────────
    // Promedio 0 → false
    // Justificación: un alumno con notas todas en cero tiene promedio 0,
    // que es menor a 13, por lo tanto debe estar reprobado.
    @Test
    public void testReprobado_promedio0() {
        assertFalse(calificacion.estaAprobado(0));
    }
}
