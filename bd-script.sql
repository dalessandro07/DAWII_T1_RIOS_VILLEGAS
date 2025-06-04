CREATE DATABASE dawii_t1;
USE dawii_t1;

CREATE TABLE DragonBall(
	id int primary key auto_increment,
    nombre text not null,
    ki text,
    max_ki text,
    raza text,
    genero text,
    descripcion text
);