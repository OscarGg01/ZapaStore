-- src/main/resources/schema.sql (MySQL DDL)

-- Desactiva la verificación de claves foráneas temporalmente si necesitas borrar o crear tablas
SET FOREIGN_KEY_CHECKS = 0;

-- Borrar tablas si existen para garantizar una inicialización limpia (útil con ddl-auto=none)
DROP TABLE IF EXISTS Pedido_Detalle;
DROP TABLE IF EXISTS Pedidos;
DROP TABLE IF EXISTS Productos;
DROP TABLE IF EXISTS Categorias;
DROP TABLE IF EXISTS Usuarios;

-- 1. Tabla Usuarios
CREATE TABLE Usuarios (
    IDUsuario VARCHAR(30) PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL,
    correo VARCHAR(100) UNIQUE NOT NULL,
    contrasena VARCHAR(100) NOT NULL,
    telefono VARCHAR(20),
    Rol VARCHAR(20) NOT NULL
);

-- 2. Tabla Categorias
CREATE TABLE Categorias (
    categoria_ID INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL,
    estado VARCHAR(20) DEFAULT 'Activo'
);

-- 3. Tabla Productos
CREATE TABLE Productos (
    producto_ID INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    categoria_ID INT,
    Precio DECIMAL(10,2) NOT NULL,
    img_Url TEXT,
    descripcion VARCHAR(255),
    CONSTRAINT fk_producto_categoria FOREIGN KEY (categoria_ID) REFERENCES Categorias(categoria_ID) ON UPDATE CASCADE ON DELETE SET NULL
);

-- 4. Tabla Pedidos
CREATE TABLE Pedidos (
    pedido_ID INT AUTO_INCREMENT PRIMARY KEY,
    IDCliente VARCHAR(30),
    total_pagar DECIMAL(10,2) NOT NULL,
    CostoEnvio DECIMAL(10,2) DEFAULT 0.00,
    fecha DATE NOT NULL,
    estado VARCHAR(20) DEFAULT 'Pendiente',
    
    -- ESTA ES LA ULTIMA LINEA ANTES DEL PARENTESIS DE CIERRE
    CONSTRAINT fk_pedido_usuario FOREIGN KEY (IDCliente) REFERENCES Usuarios(IDUsuario) ON UPDATE CASCADE ON DELETE SET NULL 
);

-- 5. Tabla Pedido_Detalle
CREATE TABLE Pedido_Detalle (
    pedidodetalle_ID INT AUTO_INCREMENT PRIMARY KEY,
    pedido_ID INT,
    producto_ID INT,
    nombre_producto VARCHAR(100),
    precio_unitario DECIMAL(10,2) NOT NULL,
    cantidad INT NOT NULL,
    -- Columna calculada (Generated Column)
    subtotal DECIMAL(10,2) GENERATED ALWAYS AS (precio_unitario * cantidad) STORED,
    CONSTRAINT fk_detalle_pedido FOREIGN KEY (pedido_ID) REFERENCES Pedidos(pedido_ID) ON UPDATE CASCADE ON DELETE CASCADE,
    CONSTRAINT fk_detalle_producto FOREIGN KEY (producto_ID) REFERENCES Productos(producto_ID) ON UPDATE CASCADE ON DELETE SET NULL
);

-- Reactiva la verificación de claves foráneas
SET FOREIGN_KEY_CHECKS = 1;