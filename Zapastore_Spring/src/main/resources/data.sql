-- src/main/resources/data.sql (MySQL DML)

-- Insertar Usuarios
INSERT INTO usuarios (IDUsuario, nombre, correo, contrasena, telefono, Rol) VALUES
('U001','Carlos Cliente','carlos@mail.com','{noop}1234','987654321','Cliente'),
('U002','Admin ZapaStore','admin@mail.com','{noop}admin123','999888777','Administrador');

-- Insertar Categorías
INSERT INTO categorias (nombre, estado) VALUES
('Running','Activo'),
('Casual','Activo'),
('Skate','Activo');

-- Insertar Productos (usando las IDs de Categorías: 1=Running, 2=Casual, 3=Skate)
INSERT INTO productos (nombre, categoria_ID, Precio, img_Url, descripcion) VALUES
('Zapa Air Zoom (R-1)', 1, 450.00, 'img/airzoom.jpg', 'Zapatillas ligeras para correr'),
('Zapa Classic Street (C-2)', 2, 320.00, 'img/classicstreet.jpg', 'Estilo urbano y cómodo'),
('Skate Pro X (S-3)', 3, 210.50, 'img/skateprox.jpg', 'Alto rendimiento para skate'),
('Zapa Casual Deluxe (C-2)', 2, 180.00, 'img/casualdeluxe.jpg', 'Confort y estilo para el día a día');

-- Insertar un Pedido de ejemplo (para U001)
INSERT INTO pedidos (IDCliente, total_pagar, CostoEnvio, fecha, estado) VALUES
('U001', 770.00, 10.00, CURDATE(), 'Completado');

-- Insertar detalles para el Pedido (pedido_ID=1)
INSERT INTO pedido_detalle (pedido_ID, producto_ID, nombre_producto, precio_unitario, cantidad) VALUES
(1, 1, 'Zapa Air Zoom (R-1)', 450.00, 1),
(1, 4, 'Zapa Casual Deluxe (C-2)', 180.00, 2);