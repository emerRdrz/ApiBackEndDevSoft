-- MySQL dump 10.13  Distrib 8.0.17, for Win64 (x86_64)
--
-- Host: localhost    Database: db_gestionlogistica
-- ------------------------------------------------------
-- Server version	8.0.17


CREATE
DATABASE db_gestionlogistica;
CREATE
USER 'gestionlogistica'@'%' IDENTIFIED BY 'admin2022$';
GRANT ALL PRIVILEGES ON db_gestionlogistica . * TO
'gestionlogistica'@'%';

--
-- Table structure for table `pais`
--
CREATE TABLE db_gestionlogistica.pais
(
    id_pais  int not null primary key auto_increment,
    descripcion char(100),
    enable      boolean
);

--
-- Table structure for table `tipo_bodega`
--
CREATE TABLE db_gestionlogistica.tipo_bodega
(
    id_tipo_bodega int not null primary key auto_increment,
    descripcion       char(50),
    enable            boolean
);

--
-- Table structure for table `tipo_producto`
--
CREATE TABLE db_gestionlogistica.tipo_producto
(
    id_tipo_producto int not null primary key auto_increment,
    descripcion         char(50),
    enable              boolean
);

--
-- Table structure for table `bodega`
--
CREATE TABLE db_gestionlogistica.bodega
(
    id_bodega      int not null primary key auto_increment,
    id_tipo_bodega int not null,
    id_pais        int not null,
    descripcion       char(50),
    direccion         char(100),
    enable            boolean
);

-- ------------------------------------------------------
--  Constraints for Table bodega
-- ------------------------------------------------------
ALTER TABLE db_gestionlogistica.bodega
    ADD CONSTRAINT fk_tipobodega_bodega FOREIGN KEY (id_tipo_bodega) REFERENCES db_gestionlogistica.tipo_bodega (id_tipo_bodega);
ALTER TABLE db_gestionlogistica.bodega
    ADD CONSTRAINT fk_pais_bodega FOREIGN KEY (id_pais) REFERENCES db_gestionlogistica.pais (id_pais);

--
-- Table structure for table `usuario`
--
CREATE TABLE db_gestionlogistica.usuario
(
    id_usuario int       not null primary key auto_increment,
    nombre        char(100) not null,
    apellido      char(100) not null,
    email         char(100) not null,
    clave         char(100) not null,
    direccion     char(100) not null,
    contacto      char(12),
    enable        boolean
);


--
-- Table structure for table `orden_transporte`
--
CREATE TABLE db_gestionlogistica.orden_transporte
(
    id_orden_transporte int            not null primary key auto_increment,
    id_tipo_producto   	int            not null,
    id_usuario         int            not null,
    cantidad_producto     int            not null,
    fecha_registro        date           not null,
    fecha_entrega         date           not null,
    id_bodega          int            not null,
    precio_envio          decimal(10, 2) not null,
    precio_descuento      decimal(10, 2),
    porcentaje_descuento  int,
    placa                 varchar(8)     not null,
    numero_guia           varchar(10)    not null
);

-- ------------------------------------------------------
--  Constraints for Table orden_transporte
-- ------------------------------------------------------
ALTER TABLE db_gestionlogistica.orden_transporte
    ADD CONSTRAINT fK_tipoProducto_ordenTransporte FOREIGN KEY (id_tipo_producto) REFERENCES db_gestionlogistica.tipo_producto (id_tipo_producto);
ALTER TABLE db_gestionlogistica.orden_transporte
    ADD CONSTRAINT fk_usuario_ordenTransporte FOREIGN KEY (id_usuario) REFERENCES db_gestionlogistica.usuario (id_usuario);
ALTER TABLE db_gestionlogistica.orden_transporte
    ADD CONSTRAINT fk_bodega_ordenTransporte FOREIGN KEY (id_bodega) REFERENCES db_gestionlogistica.bodega (id_bodega);



create table db_gestionlogistica.roles(
                                   id int not null primary key auto_increment,
                                   name varchar(20)
);

create table db_gestionlogistica.user_roles(
                                        user_id int not null,
                                        role_id int not null
);

--
-- Dumping data for table `roles`
--
INSERT INTO db_gestionlogistica.roles(name) VALUES('ROLE_USER');
INSERT INTO db_gestionlogistica.roles(name) VALUES('ROLE_MODERATOR');
INSERT INTO db_gestionlogistica.roles(name) VALUES('ROLE_ADMIN');

--
-- Dumping data for table `usuario`
--
INSERT INTO db_gestionlogistica.usuario
VALUES (null, 'Emerson',	'Rodriguez',	'rdrz@correo.com',	'$2a$10$FMWQzTjglEPv83.xWwFGTunUGpivVKscmWqF7l7sMKmyk/7AOPBWW',	'mi casa',	'50312345678',	1);

--
-- Dumping data for table `user_roles`
--
INSERT INTO db_gestionlogistica.user_roles VALUES (1,1);


INSERT INTO `db_gestionlogistica`.`pais` (`id_pais`,`descripcion`, `enable`) VALUES (1,'El Salvador', '1');
INSERT INTO `db_gestionlogistica`.`pais` (`id_pais`,`descripcion`, `enable`) VALUES (2,'Colombia', '1');
INSERT INTO `db_gestionlogistica`.`pais` (`id_pais`,`descripcion`, `enable`) VALUES (3,'Guatemala', '1');
INSERT INTO `db_gestionlogistica`.`pais` (`id_pais`,`descripcion`, `enable`) VALUES (4,'Estados Unidos', '1');
INSERT INTO `db_gestionlogistica`.`pais` (`id_pais`,`descripcion`, `enable`) VALUES (5,'Costa Rica', '1');
INSERT INTO `db_gestionlogistica`.`pais` (`id_pais`,`descripcion`, `enable`) VALUES (6,'Mexico', '1');

INSERT INTO `db_gestionlogistica`.`tipo_bodega` (`id_tipo_bodega`,`descripcion`, `enable`) VALUES (1,'Terrestre ', '1');
INSERT INTO `db_gestionlogistica`.`tipo_bodega` (`id_tipo_bodega`,`descripcion`, `enable`) VALUES (2,'Marítima ', '1');

INSERT INTO `db_gestionlogistica`.`bodega` (`id_tipo_bodega`, `id_pais`, `descripcion`, `direccion`, `enable`) VALUES ('1', '1', 'Bodega terreste A', 'ubicación bodega A', '1');
INSERT INTO `db_gestionlogistica`.`bodega` (`id_tipo_bodega`, `id_pais`, `descripcion`, `direccion`, `enable`) VALUES ('1', '1', 'Bodega terreste B', 'ubicación bodega B', '1');
INSERT INTO `db_gestionlogistica`.`bodega` (`id_tipo_bodega`, `id_pais`, `descripcion`, `direccion`, `enable`) VALUES ('2', '1', 'Bodega marítima A', 'ubicación bodega A', '1');
INSERT INTO `db_gestionlogistica`.`bodega` (`id_tipo_bodega`, `id_pais`, `descripcion`, `direccion`, `enable`) VALUES ('2', '1', 'Bodega marítima B', 'ubicación bodega B', '1');
INSERT INTO `db_gestionlogistica`.`bodega` (`id_tipo_bodega`, `id_pais`, `descripcion`, `direccion`, `enable`) VALUES ('1', '2', 'Bodega terreste A', 'ubicación bodega A', '1');
INSERT INTO `db_gestionlogistica`.`bodega` (`id_tipo_bodega`, `id_pais`, `descripcion`, `direccion`, `enable`) VALUES ('1', '2', 'Bodega terreste B', 'ubicación bodega B', '1');
INSERT INTO `db_gestionlogistica`.`bodega` (`id_tipo_bodega`, `id_pais`, `descripcion`, `direccion`, `enable`) VALUES ('2', '2', 'Bodega marítima A', 'ubicación bodega A', '1');
INSERT INTO `db_gestionlogistica`.`bodega` (`id_tipo_bodega`, `id_pais`, `descripcion`, `direccion`, `enable`) VALUES ('2', '2', 'Bodega marítima B', 'ubicación bodega B', '1');
INSERT INTO `db_gestionlogistica`.`bodega` (`id_tipo_bodega`, `id_pais`, `descripcion`, `direccion`, `enable`) VALUES ('1', '4', 'Bodega terreste A', 'ubicación bodega A', '1');
INSERT INTO `db_gestionlogistica`.`bodega` (`id_tipo_bodega`, `id_pais`, `descripcion`, `direccion`, `enable`) VALUES ('1', '4', 'Bodega terreste B', 'ubicación bodega B', '1');
INSERT INTO `db_gestionlogistica`.`bodega` (`id_tipo_bodega`, `id_pais`, `descripcion`, `direccion`, `enable`) VALUES ('2', '4', 'Bodega marítima A', 'ubicación bodega A', '1');
INSERT INTO `db_gestionlogistica`.`bodega` (`id_tipo_bodega`, `id_pais`, `descripcion`, `direccion`, `enable`) VALUES ('2', '4', 'Bodega marítima B', 'ubicación bodega B', '1');






commit;