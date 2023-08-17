Create database db_PerfectShoes

Use db_PerfectShoes

Create table Rol(
[Id] int not null primary key identity(1,1),
[Nombre] varchar (50) not null)

Create table Usuario(
[Id] int not null,
[IdRol] int not null,
[Nombre] varchar(50) not null,
[Apellido] varchar(50)not null,
[Login] varchar(100) not null,
[Password] varchar(30) not null,
[FechaRegistro] datetime not null,

foreign key (IdRol) references Rol(Id)
);
Create Table Proveedor(
[Id] int not null primary key identity(1,1),
[IdRol] int not null,
[Nombre] varchar (50) not null,
[Apellido] varchar (50) not null,
[Razon_Social] varchar (100) not null,
[Telefono] varchar (15) not null, 
[Direcciòn] varchar (100) not null, 
foreign key (IdRol) references Rol(Id)
);
Create Table Productos(
[Id] int not null primary key identity(1,1), 
[IdProveedor] int not null,
[Marca] int not null,
[Nombre] varchar (50) not null,
[Cantidad] varchar (50) not null,
[Precio] money null, 
[Talla] varchar (15) not null, 
foreign key (IdProveedor) references Proveedor(Id),
); 
Create table Marca(
 [Id] int not null primary key identity(1,1),
 [Nombre] varchar (50) not null
 );
Create table Ventas(
[Id] int not null Primary key identity (1,1),
[IdProductos] int not null,
[Fecha] datetime not null,
foreign key (IdProductos) references Productos(Id)
);
Create table Clientes(
[Id] int not null Primary key identity (1,1),
[IdVentas] int not null,
[Nombre] varchar(30) not null,
[Apellido] varchar(30) not null,
[Telefono] varchar(30) not null,
[Direcciòn] varchar (80) not null,
[Dui] varchar (30) not null
foreign key (IdVentas) references Ventas(Id)
);
Create Table Categorias(
[Id] int not null primary key identity(1,1),
[IdProductos] int not null,
[NombreCategorias] varchar (50) not null,
foreign key (IdProductos) references Productos(Id)
);
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
Total int not null,
foreign key (Idventas) references Ventas(Id),
foreign key (IdProductos) references Productos(Id),
foreign key (IdCategorias) references Categorias(Id)
);