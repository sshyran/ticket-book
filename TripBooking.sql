
create database TripBooking;
GO
use TripBooking;
GO
create table AdminAccount
(
	uname varchar(20) primary key,
	upass varchar(20) not null,
	name nvarchar(30)
)

create table Station
(
	id int identity(1,1) primary key,
	sname nvarchar(50) not null,
	[address] nvarchar(100),
	province nvarchar(50)
)

create table [Route]
(
	id int identity(1,1) primary key,
	departure int foreign key references Station(id),
	terminate int foreign key references Station(id),
	name nvarchar(255) not null,
	isShow bit not null,
)

create table Trip
(
	id int identity(1,1) primary key,
	departTime datetime,
	terminTime datetime,
	price float,
	totalSeats int,
	availableSeat int,
	routeId int foreign key references [Route](id),
	isShow bit not null,
)

create table Booking
(
	id int identity(1,1) primary key,
	tripId int foreign key references [Trip](id),
	name nvarchar(50),
	email varchar(50),
	phone varchar(20),
	numTicket int,
	total float,
	method varchar(20),
	isPaid bit not null
)

-- dummy data

insert into AdminAccount values('sa','123','System Admin')
insert into AdminAccount values('admin','admin','My Admin')
insert into AdminAccount values('test','test','Test Admin')

insert into Station values(N'BXMĐ',N'292 Đinh Bộ Lĩnh P.6 Q.Bình Thạnh',N'Hồ Chí Minh')
insert into Station values(N'BXVT',N'13 Hoa Mai, Bãi Trước',N'Vũng Tàu')
insert into Station values(N'Bến xe Quảng Ngãi',N'Lê Thánh Tôn, Nghĩa Chánh, tp. Quảng Ngãi, Quảng Ngãi',N'Quảng Ngãi')
insert into Station values(N'Bến xe Kim Mã',N'Nguyễn Thái Học, Kim Mã, Đống Đa, Hà Nội',N'Hà nội')
insert into Station values(N'Bến xe Lương Yên',N'Nguyễn Khoái, Hai Bà Trưng, Hà Nội',N'Hà Nội')
insert into Station values(N'Bến Xe Đà Nẵng',N'185 Tôn Đức Thắng, Hòa Minh, Liên Chiểu',N'Đà Nẵng')
insert into Station values(N'Bến xe trung tâm Tôn Đức Thắng',N'Nguyễn Văn Tạo, Hòa An, Thanh Khê',N'Đà Nẵng')

insert into [Route] values(1,2,N'Hồ Chí Minh - Vũng Tàu',1)
insert into [Route] values(1,3,N'Hồ Chí Minh - Quảng Ngãi',1)
insert into [Route] values(1,4,N'Hồ Chí Minh - Hà Nội',1)
insert into [Route] values(1,6,N'Hồ Chí Minh - Đà Nẵng',1)
insert into [Route] values(2,3,N'Vũng Tàu - Quảng Ngãi',1)

--trip: id departTime terminTime price totalSeats availableSeat routeId isShow
insert into [Trip] values('05/16/2014 5:00:00','05/16/2014 9:00:00',250,50,50,1,1)
insert into [Trip] values('05/16/2014 15:00:00','05/17/2014 19:00:00',250,50,50,2,1)

