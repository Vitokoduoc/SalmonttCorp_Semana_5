<p>
  <img src="https://www.duoc.cl/wp-content/uploads/2022/09/logo-0.png" width="300"/>
</p>

# 🐟 SalmonttApp  
### Sistema de Gestión de Centros de Cultivo - Maven + Java + POO  
**Autor:** Víctor Valenzuela  
**Institución:** Duoc UC  
**Asignatura:** Fundamentos de Programación / POO  
**Evaluación Semana 5 – Proyecto Sumativo**

---

## 📌 **Descripción del Proyecto**

**SalmonttApp** es una aplicación desarrollada en Java orientada a objetos que permite administrar centros de cultivo salmonícolas.  
El sistema carga datos desde archivos **TXT** y **CSV**, genera estructuras de objetos relacionadas mediante **composición** y permite realizar:

- Listado completo de centros
- Búsqueda por comuna
- Filtros por producción
- Ordenamiento alfabético
- Impresión tabular profesional
- Uso de clases compuestas:  
  **CentroCultivo → Producto → Dirección → Ruta**

Además, el proyecto está configurado con **Maven** y puede generar un **.jar ejecutable**.

---

## 🎯 **Objetivos de la Evaluación**

✔ Aplicar Programación Orientada a Objetos  
✔ Uso de clases compuestas  
✔ Lectura desde archivos externos  
✔ Manejo de colecciones  
✔ Uso de Stream API (búsqueda, filtros, ordenamiento)  
✔ Arquitectura por capas  
✔ Javadoc en todas las clases  
✔ README profesional  
✔ Generación y ejecución de un `.jar`  
✔ Implementación completa para demostración en video  

---

## 📦 **Arquitectura del Proyecto**

El proyecto sigue una estructura profesional basada en paquetes:
```
src/main/java/
└── com.salmonttcorp
├── app
│ └── Main.java
├── model
│ ├── CentroCultivo.java
│ ├── Producto.java
│ ├── Direccion.java
│ └── Ruta.java
├── service
│ └── CentroService.java
└── util
└── GestorDatos.java

src/main/resources/
├── datosCentros.txt
└── datosCentros.csv
```
---

### 🧩 **Descripción de los paquetes**

| Paquete | Rol |
|--------|-----|
| **app** | Contiene la clase principal `Main` (punto de entrada) |
| **model** | Modelo del dominio con relaciones por composición |
| **service** | Lógica de negocio: filtros, búsquedas, ordenamiento |
| **util** | Carga de archivos TXT/CSV y construcción del modelo |
| **resources** | Archivos de datos externos |

---

## 🧬 **Composición del Modelo**

La aplicación utiliza **composición** para estructurar su dominio:
```
CentroCultivo
├── Producto
├── Direccion
└── Ruta
```


Cada clase contiene Javadoc completo y validaciones.

---

## 🗂️ **Diagrama UML del Sistema (PlantUML)**

> Puedes copiar esto en cualquier renderizador PlantUML, o usar la extensión de GitHub.

```plantuml
@startuml

class CentroCultivo {
    - String nombreCentro
    - String comuna
    - int produccion
    - Producto producto
    - Direccion direccion
    - Ruta rutaAsociada
}

class Producto {
    - String nombreProducto
    - String tipoProducto
    - double precioProducto
}

class Direccion {
    - String calle
    - String numero
    - String comuna
    - String region
}

class Ruta {
    - String nomRuta
    - String responsable
    - String fecha
    - Direccion sucursal
}

CentroCultivo *-- Producto
CentroCultivo *-- Direccion
CentroCultivo *-- Ruta
Ruta --> Direccion

@enduml

```
