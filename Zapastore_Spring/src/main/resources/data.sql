-- src/main/resources/data.sql (MySQL DML)

-- ===============================
-- Insertar Usuarios
-- ===============================
INSERT INTO usuarios (IDUsuario, nombre, correo, contrasena, telefono, Rol) VALUES
('U001','Carlos Cliente','carlos@mail.com','{noop}1234','987654321','Cliente'),
('U002','Admin ZapaStore','admin@mail.com','{noop}admin123','999888777','Administrador'),
('U003','Ana Perez','ana@mail.com','{noop}ana2025','912345678','Cliente');

-- ===============================
-- Insertar Categorías
-- ===============================
INSERT INTO categorias (nombre, estado) VALUES
('Running','Activo'),
('Casual','Activo'),
('Skate','Activo'),
('Basketball','Activo'),
('Trail','Activo'),
('Fitness','Activo');

-- ===============================
-- Insertar Productos (cada categoría al menos 1 producto)
-- ===============================
-- Categoría 1 = Running
INSERT INTO productos (nombre, categoria_ID, Precio, img_Url, descripcion) VALUES
('Zapa Air Zoom (R-1)', 1, 450.00, 'img/zapatilla1.jpg', 'Zapatillas ligeras para correr'),
('Zapa Speed Max (R-2)', 1, 480.00, 'img/zapatilla2.jpg', 'Velocidad y amortiguación superior');

-- Categoría 2 = Casual
INSERT INTO productos (nombre, categoria_ID, Precio, img_Url, descripcion) VALUES
('Zapa Classic Street (C-2)', 2, 320.00, 'img/zapatilla3.jpg', 'Estilo urbano y cómodo'),
('Zapa Casual Deluxe (C-2)', 2, 180.00, 'img/zapatilla4.jpg', 'Confort y estilo para el día a día');

-- Categoría 3 = Skate
INSERT INTO productos (nombre, categoria_ID, Precio, img_Url, descripcion) VALUES
('Skate Pro X (S-3)', 3, 210.50, 'img/zapatilla5.jpg', 'Alto rendimiento para skate'),
('Skate Street Runner (S-3)', 3, 230.00, 'img/zapatilla6.jpg', 'Agilidad y durabilidad en cada salto');

-- Categoría 4 = Basketball
INSERT INTO productos (nombre, categoria_ID, Precio, img_Url, descripcion) VALUES
('Hoop Master (B-4)', 4, 500.00, 'img/zapatilla-urb.jpg', 'Dominio total en la cancha');

-- Categoría 5 = Trail
INSERT INTO productos (nombre, categoria_ID, Precio, img_Url, descripcion) VALUES
('Trail Explorer (T-5)', 5, 350.00, 'img/zapatilla-run.jpg', 'Para correr en montaña y senderos');

-- Categoría 6 = Fitness
INSERT INTO productos (nombre, categoria_ID, Precio, img_Url, descripcion) VALUES
('Gym Pro Fit (F-6)', 6, 200.00, 'img/zapatilla-dep.jpg', 'Entrenamiento cómodo y seguro');

-- ===============================
-- Insertar Pedidos de ejemplo
-- ===============================
-- Pedido de U001
INSERT INTO pedidos (IDCliente, total_pagar, CostoEnvio, fecha, estado) VALUES
('U001', 770.00, 10.00, CURDATE(), 'Completado');

-- Detalle del Pedido (pedido_ID=1)
INSERT INTO pedido_detalle (pedido_ID, producto_ID, nombre_producto, precio_unitario, cantidad) VALUES
(1, 1, 'Zapa Air Zoom (R-1)', 450.00, 1),
(1, 4, 'Zapa Casual Deluxe (C-2)', 180.00, 2);

-- Pedido de U003
INSERT INTO pedidos (IDCliente, total_pagar, CostoEnvio, fecha, estado) VALUES
('U003', 480.00, 10.00, CURDATE(), 'Pendiente');

-- Detalle del Pedido (pedido_ID=2)
INSERT INTO pedido_detalle (pedido_ID, producto_ID, nombre_producto, precio_unitario, cantidad) VALUES
(2, 2, 'Zapa Speed Max (R-2)', 480.00, 1);
