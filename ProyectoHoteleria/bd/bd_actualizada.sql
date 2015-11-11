drop database IF EXISTS bd_ser_hotel;
create database bd_ser_hotel;
use bd_ser_hotel;

/*Creacion de Tablas*/
create table tb_empleado(
cod_emp int,
nom_emp varchar(50),
ape_pat varchar(50),
ape_mat varchar(50),
tip_emp int,
usu_emp varchar(50),
pas_emp varchar(20)
);


create table tb_cliente(
cod_cli int,
nom_cli varchar(50),
ape_pat varchar(50),
ape_mat varchar(50),
tel_cli varchar(30),
dni_cli char(8)
);

create table tb_habitacion(
num_hab int,
cat_hab int,
tip_hab int,
pre_dia double,
est_hab int
);

create table tb_consumo(
cod_con int,
cod_cli int,
fec_ped date,
ser_con int,
tot_con double,
est_con int
);

create table tb_Producto(
cod_pro int,
des_pro varchar(50),
pre_pro double,
sto_pro int
);

create table tb_con_pro(
cod_con int,
cod_pro int,
can_det int,
pre_uni double,
sub_tot double
);


create table tb_alojamiento(
cod_alo int,
cod_cli int,
cod_emp int,
fec_lle date, 
hor_lle time,
fec_sal date,
hor_sal time,  
est_alo int
);

create table tb_alo_hab(
cod_alo int,
num_hab int
);


/*Creacion de Constraint*/
alter table tb_empleado 
			add primary key (cod_emp),
			MODIFY COLUMN cod_emp INT NOT NULL AUTO_INCREMENT;

alter table tb_cliente     
			add primary key(cod_cli),
			MODIFY COLUMN cod_cli INT NOT NULL AUTO_INCREMENT;

alter table tb_habitacion  
			add primary key(num_hab),
			MODIFY COLUMN num_hab INT NOT NULL AUTO_INCREMENT;

alter table tb_consumo 
			add primary key(cod_con),
			add foreign key(cod_cli) references tb_cliente(cod_cli),
			MODIFY COLUMN cod_con INT NOT NULL AUTO_INCREMENT;
alter table tb_producto 
			add primary key(cod_pro),
			MODIFY COLUMN cod_pro INT NOT NULL AUTO_INCREMENT;

alter table tb_con_pro 
			add primary key(cod_con, cod_pro),
			add foreign key(cod_con) references tb_consumo(cod_con),
			add foreign key(cod_pro) references tb_producto(cod_pro);

alter table tb_alojamiento 
			add primary key (cod_alo), 
			add foreign key (cod_cli) references tb_cliente(cod_cli),
			add foreign key (cod_emp) references tb_empleado(cod_emp),
			MODIFY COLUMN cod_alo INT NOT NULL AUTO_INCREMENT;

alter table tb_alo_hab 
			add primary key(cod_alo, num_hab),
			add foreign key(cod_alo) references tb_alojamiento(cod_alo),
			add foreign key(num_hab) references tb_habitacion(num_hab);

/*Inserts */

insert into tb_empleado 
values(1,'Admin','Admin','Admin',1,'Admin','1234');

insert into tb_empleado(cod_emp,nom_emp, ape_Pat, ape_mat, tip_emp, usu_emp, pas_emp) 
values (2, 'Katherine','Cuzcano','Mendoza',0,'kcuzcano','1234');

insert into tb_habitacion(cat_hab, tip_hab, pre_dia, est_hab)
values (2,0,100,0);
insert into tb_habitacion(cat_hab, tip_hab, pre_dia, est_hab) 
values (2,0,100,0);
insert into tb_habitacion(cat_hab, tip_hab, pre_dia, est_hab) 
values (2,0,100,0);
insert into tb_habitacion(cat_hab, tip_hab, pre_dia, est_hab) 
values (2,0,100,0);
insert into tb_habitacion(cat_hab, tip_hab, pre_dia, est_hab) 
values (2,0,100,0);
insert into tb_habitacion(cat_hab, tip_hab, pre_dia, est_hab) 
values (2,1,200,0);
insert into tb_habitacion(cat_hab, tip_hab, pre_dia, est_hab) 
values (2,1,200,0);
insert into tb_habitacion(cat_hab, tip_hab, pre_dia, est_hab)
values (2,1,200,0);
insert into tb_habitacion(cat_hab, tip_hab, pre_dia, est_hab)
values (2,1,200,0);
insert into tb_habitacion(cat_hab, tip_hab, pre_dia, est_hab) 
values (2,1,200,0);
insert into tb_habitacion(cat_hab, tip_hab, pre_dia, est_hab) 
values (2,2,300,0);
insert into tb_habitacion(cat_hab, tip_hab, pre_dia, est_hab) 
values (2,2,300,0);
insert into tb_habitacion(cat_hab, tip_hab, pre_dia, est_hab) 
values (2,2,300,0);
insert into tb_habitacion(cat_hab, tip_hab, pre_dia, est_hab) 
values (2,2,300,0);
insert into tb_habitacion(cat_hab, tip_hab, pre_dia, est_hab) 
values (2,2,300,0);
insert into tb_habitacion(cat_hab, tip_hab, pre_dia, est_hab)
values (2,3,500,0);
insert into tb_habitacion(cat_hab, tip_hab, pre_dia, est_hab)
values (2,3,500,0);
insert into tb_habitacion(cat_hab, tip_hab, pre_dia, est_hab)
values (2,3,500,0);
insert into tb_habitacion(cat_hab, tip_hab, pre_dia, est_hab) 
values (2,3,500,0);
insert into tb_habitacion(cat_hab, tip_hab, pre_dia, est_hab)
values (2,3,500,0);

insert into tb_habitacion(cat_hab, tip_hab, pre_dia, est_hab) 
values (1,0,200,0);
insert into tb_habitacion(cat_hab, tip_hab, pre_dia, est_hab)
values (1,0,200,0);
insert into tb_habitacion(cat_hab, tip_hab, pre_dia, est_hab)
values (1,0,200,0);
insert into tb_habitacion(cat_hab, tip_hab, pre_dia, est_hab) 
values (1,0,200,0);
insert into tb_habitacion(cat_hab, tip_hab, pre_dia, est_hab) 
values (1,0,200,0);
insert into tb_habitacion(cat_hab, tip_hab, pre_dia, est_hab) 
values (1,1,400,0);
insert into tb_habitacion(cat_hab, tip_hab, pre_dia, est_hab) 
values (1,1,400,0);
insert into tb_habitacion(cat_hab, tip_hab, pre_dia, est_hab) 
values (1,1,400,0);
insert into tb_habitacion(cat_hab, tip_hab, pre_dia, est_hab) 
values (1,1,400,0);
insert into tb_habitacion(cat_hab, tip_hab, pre_dia, est_hab) 
values (1,1,400,0);
insert into tb_habitacion(cat_hab, tip_hab, pre_dia, est_hab) 
values (1,2,600,0);
insert into tb_habitacion(cat_hab, tip_hab, pre_dia, est_hab)
values (1,2,600,0);
insert into tb_habitacion(cat_hab, tip_hab, pre_dia, est_hab)
values (1,2,600,0);
insert into tb_habitacion(cat_hab, tip_hab, pre_dia, est_hab)
values (1,2,600,0);
insert into tb_habitacion(cat_hab, tip_hab, pre_dia, est_hab) 
values (1,2,600,0);
insert into tb_habitacion(cat_hab, tip_hab, pre_dia, est_hab) 
values (1,3,800,0);
insert into tb_habitacion(cat_hab, tip_hab, pre_dia, est_hab)
values (1,3,800,0);
insert into tb_habitacion(cat_hab, tip_hab, pre_dia, est_hab) 
values (1,3,800,0);
insert into tb_habitacion(cat_hab, tip_hab, pre_dia, est_hab) 
values (1,3,800,0);
insert into tb_habitacion(cat_hab, tip_hab, pre_dia, est_hab) 
values (1,3,800,0);

insert into tb_habitacion(cat_hab, tip_hab, pre_dia, est_hab)
values (0,0,400,0);
insert into tb_habitacion(cat_hab, tip_hab, pre_dia, est_hab) 
values (0,0,400,0);
insert into tb_habitacion(cat_hab, tip_hab, pre_dia, est_hab) 
values (0,0,400,0);
insert into tb_habitacion(cat_hab, tip_hab, pre_dia, est_hab) 
values (0,0,400,0);
insert into tb_habitacion(cat_hab, tip_hab, pre_dia, est_hab) 
values (0,0,400,0);
insert into tb_habitacion(cat_hab, tip_hab, pre_dia, est_hab) 
values (0,1,800,0);
insert into tb_habitacion(cat_hab, tip_hab, pre_dia, est_hab) 
values (0,1,800,0);
insert into tb_habitacion(cat_hab, tip_hab, pre_dia, est_hab) 
values (0,1,800,0);
insert into tb_habitacion(cat_hab, tip_hab, pre_dia, est_hab) 
values (0,1,800,0);
insert into tb_habitacion(cat_hab, tip_hab, pre_dia, est_hab) 
values (0,1,800,0);
insert into tb_habitacion(cat_hab, tip_hab, pre_dia, est_hab) 
values (0,2,1600,0);
insert into tb_habitacion(cat_hab, tip_hab, pre_dia, est_hab) 
values (0,2,1600,0);
insert into tb_habitacion(cat_hab, tip_hab, pre_dia, est_hab) 
values (0,2,1600,0);
insert into tb_habitacion(cat_hab, tip_hab, pre_dia, est_hab) 
values (0,2,1600,0);
insert into tb_habitacion(cat_hab, tip_hab, pre_dia, est_hab) 
values (0,3,1800,0);
insert into tb_habitacion(cat_hab, tip_hab, pre_dia, est_hab)
values (0,3,1800,0);
insert into tb_habitacion(cat_hab, tip_hab, pre_dia, est_hab) 
values (0,3,1800,0);
insert into tb_habitacion(cat_hab, tip_hab, pre_dia, est_hab)
values (0,3,1800,0);

insert into tb_producto(des_pro,pre_pro,sto_pro)
values('Gaseosa',2.00,50);
insert into tb_producto(des_pro,pre_pro,sto_pro)
values('Galleta',1.00,60);
insert into tb_producto(des_pro,pre_pro,sto_pro)
values('Licor',50.00,80);
insert into tb_producto(des_pro,pre_pro,sto_pro)
values('Snack',10.00,50);
insert into tb_producto(des_pro,pre_pro,sto_pro)
values('Toalla',2.00,50);

