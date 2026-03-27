-- Base de datos para sistema de mascotas
CREATE DATABASE IF NOT EXISTS mascota_db;
USE mascota_db;

-- Tabla de Especies
CREATE TABLE especies (
    id_especie INT PRIMARY KEY AUTO_INCREMENT,
    nombre_especie VARCHAR(50) NOT NULL UNIQUE,
    descripcion VARCHAR(200)
);

-- Tabla de Razas
CREATE TABLE razas (
    id_raza INT PRIMARY KEY AUTO_INCREMENT,
    nombre_raza VARCHAR(100) NOT NULL,
    id_especie INT NOT NULL,
    descripcion VARCHAR(200),
    FOREIGN KEY (id_especie) REFERENCES especies(id_especie)
);

-- Tabla de Colores
CREATE TABLE colores (
    id_color INT PRIMARY KEY AUTO_INCREMENT,
    nombre_color VARCHAR(50) NOT NULL UNIQUE,
    codigo_hex VARCHAR(7)
);

-- Tabla de Dueños
CREATE TABLE duenos (
    id_dueno INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(100) NOT NULL,
    apellido VARCHAR(100) NOT NULL,
    telefono VARCHAR(20),
    email VARCHAR(100),
    direccion VARCHAR(200),
    fecha_registro TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Tabla de Mascotas
CREATE TABLE mascotas (
    id_mascota INT PRIMARY KEY AUTO_INCREMENT,
    id_dueno INT NOT NULL,
    nombre VARCHAR(100) NOT NULL,
    id_especie INT NOT NULL,
    id_raza INT NOT NULL,
    sexo ENUM('Macho', 'Hembra') NOT NULL,
    edad INT,
    fecha_nacimiento DATE,
    fecha_registro TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    descripcion VARCHAR(500),
    FOREIGN KEY (id_dueno) REFERENCES duenos(id_dueno),
    FOREIGN KEY (id_especie) REFERENCES especies(id_especie),
    FOREIGN KEY (id_raza) REFERENCES razas(id_raza)
);

-- Tabla de relación Mascotas-Colores (permite múltiples colores por mascota)
CREATE TABLE mascota_colores (
    id_mascota_color INT PRIMARY KEY AUTO_INCREMENT,
    id_mascota INT NOT NULL,
    id_color INT NOT NULL,
    porcentaje INT DEFAULT 100,
    posicion VARCHAR(100),
    FOREIGN KEY (id_mascota) REFERENCES mascotas(id_mascota) ON DELETE CASCADE,
    FOREIGN KEY (id_color) REFERENCES colores(id_color),
    UNIQUE KEY unique_mascota_color (id_mascota, id_color)
);

-- Insertar Especies
INSERT INTO especies (nombre_especie, descripcion) VALUES
('Perro', 'Canino doméstico'),
('Gato', 'Felino doméstico');

-- Insertar Razas de Perros (20 principales)
INSERT INTO razas (nombre_raza, id_especie, descripcion) VALUES
('Labrador Retriever', 1, 'Perro de compañía amigable'),
('Bulldog Francés', 1, 'Perro pequeño y compacto'),
('Golden Retriever', 1, 'Perro inteligente y leal'),
('Bulldog Inglés', 1, 'Perro de tamaño medio'),
('Poodle', 1, 'Perro inteligente y elegante'),
('Beagle', 1, 'Perro de caza pequeño'),
('Yorkshire Terrier', 1, 'Perro toy de largo pelaje'),
('Dachshund', 1, 'Perro pequeño de patas cortas'),
('Rottweiler', 1, 'Perro guardián grande'),
('Pastor Alemán', 1, 'Perro inteligente y trabajador'),
('Doberman', 1, 'Perro guardián elegante'),
('Chihuahua', 1, 'Perro toy muy pequeño'),
('Cocker Spaniel', 1, 'Perro de compañía mediano'),
('Schnauzer', 1, 'Perro de trabajo inteligente'),
('Boxer', 1, 'Perro atlético y leal'),
('Shih Tzu', 1, 'Perro toy de largo pelaje'),
('Husky Siberiano', 1, 'Perro grande y enérgico'),
('San Bernardo', 1, 'Perro guardián gigante'),
('Cocker Spaniel Inglés', 1, 'Perro de compañía deportivo'),
('Pug', 1, 'Perro toy de cara plana');

-- Insertar Razas de Gatos (20 principales)
INSERT INTO razas (nombre_raza, id_especie, descripcion) VALUES
('Persa', 2, 'Gato de pelaje largo y denso'),
('Siamés', 2, 'Gato elegante de ojos azules'),
('Gato Doméstico Europeo', 2, 'Gato común de patas medias'),
('Maine Coon', 2, 'Gato grande de pelaje largo'),
('Ragdoll', 2, 'Gato semi-largo de ojos azules'),
('Bengalí', 2, 'Gato de pelaje moteado'),
('Abisinio', 2, 'Gato de pelaje corto y brillante'),
('Scottish Fold', 2, 'Gato con orejas dobladas'),
('Birmania', 2, 'Gato semi-largo de puntas oscuras'),
('Esfinge', 2, 'Gato sin pelaje'),
('Manx', 2, 'Gato sin cola'),
('Sphynx', 2, 'Gato sin pelaje compacto'),
('Angora Turco', 2, 'Gato de pelaje largo y sedoso'),
('Tonkinés', 2, 'Gato de compañía cariñoso'),
('Bombay', 2, 'Gato negro de ojos de cobre'),
('Siam', 2, 'Gato elegante de Tailandia'),
('Korat', 2, 'Gato gris de Tailandia'),
('Turco Van', 2, 'Gato semi-largo blanco'),
('Sagrado de Birmania', 2, 'Gato semi-largo de puntas coloreadas'),
('Gato de Albergue', 2, 'Mestizo de característica variable');

-- Insertar Colores (10+ colores típicos)
INSERT INTO colores (nombre_color, codigo_hex) VALUES
('Negro', '#000000'),
('Blanco', '#FFFFFF'),
('Gris', '#808080'),
('Rojo/Naranja', '#FF4500'),
('Café/Marrón', '#8B4513'),
('Crema', '#FFFDD0'),
('Chocolate', '#D2B48C'),
('Dorado', '#FFD700'),
('Plateado', '#C0C0C0'),
('Azul Gris', '#A9A9A9'),
('Rojizo', '#B22222'),
('Tricolor', '#CCCCCC'),
('Moteado', '#696969'),
('Rayado', '#DCDCDC'),
('Puntas Oscuras', '#696969');

-- Insertar ejemplo de Dueño
INSERT INTO duenos (nombre, apellido, telefono, email, direccion) VALUES
('Juan', 'Pérez', '555-1234', 'juan@email.com', 'Calle Principal 123');

-- Insertar ejemplo de Mascota
INSERT INTO mascotas (id_dueno, nombre, id_especie, id_raza, sexo, edad, fecha_nacimiento, descripcion) VALUES
(1, 'Max', 1, 2, 'Macho', 3, '2022-01-15', 'Bulldog francés muy cariñoso');

-- Insertar colores para la mascota
INSERT INTO mascota_colores (id_mascota, id_color, porcentaje, posicion) VALUES
(1, 1, 70, 'Cuerpo'),
(1, 2, 30, 'Pecho y patas');

-- Crear índices para mejorar búsquedas
CREATE INDEX idx_dueno_mascota ON mascotas(id_dueno);
CREATE INDEX idx_especie ON mascotas(id_especie);
CREATE INDEX idx_raza ON mascotas(id_raza);
CREATE INDEX idx_nombre_mascota ON mascotas(nombre);
