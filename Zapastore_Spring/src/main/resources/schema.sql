-- src/main/resources/schema.sql (MySQL DDL)

-- Desactiva la verificación de claves foráneas temporalmente
SET FOREIGN_KEY_CHECKS = 0;

-- Borrar tablas si existen (usando minúsculas)
DROP TABLE IF EXISTS pedido_detalle;
DROP TABLE IF EXISTS pedidos;
DROP TABLE IF EXISTS productos;
DROP TABLE IF EXISTS categorias;
DROP TABLE IF EXISTS usuarios;

-- 1. Tabla usuarios (Minúsculas)
CREATE TABLE usuarios (
    IDUsuario VARCHAR(30) PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL,
    correo VARCHAR(100) UNIQUE NOT NULL,
    contrasena VARCHAR(100) NOT NULL,
    telefono VARCHAR(20),
    Rol VARCHAR(20) NOT NULL
);

-- 2. Tabla categorias (Minúsculas)
CREATE TABLE categorias (
    categoria_ID INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL,
    estado VARCHAR(20) DEFAULT 'Activo'
);

-- 3. Tabla productos (Minúsculas)
CREATE TABLE productos (
    producto_ID INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    categoria_ID INT,
    Precio DECIMAL(10,2) NOT NULL,
    img_Url TEXT,
    descripcion VARCHAR(255),
    -- CORRECCIÓN: Referencia a 'categorias' en minúsculas
    CONSTRAINT fk_producto_categoria FOREIGN KEY (categoria_ID) REFERENCES categorias(categoria_ID) ON UPDATE CASCADE ON DELETE SET NULL
);

-- 4. Tabla pedidos (Minúsculas)
CREATE TABLE pedidos (
    pedido_ID INT AUTO_INCREMENT PRIMARY KEY,
    IDCliente VARCHAR(30),
    total_pagar DECIMAL(10,2) NOT NULL,
    CostoEnvio DECIMAL(10,2) DEFAULT 0.00,
    fecha DATE NOT NULL,
    estado VARCHAR(20) DEFAULT 'Pendiente',
    
    -- CORRECCIÓN: Referencia a 'usuarios' en minúsculas
    CONSTRAINT fk_pedido_usuario FOREIGN KEY (IDCliente) REFERENCES usuarios(IDUsuario) ON UPDATE CASCADE ON DELETE SET NULL 
);

-- 5. Tabla pedido_detalle (Minúsculas)
CREATE TABLE pedido_detalle (
    pedidodetalle_ID INT AUTO_INCREMENT PRIMARY KEY,
    pedido_ID INT,
    producto_ID INT,
    nombre_producto VARCHAR(100),
    precio_unitario DECIMAL(10,2) NOT NULL,
    cantidad INT NOT NULL,
    -- Columna calculada (Generated Column)
    subtotal DECIMAL(10,2) GENERATED ALWAYS AS (precio_unitario * cantidad) STORED,
    -- CORRECCIONES: Referencias a 'pedidos' y 'productos' en minúsculas
    CONSTRAINT fk_detalle_pedido FOREIGN KEY (pedido_ID) REFERENCES pedidos(pedido_ID) ON UPDATE CASCADE ON DELETE CASCADE,
    CONSTRAINT fk_detalle_producto FOREIGN KEY (producto_ID) REFERENCES productos(producto_ID) ON UPDATE CASCADE ON DELETE SET NULL
);

-- Reactiva la verificación de claves foráneas
SET FOREIGN_KEY_CHECKS = 1;