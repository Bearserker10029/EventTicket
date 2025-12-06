
CREATE DATABASE IF NOT EXISTS lab10_eventos;
USE lab10_eventos;

CREATE TABLE IF NOT EXISTS usuario (
  id_usuario       INT AUTO_INCREMENT PRIMARY KEY,
  nombres          VARCHAR(80)  NOT NULL,
  apellidos        VARCHAR(80)  NOT NULL,
  email            VARCHAR(120) NOT NULL,
  creado_en        TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  actualizado_en   TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  UNIQUE KEY uk_usuario_email (email)
);

CREATE TABLE IF NOT EXISTS evento (
  id_evento        INT AUTO_INCREMENT PRIMARY KEY,
  titulo           VARCHAR(150) NOT NULL,
  descripcion      VARCHAR(400) NULL,
  fecha            DATE NOT NULL,
  lugar            VARCHAR(150) NOT NULL,
  creado_en        TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS ticket_tipo (
  id_ticket_tipo   INT AUTO_INCREMENT PRIMARY KEY,
  id_evento        INT NOT NULL,
  nombre           VARCHAR(60) NOT NULL,            -- General, VIP, Estudiante, etc.
  precio           DECIMAL(10,2) NOT NULL CHECK (precio >= 0),
  cupo_total       INT NOT NULL CHECK (cupo_total >= 0),
  cupo_disponible  INT NOT NULL CHECK (cupo_disponible >= 0),
  CONSTRAINT fk_tt_evento FOREIGN KEY (id_evento) REFERENCES evento(id_evento)
    ON UPDATE CASCADE ON DELETE CASCADE,
  UNIQUE KEY uk_ticket_tipo_evento_nombre (id_evento, nombre)
);

CREATE TABLE IF NOT EXISTS reserva_item (
  id_item        INT AUTO_INCREMENT PRIMARY KEY,
  id_usuario     INT NOT NULL,
  id_ticket_tipo INT NOT NULL,
  cantidad       INT NOT NULL CHECK (cantidad > 0),
  agregado_en    TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  UNIQUE KEY uk_reserva_usuario_ticket (id_usuario, id_ticket_tipo),
  CONSTRAINT fk_reserva_usuario FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario)
    ON UPDATE CASCADE ON DELETE CASCADE,
  CONSTRAINT fk_reserva_ticket  FOREIGN KEY (id_ticket_tipo) REFERENCES ticket_tipo(id_ticket_tipo)
    ON UPDATE CASCADE ON DELETE CASCADE
);

INSERT INTO usuario (nombres, apellidos, email) VALUES
  ('Ana', 'Pérez', 'ana@demo.com'),
  ('Luis', 'Rivera', 'luis@demo.com');

INSERT INTO evento (titulo, descripcion, fecha, lugar) VALUES
  ('Concierto Sinfónico', 'Obras clásicas y contemporáneas', DATE_ADD(CURDATE(), INTERVAL 15 DAY), 'Teatro Municipal'),
  ('TechFest 2025', 'Charlas y workshops de tecnología', DATE_ADD(CURDATE(), INTERVAL 30 DAY), 'Centro de Convenciones');

INSERT INTO ticket_tipo (id_evento, nombre, precio, cupo_total, cupo_disponible) VALUES
  ((SELECT id_evento FROM evento WHERE titulo='Concierto Sinfónico'), 'General',   60.00, 200, 200),
  ((SELECT id_evento FROM evento WHERE titulo='Concierto Sinfónico'), 'VIP',      120.00,  50,  50),
  ((SELECT id_evento FROM evento WHERE titulo='TechFest 2025'),      'Pase Día',  80.00, 300, 300),
  ((SELECT id_evento FROM evento WHERE titulo='TechFest 2025'),      'FullPass', 150.00, 150, 150);

