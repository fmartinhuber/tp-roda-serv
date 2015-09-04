--Copiar y pegar en la base de datos, no se ejecuta desde eclipse esto!

--------------------------------------------------
--------------------------------------------------
--------------------------------------------------

--Drops para cuando hiciste alguna cagada, borra toda la BD

drop table ItemFactura
go
drop table ItemOrdenCompra
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

-- Inserts hardcodeados para tener valores a consultar

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
insert into Rodamiento (tipo, codigo, stock, serie, origen, descripcion, monto, costo, marca, rodamiento_proveedor)
values ('Bolilla', '22310', 85, 8979745, 'Japon', 'CCW33', 310.71, 310.71, 'ZKL', 1)
--2
insert into Rodamiento (tipo, codigo, stock, serie, origen, descripcion, monto, costo, marca, rodamiento_proveedor)
values ('Bolilla', '22310', 9, 9562174, 'Argentina', 'EKW33', 249, 249, 'SKF', 1)
--3
insert into Rodamiento (tipo, codigo, stock, serie, origen, descripcion, monto, costo, marca, rodamiento_proveedor)
values ('Agujas', '6200F', 0, 2025791, 'Francia', '', 7.1, 7.1, 'SNR', 2)
--4
insert into Rodamiento (tipo, codigo, stock, serie, origen, descripcion, monto, costo, marca, rodamiento_proveedor)
values ('Rodilla', '6200A', 30, 3328710, 'Alemania', '2RS', 7.9, 7.9, 'FAG', 3)