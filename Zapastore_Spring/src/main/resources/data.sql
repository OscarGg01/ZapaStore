-- ===============================
-- Insertar Usuarios
-- ===============================
INSERT INTO usuarios (IDUsuario, nombre, correo, contrasena, telefono, Rol) VALUES
('U001','Carlos Cliente','carlos@mail.com','1234','987654321','CLIENTE'),
('U002','Admin ZapaStore','admin@mail.com','admin123','999888777','ADMIN'),
('U003','Ana Perez','ana@mail.com','ana2025','912345678','CLIENTE');

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
-- Insertar Productos
-- ===============================
INSERT INTO productos (nombre, categoria_ID, Precio, img_Url, descripcion) VALUES
('Zapa Air Zoom (R-1)', 1, 450.00, 'img/zapatilla1.jpg', 'Zapatillas ligeras para correr'),
('Zapa Speed Max (R-2)', 1, 480.00, 'img/zapatilla2.jpg', 'Velocidad y amortiguación superior'),
('Zapa Classic Street (C-2)', 2, 320.00, 'img/zapatilla3.jpg', 'Estilo urbano y cómodo'),
('Zapa Casual Deluxe (C-2)', 2, 180.00, 'img/zapatilla4.jpg', 'Confort y estilo para el día a día'),
('Skate Pro X (S-3)', 3, 210.50, 'img/zapatilla5.jpg', 'Alto rendimiento para skate'),
('Skate Street Runner (S-3)', 3, 230.00, 'img/zapatilla6.jpg', 'Agilidad y durabilidad en cada salto'),
('Hoop Master (B-4)', 4, 500.00, 'img/zapatilla-urb.jpg', 'Dominio total en la cancha'),
('Trail Explorer (T-5)', 5, 350.00, 'img/zapatilla-run.jpg', 'Para correr en montaña y senderos'),
('Gym Pro Fit (F-6)', 6, 200.00, 'img/zapatilla-dep.jpg', 'Entrenamiento cómodo y seguro');

-- ===============================
-- Insertar Pedidos de ejemplo
-- ===============================
-- Pedido de U001
INSERT INTO pedidos (IDCliente, total_pagar, CostoEnvio, fecha, estado) VALUES
('U001', 770.00, 10.00, CURDATE(), 'Completado');

-- Detalle del Pedido (pedido_ID=1)
INSERT INTO pedido_detalle (pedido_ID, producto_ID, nombre_producto, precio_unitario, cantidad, talla, subtotal) 
VALUES 
(1, 1, 'Zapa Air Zoom (R-1)', 450.00, 1, 35, 450.00),
(1, 4, 'Zapa Casual Deluxe (C-2)', 180.00, 2, 35, 360.00);

-- Pedido de U003
INSERT INTO pedidos (IDCliente, total_pagar, CostoEnvio, fecha, estado) VALUES
('U003', 480.00, 10.00, CURDATE(), 'Pendiente');

-- Detalle del Pedido (pedido_ID=2)
INSERT INTO pedido_detalle (pedido_ID, producto_ID, nombre_producto, precio_unitario, cantidad, talla, subtotal) 
VALUES
(2, 2, 'Zapa Speed Max (R-2)', 480.00, 1, 35, 480.00);
