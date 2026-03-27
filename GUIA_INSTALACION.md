# 📚 Guía de Base de Datos de Mascotas

## Descripción
Sistema de base de datos MySQL para gestionar mascotas (perros y gatos) con:
- ✅ 20 razas de perros
- ✅ 20 razas de gatos  
- ✅ 15+ colores predefinidos
- ✅ Soporte para múltiples colores por mascota
- ✅ Información completa de dueños y mascotas

---

## 📋 Requisitos

1. **MySQL 5.7+** [(Descargar)](https://www.mysql.com/downloads/)
2. **Java 8+** [(Descargar)](https://www.oracle.com/java/technologies/downloads/)
3. **MySQL Connector/J** [(Descargar)](https://dev.mysql.com/downloads/connector/j/)

---

## 🚀 Paso 1: Crear la Base de Datos

### Opción A: Línea de comandos MySQL
```bash
mysql -u root -p < mascota_database.sql
```

### Opción B: Interfaz gráfica (MySQL Workbench)
1. Abre MySQL Workbench
2. Crea una nueva conexión
3. Copia el contenido de `mascota_database.sql`
4. Ejecuta en una nueva pestaña de script

### Opción C: PhpMyAdmin
1. Accede a PhpMyAdmin
2. Crea una nueva base de datos: `mascota_db`
3. Importa el archivo `mascota_database.sql`

---

## 🔧 Paso 2: Configurar Java

### 1. Descargar MySQL Connector/J
```bash
# En Linux/Mac
wget https://dev.mysql.com/get/Downloads/Connector-J/mysql-connector-java-8.0.33.jar

# En Windows
# Descargalo manualmente desde: https://dev.mysql.com/downloads/connector/j/
```

### 2. Copiar a carpeta de proyecto
```bash
# Crea una carpeta para librerías
mkdir /workspaces/Base-de-datos/lib
cp mysql-connector-java-8.0.33.jar /workspaces/Base-de-datos/lib/
```

---

## 💻 Paso 3: Compilar y Ejecutar

### Compilar las clases Java
```bash
cd /workspaces/Base-de-datos

# Compilar con la librería MySQL
javac -cp lib/mysql-connector-java-8.0.33.jar MascotaDAO.java
javac -cp lib/mysql-connector-java-8.0.33.jar EjemploUso.java
```

### Ejecutar el ejemplo
```bash
java -cp .:lib/mysql-connector-java-8.0.33.jar EjemploUso
```

---

## 📊 Estructura de la Base de Datos

### Tablas principales:

```
├── especie
│   └── razas
│       └── mascotas
│           └── mascota_colores
│               └── colores
├── duenos
└── colores
```

### Tabla **duenos**
| Campo | Tipo | Descripción |
|-------|------|-------------|
| id_dueno | INT | ID único (auto-incremental) |
| nombre | VARCHAR(100) | Nombre del dueño |
| apellido | VARCHAR(100) | Apellido del dueño |
| telefono | VARCHAR(20) | Teléfono de contacto |
| email | VARCHAR(100) | Email |
| direccion | VARCHAR(200) | Dirección |

### Tabla **mascotas**
| Campo | Tipo | Descripción |
|-------|------|-------------|
| id_mascota | INT | ID único (auto-incremental) |
| id_dueno | INT (FK) | Referencia al dueño |
| nombre | VARCHAR(100) | Nombre de la mascota |
| id_especie | INT (FK) | Perro (1) o Gato (2) |
| id_raza | INT (FK) | Raza de la mascota |
| sexo | ENUM | 'Macho' o 'Hembra' |
| edad | INT | Edad en años |
| fecha_nacimiento | DATE | Fecha de nacimiento |
| descripcion | VARCHAR(500) | Notas adicionales |

### Tabla **mascota_colores**
Permite múltiples colores por mascota:
| Campo | Tipo | Descripción |
|-------|------|-------------|
| id_mascota_color | INT | ID único |
| id_mascota | INT (FK) | Referencia a mascota |
| id_color | INT (FK) | Color aplicado |
| porcentaje | INT | Porcentaje del cuerpo |
| posicion | VARCHAR(100) | Dónde está el color |

---

## 🔌 Cómo Usar en tu Código Java

### 1. Conectarse a la base de datos
```java
MascotaDAO dao = new MascotaDAO();
```

### 2. Registrar una mascota
```java
dao.registrarMascota(
    1,                    // ID del dueño
    "Milo",              // Nombre
    1,                   // Especie (1=Perro, 2=Gato)
    2,                   // ID de raza
    "Macho",             // Sexo
    3                    // Edad
);
```

### 3. Ver razas disponibles
```java
// Ver todas las razas de perros (id_especie = 1)
List<String> razas = dao.obtenerRazasPorEspecie(1);
for (String raza : razas) {
    System.out.println(raza);
}
```

### 4. Ver todos los colores
```java
List<String> colores = dao.obtenerTodosLosColores();
for (String color : colores) {
    System.out.println(color);
}
```

### 5. Agregar colores a una mascota
```java
dao.agregarColorAMascota(
    1,              // ID de mascota
    3,              // ID de color
    50,             // Porcentaje
    "Cabeza"        // Posición
);
```

### 6. Obtener información de una mascota
```java
dao.obtenerInfoMascota(1);  // Muestra información completa
dao.obtenerColoresMascota(1);  // Muestra colores
```

### 7. Cerrar conexión
```java
dao.cerrar();
```

---

## 🔍 Razas Disponibles

### Perros (20)
1. Labrador Retriever
2. Bulldog Francés
3. Golden Retriever
4. Bulldog Inglés
5. Poodle
6. Beagle
7. Yorkshire Terrier
8. Dachshund
9. Rottweiler
10. Pastor Alemán
11. Doberman
12. Chihuahua
13. Cocker Spaniel
14. Schnauzer
15. Boxer
16. Shih Tzu
17. Husky Siberiano
18. San Bernardo
19. Cocker Spaniel Inglés
20. Pug

### Gatos (20)
1. Persa
2. Siamés
3. Gato Doméstico Europeo
4. Maine Coon
5. Ragdoll
6. Bengalí
7. Abisinio
8. Scottish Fold
9. Birmania
10. Esfinge
11. Manx
12. Sphynx
13. Angora Turco
14. Tonkinés
15. Bombay
16. Siam
17. Korat
18. Turco Van
19. Sagrado de Birmania
20. Gato de Albergue

---

## 🎨 Colores Disponibles
- Negro
- Blanco
- Gris
- Rojo/Naranja
- Café/Marrón
- Crema
- Chocolate
- Dorado
- Plateado
- Azul Gris
- Rojizo
- Tricolor
- Moteado
- Rayado
- Puntas Oscuras

---

## ⚙️ Configuración en MascotaDAO.java

Si tu MySQL no está en `localhost:3306`, modifica estas líneas:
```java
private String url = "jdbc:mysql://localhost:3306/mascota_db";
private String usuario = "root";
private String password = "";
```

---

## 🐛 Solución de Problemas

### Error: "com.mysql.cj.jdbc.Driver not found"
```
➜ Asegúrate de agregar mysql-connector-java al classpath:
   javac -cp lib/mysql-connector-java-8.0.33.jar MascotaDAO.java
```

### Error: "Access denied for user 'root'"
```
➜ Verifica tu usuario y contraseña en MascotaDAO.java
➜ Si no tienes contraseña, déjalo vacío: private String password = "";
```

### Error: "Unknown database 'mascota_db'"
```
➜ Ejecuta primero el script SQL: mysql -u root -p < mascota_database.sql
```

---

## 📝 Ejemplo Completo

Ver el archivo `EjemploUso.java` para un ejemplo completo de uso de todas las funciones.

---

## 📞 Soporte
Si necesitas modificar el esquema de la base de datos o agregar nuevas funcionalidades, 
edita el archivo `mascota_database.sql` y vuelve a crear la base de datos.

¡Listo! 🚀 Tu base de datos está lista para usar.
