Create database DB_PerfectShoes2
go

USE DB_PerfectShoes2
go

Drop database DB_PerfectShoes2

Create table Rol(
[Id] int not null primary key identity(1,1),
[Nombre] varchar (50) not null)

go

Create table Usuario(
[Id] int Primary key identity(1,1),
[IdRol] int not null,
[Nombre] varchar(50) not null,
[Apellido] varchar(50)not null,
[Login] varchar(100) not null,
[Password] varchar(50) not null,
[FechaRegistro] date not null,
[Estatus] tinyint not null
foreign key (IdRol) references Rol(Id)
);
go
Create Table Proveedor(
[Id] int not null primary key identity(1,1),
[Nombre] varchar (50) not null,
[Apellido] varchar (50) not null,
[Razon_Social] varchar (100) not null,
[Telefono] varchar (15) not null, 
[Direcciòn] varchar (100) not null)
go
Create table Marca(
 [Id] int not null primary key identity(1,1),
 [Nombre] varchar (50) not null
 );
 go
Create Table Productos(
[Id] int not null primary key identity(1,1), 
[IdProveedor] int not null,
[IdMarca] int not null,
[Nombre] varchar (50) not null,
[Cantidad] varchar (50) not null,
[Precio] money null, 
[Talla] varchar (15) not null
foreign key (IdProveedor) references Proveedor(Id),
foreign key (IdMarca) references Marca(Id),
);
go

 Create table Clientes(
[Id] int not null Primary key identity (1,1),
[Nombre] varchar(30) not null,
[Apellido] varchar(30) not null,
[Telefono] varchar(30) not null,
[Direcciòn] varchar (80) not null,
[Dui] varchar (30) not null
);
go
Create table Ventas(
[Id] int not null Primary key identity (1,1),
[IdClientes] int not null,
[Fecha] datetime not null,
foreign key (IdClientes) references Clientes(Id)
);

go

Create Table Categorias(
[Id] int not null primary key identity(1,1),
[IdProductos] int not null,
[NombreCategorias] varchar (50) not null
);
go
Create table Detalle_Venta(
[Id] int not null primary key identity(1,1),
[Idventas] int not null,
[IdProductos] int not null,
[IdCategorias] int not null,
[Nombre] varchar (50) not null,
[Marca] varchar (50) not null,
[Cantidad] int not null,
[Precio] money null,
SubTotal int not null,
Total int not null
);
go

/*viernes70*/
Insert into Usuario( IdRol,Nombre, Apellido, Login, Password, FechaRegistro,Estatus)
Values(1,'Carmen', 'Mejia', 'CarmenM', '1234567', '2023-08-15',1)
go
Insert into Usuario( IdRol,Nombre, Apellido, Login, Password, FechaRegistro,Estatus)
Values(2,'Carmen', 'Mejia', 'Hola', 'e10adc3949ba59abbe56e057f20f883e', '2023-08-15',1)
go

Insert into Rol(Nombre)
values('Administrador');

-- CREACION DEL LOGIN
CREATE LOGIN [JavaUser]
WITH PASSWORD = '123456789',
DEFAULT_DATABASE = DB_PerfectShoes2,
CHECK_POLICY = OFF,
CHECK_EXPIRATION = OFF;

-- ASIGNACIÓN DE PERMISOS
USE DB_PerfectShoes2;
CREATE USER [JavaUser] FOR LOGIN [JavaUser] WITH DEFAULT_SCHEMA = dbo;
ALTER ROLE db_owner ADD MEMBER [JavaUser];
