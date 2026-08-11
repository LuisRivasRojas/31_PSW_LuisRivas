package com.luis.testing;

public class Calificacion {

    public double calcularPromedio(double nota1, double nota2, double nota3) {
        return (nota1 + nota2 + nota3) / 3;
    }

    public boolean estaAprobado(double promedio) {
        return promedio >= 13;
    }
}
