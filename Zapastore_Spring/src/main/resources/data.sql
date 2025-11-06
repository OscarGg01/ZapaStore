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
-- Insertar Productos (Adaptado a VARCHAR(255) y sin detalle_producto)
-- ===============================
INSERT INTO productos (nombre, categoria_ID, Precio, img_Url, descripcion) VALUES
('Nike Air Zoom Pegasus', 
 1, 450.00, 'img/zapatilla1.jpg', 
 'Ligeras, media distancia. Fabricante: Nike Tech | Material: Malla Flyknit/TPU | Drop: 8mm. Veloz y transpirable.'),

('Adidas Adizero Boston', 
 1, 480.00, 'img/zapatilla2.jpg', 
 'Velocidad y amortiguación superior. Fabricante: Adidas Performance | Suela: Lightstrike Pro | Uso: Competición en asfalto.'),

('Converse Chuck 70 Classic', 
 2, 320.00, 'img/zapatilla3.jpg', 
 'Estilo urbano, cómodo. Fabricante: Converse Originals | Material: Lona premium/cuero sintético | Suela: Goma vulcanizada.'),

('Puma Suede Mayze', 
 2, 180.00, 'img/zapatilla4.jpg', 
 'Confort diario. Fabricante: Puma Lifestyle | Material: Gamuza sintética suave | Estilo: Plataforma Low-top clásico.'),

('Vans Sk8-Hi Pro X', 
 3, 210.50, 'img/zapatilla5.jpg', 
 'Alto rendimiento para skate. Fabricante: Vans Off The Wall | Material: Piel de ante doble costura | Tecnología: Plantilla PopCush absorbente de impactos.'),

('DC Shoes Pure Street', 
 3, 230.00, 'img/zapatilla6.jpg', 
 'Agilidad y durabilidad. Fabricante: DC Shoes | Material: Textil ripstop | Suela: Diseño "Pill Pattern" para máximo agarre.'),

('Jordan Air XXVI', 
 4, 500.00, 'img/zapatilla-urb.jpg', 
 'Dominio total en la cancha. Fabricante: Jordan Brand | Material: Sintético, soporte tobillo alto | Tecnología: Unidad de aire Max-Volume en talón.'),

('Salomon Speedcross 6', 
 5, 350.00, 'img/zapatilla-run.jpg', 
 'Correr en montaña y senderos. Fabricante: Salomon Outdoors | Material: Malla impermeable Gore-Tex | Suela: Tacos Contagrip ultra-resistente.'),

('Reebok Nano X4', 
 6, 200.00, 'img/zapatilla-dep.jpg', 
 'Entrenamiento cómodo y seguro. Fabricante: Reebok Training | Material: Tejido Flexweave sin costuras | Diseño: Lateral estable para HIIT.');

-- ===============================
-- Insertar Pedidos de ejemplo
-- ===============================
-- Pedido de U001
INSERT INTO pedidos (IDCliente, total_pagar, CostoEnvio, fecha, estado) VALUES
('U001', 770.00, 10.00, CURDATE(), 'Completado');

-- Detalle del Pedido (pedido_ID=1)
INSERT INTO pedido_detalle (pedido_ID, producto_ID, nombre_producto, precio_unitario, cantidad, talla, subtotal) 
VALUES 
(1, 1, 'Nike Air Zoom Pegasus', 450.00, 1, 39, 450.00),
(1, 4, 'Puma Suede Mayze', 180.00, 2, 42, 360.00);

-- Pedido de U003
INSERT INTO pedidos (IDCliente, total_pagar, CostoEnvio, fecha, estado) VALUES
('U003', 480.00, 10.00, CURDATE(), 'Pendiente');

-- Detalle del Pedido (pedido_ID=2)
INSERT INTO pedido_detalle (pedido_ID, producto_ID, nombre_producto, precio_unitario, cantidad, talla, subtotal) 
VALUES
(2, 2, 'Adidas Adizero Boston', 480.00, 1, 40, 480.00);