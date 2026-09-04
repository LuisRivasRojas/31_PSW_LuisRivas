# Reto de Refactorización — PedidoService

Proyecto Java desarrollado como parte del reto de refactorización del curso de **Pruebas de Software**, aplicando técnicas de mejora de código sin alterar el comportamiento funcional del sistema.

---

## Descripción del Proyecto

El proyecto implementa la clase `PedidoService` con lógica de negocio para el manejo de pedidos: cálculo de totales con descuentos, clasificación por estado y validación de datos de entrada.

El objetivo del reto fue identificar **code smells** en el código original y aplicar **técnicas de refactorización** para mejorar la legibilidad y mantenibilidad, manteniendo el 100% de los tests pasando.

---

## Estructura del Proyecto

```
reto-refactorizacion/
├── src/
│   ├── main/java/pe/edu/vallegrande/
│   │   └── PedidoService.java          # Clase principal refactorizada
│   └── test/java/pe/edu/vallegrande/
│       └── PedidoServiceTest.java      # Suite de pruebas JUnit 5
├── target/
│   └── site/jacoco/index.html          # Reporte de cobertura JaCoCo
├── pom.xml                             # Configuración Maven + JaCoCo
├── README.md                           # Este archivo
└── REPORTE_REFACTORIZACION.md          # Reporte completo del reto
```

---

## Tecnologías Utilizadas

| Herramienta | Versión | Uso |
|---|---|---|
| Java | 17 | Lenguaje principal |
| Maven | 3.x | Gestión de dependencias y build |
| JUnit Jupiter | 5.10.2 | Pruebas unitarias |
| JaCoCo | 0.8.12 | Cobertura de código |

---

## Cómo Ejecutar el Proyecto

### 1. Clonar o descargar el proyecto
```bash
git clone <url-del-repositorio>
cd reto-refactorizacion
```

### 2. Ejecutar los tests
```bash
mvn clean test
```

### 3. Ver el reporte de cobertura JaCoCo
Abrir en el navegador:
```
target/site/jacoco/index.html
```

### Resultado esperado
```
Tests run: 4, Failures: 0, Errors: 0, Skipped: 0
BUILD SUCCESS
```

---

## Code Smells Identificados y Corregidos

| # | Smell | Método | Técnica Aplicada |
|---|---|---|---|
| 1 | Variable `x` sin nombre descriptivo | `calcularTotal()` | Renombrar variable |
| 2 | Números mágicos `0.90`, `0.95`, `10` | `calcularTotal()` | Extraer constante |
| 3 | Tres `if` separados redundantes | `validarPedido()` | Simplificar condiciones |
| 4 | `equals("")` en lugar de `isEmpty()` | `validarPedido()` | Mejorar expresividad |

---

## Métricas de Cobertura JaCoCo

| Métrica | Cobertura |
|---|---|
| Instructions | 78% |
| Branches | 54% |
| Methods | 100% |
| Classes | 100% |

---
## Reporte generado - index.html
<img width="1917" height="562" alt="image" src="https://github.com/user-attachments/assets/7d9f5ea1-89b9-4cf8-b701-f4cc46203872" />



## Regla Fundamental del Reto

> ⚠️ **El comportamiento del programa NO debe cambiar.**
> Solo se mejora la estructura interna: nombres, organización y legibilidad.
> Los tests deben seguir pasando al 100% antes y después de refactorizar.

---

## Pruebas Unitarias

La suite incluye 4 tests que validan el comportamiento completo de `PedidoService`:

| Test | Qué verifica |
|---|---|
| `debeCalcularTotal` | Cálculo básico sin descuentos |
| `debeAplicarDescuentoClienteFrecuente` | Descuento del 10% para clientes frecuentes |
| `debeObtenerEstadoMediano` | Clasificación de pedido como MEDIANO |
| `debeValidarPedidoCorrecto` | Validación de pedido con datos correctos |

---

## Autor - Luis Rivas

Desarrollado como parte del **Reto de Refactorización**  
Curso: Pruebas de Software  
Instituto: Valle Grande  
