--Copiar y pegar en la base de datos, no se ejecuta desde eclipse esto!

--------------------------------------------------
--------------------------------------------------
--------------------------------------------------

--Drops para cuando hiciste alguna cagada, borra toda la BD

drop table ItemFactura
go
drop table ItemOrdenCompra
go
drop table OrdenCompra_Cotizacion
go
drop table OrdenCompra
go
drop table Factura
go
drop table ItemCotizacion
go
drop table Rodamiento
go
drop table Cotizacion
go
drop table Proveedor
go
drop table Remito
go
drop table Cliente
go

--------------------------------------------------
--------------------------------------------------
--------------------------------------------------

-- Inserts hardcodeados para tener valores a consultar y poder trabajar

--Proveedor
--1
insert into Proveedor (nombre)
values ('Solear SA')
go
--2
insert into Proveedor (nombre)
values ('Quatum SRL')
go
--3
insert into Proveedor (nombre)
values ('Fill Hermanos')
go

--Rodamiento (Se usa la tabla de rodamientos al final del TPO como guia)
--1
insert into Rodamiento (tipo, codigo, stock, origen, caracteristica, monto, marca, rodamiento_proveedor)
values ('Bolilla', '22310', 85, 'Japon', 'CCW33', 310.71, 'ZKL', 1)
go
--2
insert into Rodamiento (tipo, codigo, stock, origen, caracteristica, monto, marca, rodamiento_proveedor)
values ('Bolilla', '22310', 9, 'Argentina', 'EKW33', 249, 'SKF', 1)
go
--3
insert into Rodamiento (tipo, codigo, stock, origen, caracteristica, monto, marca, rodamiento_proveedor)
values ('Agujas', '6200F', 0, 'Francia', '', 7.1, 'SNR', 2)
go
--4
insert into Rodamiento (tipo, codigo, stock, origen, caracteristica, monto, marca, rodamiento_proveedor)
values ('Rodilla', '6200A', 30, 'Alemania', '2RS', 7.9, 'FAG', 3)
go

--Cliente
--1
insert into Cliente (CUIT, mail, razonSocial)
values ('20345850090', 'dariodario@gmail.com', 'Nieto SRL')
go
--2
insert into Cliente (CUIT, mail, razonSocial)
values ('2033852947', 'martinhub@gmail.com', 'Martin SA')
go
--3
insert into Cliente (CUIT, mail, razonSocial)
values ('205558630', 'ramiro@fryda.com', 'R&R')
go
--4
insert into Cliente (CUIT, mail, razonSocial)
values ('207410589', 'charlycharly@hotmail.com', 'Carlos Consultores')
go

--Cotizaciones e Items
--1
insert into Cotizacion (cotizacion_cliente, estado, fechaCreacion, fechaVigencia)
values (1,'APROBADA','2015-08-12','2015-09-12')
go
-- 1-1
insert into ItemCotizacion (item_rodamiento, cant, subtotal, cotizacion_items)
values (1, 200,((select monto from Rodamiento where IdRodamiento = 1)*200), 1)
go
-- 1-2
insert into ItemCotizacion (item_rodamiento, cant, subtotal, cotizacion_items)
values (3, 150,((select monto from Rodamiento where IdRodamiento = 3)*150), 1)
go
-- 1-3
insert into ItemCotizacion (item_rodamiento, cant, subtotal, cotizacion_items)
values (4, 60,((select monto from Rodamiento where IdRodamiento = 4)*60), 1)
go

--2
insert into Cotizacion (cotizacion_cliente, estado, fechaCreacion, fechaVigencia)
values (1,'APROBADA','2015-09-05','2015-10-05')
go
-- 2-1
insert into ItemCotizacion (item_rodamiento, cant, subtotal, cotizacion_items)
values (1, 100,((select monto from Rodamiento where IdRodamiento = 1)*100), 2)
go
-- 2-2
insert into ItemCotizacion (item_rodamiento, cant, subtotal, cotizacion_items)
values (4, 90,((select monto from Rodamiento where IdRodamiento = 4)*90), 2)
go

--3
insert into Cotizacion (cotizacion_cliente, estado, fechaCreacion, fechaVigencia)
values (1,'PENDIENTE','2015-09-10','2015-10-10')
go
--3-1
insert into ItemCotizacion (item_rodamiento, cant, subtotal, cotizacion_items)
values (2, 40,((select monto from Rodamiento where IdRodamiento = 2)*40), 3)
go
--3-2
insert into ItemCotizacion (item_rodamiento, cant, subtotal, cotizacion_items)
values (3, 90,((select monto from Rodamiento where IdRodamiento = 3)*90), 3)
go
--3-3
insert into ItemCotizacion (item_rodamiento, cant, subtotal, cotizacion_items)
values (4, 60,((select monto from Rodamiento where IdRodamiento = 4)*60), 3)
go

--4
insert into Cotizacion (cotizacion_cliente, estado, fechaCreacion, fechaVigencia)
values (1,'ORDENADA','2015-08-10','2015-09-09')
go
-- 4-1
insert into ItemCotizacion (item_rodamiento, cant, subtotal, cotizacion_items)
values (4, 30,((select monto from Rodamiento where IdRodamiento = 4)*30),4)
go