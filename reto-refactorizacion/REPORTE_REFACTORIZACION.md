# Reporte de Refactorización — PedidoService

**Proyecto:** reto-refactorizacion  
**Paquete:** pe.edu.vallegrande  
**Clase analizada:** PedidoService.java  
**Herramienta de cobertura:** JaCoCo 0.8.12  
**Framework de pruebas:** JUnit Jupiter 5.10.2  

---

## 1. Situación Inicial

### Descripción del proyecto
El proyecto consiste en una clase `PedidoService` con tres métodos de lógica de negocio para el manejo de pedidos:
- `calcularTotal()` — calcula el precio total aplicando descuentos
- `obtenerEstado()` — clasifica un pedido según su monto
- `validarPedido()` — verifica que los datos de un pedido sean correctos

### Código inicial — PedidoService.java

```java
public class PedidoService {

    public double calcularTotal(double precio, int cantidad, boolean clienteFrecuente) {
        double x = 0;
        if (cantidad > 0) {
            x = precio * cantidad;
        }
        if (clienteFrecuente) {
            x = x * 0.90;
        }
        if (cantidad >= 10) {
            x = x * 0.95;
        }
        return x;
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
        if (producto == null) {
            return false;
        }
        if (producto.equals("")) {
            return false;
        }
        if (cantidad <= 0) {
            return false;
        }
        return true;
    }
}
```

---

## 2. Reporte JaCoCo Inicial

> **Comando ejecutado:** `mvn clean test`  
> **Archivo generado:** `target/site/jacoco/index.html`

| Métrica | Missed | Total | Cobertura |
|---|---|---|---|
| Instructions | 15 | 70 | **78%** |
| Branches | 10 | 22 | **54%** |
| Methods | 0 | 4 | **100%** |
| Classes | 0 | 1 | **100%** |

---

## 3. Análisis de Code Smells

Se identificaron los siguientes problemas de diseño en el código inicial:

### Smell #1 — Nombre de variable sin significado semántico
- **Ubicación:** método `calcularTotal()`, línea 6
- **Problema:** La variable `x` no comunica qué representa. Es un nombre genérico que obliga al lector a deducir su propósito.
- **Técnica de refactorización:** Renombrar variable (*Rename Variable*)

### Smell #2 — Números mágicos sin contexto
- **Ubicación:** método `calcularTotal()`, líneas 11 y 15
- **Problema:** Los valores `0.90`, `0.95` y `10` aparecen directamente en el código sin explicar qué representan (¿10% de descuento? ¿5%? ¿a partir de cuántas unidades?).
- **Técnica de refactorización:** Extraer constante (*Extract Constant*)

### Smell #3 — Método con responsabilidad duplicada
- **Ubicación:** método `validarPedido()`, líneas 25-33
- **Problema:** Tres bloques `if` separados que hacen lo mismo: retornar `false`. Se puede unificar en una sola expresión booleana más legible.
- **Técnica de refactorización:** Simplificar condiciones (*Simplify Conditional*)

### Smell #4 — Uso de `equals("")` en lugar de `isEmpty()`
- **Ubicación:** método `validarPedido()`, línea 29
- **Problema:** `producto.equals("")` es menos expresivo y puede lanzar `NullPointerException` si no se controla bien el orden. `isEmpty()` comunica mejor la intención.
- **Técnica de refactorización:** Renombrar / mejorar expresividad

---

## 4. Refactorizaciones Realizadas

### Refactorización 1 — Extracción de constantes

**Antes:**
```java
x = x * 0.90;
x = x * 0.95;
if (cantidad >= 10)
```

**Después:**
```java
private static final double DESCUENTO_CLIENTE_FRECUENTE = 0.90;
private static final double DESCUENTO_CANTIDAD_MAYOR = 0.95;
private static final int CANTIDAD_MINIMA_DESCUENTO = 10;
```

**Beneficio:** Cada número ahora tiene un nombre que explica su propósito. Si el negocio cambia el porcentaje, se modifica en un solo lugar.

---

### Refactorización 2 — Renombrar variable `x`

**Antes:**
```java
double x = 0;
if (cantidad > 0) {
    x = precio * cantidad;
}
```

**Después:**
```java
if (cantidad <= 0) {
    return 0;
}
double totalCalculado = precio * cantidad;
```

**Beneficio:** El nombre `totalCalculado` comunica exactamente qué almacena la variable. Además se usa un *guard clause* para salir temprano si la cantidad no es válida.

---

### Refactorización 3 — Simplificar `validarPedido()`

**Antes:**
```java
if (producto == null) { return false; }
if (producto.equals("")) { return false; }
if (cantidad <= 0) { return false; }
return true;
```

**Después:**
```java
boolean productoValido = producto != null && !producto.isEmpty();
boolean cantidadValida = cantidad > 0;
return productoValido && cantidadValida;
```

**Beneficio:** El método se reduce de 8 líneas a 3. Las variables booleanas nombradas (`productoValido`, `cantidadValida`) hacen que el código se lea como una oración en español.

---

## 5. Código Refactorizado Final

```java
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
```

---

## 6. Reporte JaCoCo Final

> **Comando ejecutado:** `mvn clean test` (después de refactorizar)  
> **Archivo generado:** `target/site/jacoco/index.html`

| Métrica | Missed | Total | Cobertura |
|---|---|---|---|
| Instructions | 15 | 70 | **78%** |
| Branches | 10 | 22 | **54%** |
| Methods | 0 | 4 | **100%** |
| Classes | 0 | 1 | **100%** |

---

## 7. Comparativa de Métricas

| Métrica | Antes | Después | Resultado |
|---|---|---|---|
| Instructions | 78% | 78% | ✅ Mantenida |
| Branches | 54% | 54% | ✅ Mantenida |
| Methods | 100% | 100% | ✅ Mantenida |
| Classes | 100% | 100% | ✅ Mantenida |
| Tests pasando | 4/4 | 4/4 | ✅ Sin regresiones |

---

## 8. Conclusiones

1. **La funcionalidad se mantuvo intacta.** Los 4 tests unitarios siguen pasando al 100% después de la refactorización, lo que confirma que el comportamiento externo del sistema no fue alterado.

2. **La legibilidad mejoró significativamente.** El código refactorizado se puede leer y entender sin necesidad de comentarios adicionales. Los nombres de variables y constantes comunican la intención del negocio.

3. **La mantenibilidad aumentó.** Al extraer los valores de descuento como constantes, cualquier cambio en las reglas de negocio (por ejemplo, cambiar el descuento del 10% al 15%) se realiza en un único punto del código.

4. **La cobertura de JaCoCo se mantuvo estable.** Esto demuestra que refactorizar no significa perder calidad de pruebas, sino mejorar la estructura sin alterar lo que el programa hace.

5. **Refactorizar ≠ reescribir.** Este ejercicio demuestra que pequeños cambios de nombres, extracción de constantes y simplificación de condiciones tienen un gran impacto en la calidad del código sin tocar su comportamiento.

---

*Documento generado como parte del Reto de Refactorización — Curso de Pruebas de Software*
