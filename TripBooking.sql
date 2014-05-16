
create database TripBooking;
GO
use TripBooking;
GO
create table AdminAccount
(
	uname varchar(20) primary key,
	upass varchar(20),
	name nvarchar(30)
)

create table Station
(
	id int identity(1,1) primary key,
	sname nvarchar(50),
	[address] nvarchar(100),
	province nvarchar(50)
)

create table [Route]
(
	id int identity(1,1) primary key,
	departure int foreign key references Station(id),
	terminate int foreign key references Station(id),
	duration int, -- luu bang phut
)

create table Trip
(
	id int identity(1,1) primary key,
	departTime datetime,
	terminTime datetime,
	price float,
	totalSeats int,
	availableSeat int,
	routeId int foreign key references [Route](id)
)

create table Booking
(
	id int identity(1,1) primary key,
	tripId int foreign key references [Trip](id),
	name nvarchar(50),
	email varchar(50),
	phone varchar(20),
	note nvarchar(200),
	isPaid bit
)

-- dummy data

insert into AdminAccount values('sa','123','System Admin')
insert into AdminAccount values('admin','admin','My Admin')
insert into AdminAccount values('test','test','Test Admin')

insert into Station values(N'BXMĐ',N'292 Đinh Bộ Lĩnh P.6 Q.Bình Thạnh',N'Hồ Chí Minh')
insert into Station values(N'BXVT',N'13 Hoa Mai, Bãi Trước',N'Vũng Tàu')


insert into [Route] values(1,2,150)

insert into [Trip] values('05/16/2014 17:00:00','05/16/2014 19:30:00',250,50,50,1)

