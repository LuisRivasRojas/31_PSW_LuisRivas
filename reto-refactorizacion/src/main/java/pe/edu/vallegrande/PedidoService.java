package pe.edu.vallegrande;

public class PedidoService {

    private static final double DESCUENTO_CLIENTE_FRECUENTE = 0.90;
    private static final double DESCUENTO_CANTIDAD_MAYOR = 0.95;
    private static final int CANTIDAD_MINIMA_DESCUENTO = 10;

    public double calcularTotal(double precio, int cantidad, boolean clienteFrecuente) {

        if (cantidad <= 0) {
            return 0;
        }

        double totalCalculado = precio * cantidad;

        if (clienteFrecuente) {
            totalCalculado = totalCalculado * DESCUENTO_CLIENTE_FRECUENTE;
        }

        if (cantidad >= CANTIDAD_MINIMA_DESCUENTO) {
            totalCalculado = totalCalculado * DESCUENTO_CANTIDAD_MAYOR;
        }

        return totalCalculado;
    }

    public String obtenerEstado(double total) {

        if (total <= 0) {
            return "ERROR";
        } else if (total < 100) {
            return "PEQUEÑO";
        } else if (total < 500) {
            return "MEDIANO";
        } else {
            return "GRANDE";
        }
    }

    public boolean validarPedido(String producto, int cantidad) {
        boolean productoValido = producto != null && !producto.isEmpty();
        boolean cantidadValida = cantidad > 0;
        return productoValido && cantidadValida;
    }
}
