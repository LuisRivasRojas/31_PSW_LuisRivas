package com.luis.testing;

public class Producto {

    private double precio;
    private int cantidad;

    public Producto(double precio, int cantidad) {
        this.precio = precio;
        this.cantidad = cantidad;
    }

    // Método de cálculo: precio * cantidad
    public double calcularTotal() {
        return precio * cantidad;
    }

    public double getPrecio() {
        return precio;
    }

    public int getCantidad() {
        return cantidad;
    }
}
