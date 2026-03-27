# 🐾 Sistema de Base de Datos de Mascotas

## 📌 Descripción

This is a complete database system for managing pets (dogs and cats) with owner information, breeds, colors, and more. Perfect for veterinary clinics, pet shops, or personal pet management.

## 🎯 Características

✅ **Registro de Mascotas**: Almacena información completa de perros y gatos  
✅ **20 Razas de Perros**: Todas las razas principales de perros  
✅ **20 Razas de Gatos**: Todas las razas principales de gatos  
✅ **Múltiples Colores**: Cada mascota puede tener hasta 5 colores diferentes  
✅ **Gestión de Dueños**: Registra propietarios de mascotas  
✅ **Colores Predefinidos**: 15+ colores estándar para mascotas  
✅ **Código Java**: Clase DAO completa para conectar con la base de datos  

## 📂 Archivos Incluidos

| Archivo | Descripción |
|---------|-------------|
| `mascota_database.sql` | Script SQL completo con toda la estructura |
| `MascotaDAO.java` | Clase Java para gestionar la BD |
| `EjemploUso.java` | Ejemplo de cómo usar la clase MascotaDAO |
| `GUIA_INSTALACION.md` | Guía detallada de instalación y uso |
| `README.md` | Este archivo |

## 🚀 Inicio Rápido

### 1. Crear la base de datos
```bash
mysql -u root -p < mascota_database.sql
```

### 2. Compilar clases Java
```bash
javac -cp lib/mysql-connector-java-8.0.33.jar MascotaDAO.java
javac -cp lib/mysql-connector-java-8.0.33.jar EjemploUso.java
```

### 3. Ejecutar
```bash
java -cp .:lib/mysql-connector-java-8.0.33.jar EjemploUso
```

## 📊 Estructura de Datos

### Tablas principales:
- **duenos**: Guardos de las mascotas
- **especies**: Perro o Gato
- **razas**: 20 razas de perros, 20 de gatos
- **mascotas**: Información de cada mascota
- **colores**: 15+ colores predefinidos
- **mascota_colores**: Relación muchos-a-muchos (permite múltiples colores)

## 💻 Uso en Java

```java
// Crear instancia
MascotaDAO dao = new MascotaDAO();

// Registrar mascota
dao.registrarMascota(1, "Max", 1, 2, "Macho", 3);

// Ver razas
List<String> razas = dao.obtenerRazasPorEspecie(1);

// Ver colores
List<String> colores = dao.obtenerTodosLosColores();

// Agregar color a mascota
dao.agregarColorAMascota(1, 3, 70, "Cuerpo");

// Cerrar
dao.cerrar();
```

## 📋 Razas de Perros

Labrador Retriever, Bulldog Francés, Golden Retriever, Bulldog Inglés, Poodle, Beagle, Yorkshire Terrier, Dachshund, Rottweiler, Pastor Alemán, Doberman, Chihuahua, Cocker Spaniel, Schnauzer, Boxer, Shih Tzu, Husky Siberiano, San Bernardo, Cocker Spaniel Inglés, Pug

## 🐱 Razas de Gatos

Persa, Siamés, Gato Doméstico Europeo, Maine Coon, Ragdoll, Bengalí, Abisinio, Scottish Fold, Birmania, Esfinge, Manx, Sphynx, Angora Turco, Tonkinés, Bombay, Siam, Korat, Turco Van, Sagrado de Birmania, Gato de Albergue

## 🎨 Colores Disponibles

Negro, Blanco, Gris, Rojo/Naranja, Café/Marrón, Crema, Chocolate, Dorado, Plateado, Azul Gris, Rojizo, Tricolor, Moteado, Rayado, Puntas Oscuras

## 📖 Documentación Completa

Para más detalles sobre instalación, configuración y uso avanzado, consulta [GUIA_INSTALACION.md](./GUIA_INSTALACION.md)

## ⚙️ Requisitos

- MySQL 5.7+
- Java 8+
- MySQL Connector/J

## 📝 Licencia

Este proyecto es de uso libre para fines educativos y comerciales.

---

**¡Listo para usar! 🚀 Sigue la guía de instalación para empezar.**
