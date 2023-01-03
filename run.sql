create database Mediklik;
use Mediklik;

create table User(
UserID integer not null,
UserName varchar(32) not null,
Password varchar(64) not null,
IsAdmin boolean not null,
Balance integer not null,
primary key (UserID)
);

create table Category(
CategoryID integer not null,
CategoryName varchar(32) not null,
primary key (CategoryID)
);

create table Item(
ItemID integer not null,
ItemName varchar(32) not null,
ItemPrice integer not null,
CategoryID integer,
ItemRating double,
ItemImage varchar(64),
primary key (ItemID),
foreign key (CategoryID) references Category(CategoryID)
);

create table Cart(
UserID integer not null,
ItemID integer not null,
CartQuantity integer not null,
foreign key (UserID) references User(UserID),
foreign key (ItemID) references Item(ItemID)
);

create table Store(
StoreID integer not null,
StoreName varchar(32) not null,
StoreRating double,
primary key (StoreID)
);

create table Inventory(
StoreID integer not null,
ItemID integer not null,
InventoryQuantity integer not null,
primary key (StoreID),
foreign key (StoreID) references Store(StoreID),
foreign key (ItemID) references Item(ItemID)
);

create table Transaction(
TransactionID integer not null,
UserID integer not null,
primary key (TransactionID),
foreign key (UserID) references User(UserID)
);

create table TransactionItem(
TransactionID integer not null,
ItemID integer not null,
TransactionQuantity integer not null,
foreign key(TransactionID) references Transaction(TransactionID),
foreign key(ItemID) references Item(ItemID)
);

create table CurrentID(
UserID integer,
CategoryID integer,
ItemID integer,
StoreID integer,
TransactionID integer
);

insert into CurrentID values(1,-1,0,-1,-1);
insert into User values (0, "admin", "adminpassword", 1, 0);
insert into User values (1, "dummy", "password", 1, 1000000);
insert into Category values (0, "Uncategorized");
insert into Item values (0, "Panacea", 125000, 0, 4.50, "pill.jpg");
insert into Cart values (1, 0, 3);