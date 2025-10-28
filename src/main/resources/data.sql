-- Insertar libros de prueba
INSERT INTO libros (titulo, autor, isbn, editorial, fecha_publicacion, categoria, copias_totales, copias_disponibles) VALUES
('Cien años de soledad', 'Gabriel García Márquez', '978-0307474728', 'Editorial Sudamericana', '1967-06-05', 'Ficción', 5, 3),
('Don Quijote de la Mancha', 'Miguel de Cervantes', '978-8420412146', 'Real Academia Española', '1605-01-16', 'Clásico', 4, 4),
('El principito', 'Antoine de Saint-Exupéry', '978-0156012195', 'Reynal & Hitchcock', '1943-04-06', 'Infantil', 8, 6),
('1984', 'George Orwell', '978-0451524935', 'Secker & Warburg', '1949-06-08', 'Ficción', 6, 2),
('Harry Potter y la piedra filosofal', 'J.K. Rowling', '978-0439708180', 'Bloomsbury', '1997-06-26', 'Fantasía', 10, 7),
('Crónica de una muerte anunciada', 'Gabriel García Márquez', '978-0307387035', 'Editorial Sudamericana', '1981-04-01', 'Ficción', 3, 1),
('El amor en los tiempos del cólera', 'Gabriel García Márquez', '978-0307387738', 'Editorial Sudamericana', '1985-09-05', 'Romance', 4, 4),
('La casa de los espíritus', 'Isabel Allende', '978-1501117015', 'Plaza & Janés', '1982-10-01', 'Ficción', 5, 3),
('Rayuela', 'Julio Cortázar', '978-8420633473', 'Editorial Sudamericana', '1963-06-28', 'Ficción', 3, 2),
('El túnel', 'Ernesto Sabato', '978-8432217265', 'Sur', '1948-01-01', 'Ficción', 4, 4);

-- Insertar usuarios de prueba
INSERT INTO usuarios (nombre, apellido, email, telefono, rut, fecha_registro) VALUES
('Juan', 'Pérez', 'juan.perez@email.com', '912345678', '12345678-9', '2024-01-15'),
('María', 'González', 'maria.gonzalez@email.com', '987654321', '98765432-1', '2024-02-20'),
('Carlos', 'Rodríguez', 'carlos.rodriguez@email.com', '955555555', '11111111-1', '2024-03-10'),
('Ana', 'Martínez', 'ana.martinez@email.com', '944444444', '22222222-2', '2024-04-05'),
('Luis', 'López', 'luis.lopez@email.com', '933333333', '33333333-3', '2024-05-12');

-- Insertar préstamos de prueba (algunos activos y algunos devueltos)
INSERT INTO prestamos (libro_id, usuario_id, fecha_prestamo, fecha_devolucion_esperada, fecha_devolucion_real, estado) VALUES
(1, 1, '2024-10-01', '2024-10-15', '2024-10-14', 'DEVUELTO'),
(2, 2, '2024-10-10', '2024-10-24', NULL, 'ACTIVO'),
(3, 3, '2024-10-15', '2024-10-29', NULL, 'ACTIVO'),
(4, 1, '2024-10-05', '2024-10-19', '2024-10-18', 'DEVUELTO'),
(5, 4, '2024-10-20', '2024-11-03', NULL, 'ACTIVO');
