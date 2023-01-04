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

#create table CurrentID(
#UserID integer,
#CategoryID integer,
#ItemID integer,
#StoreID integer,
#TransactionID integer
#);

#IDs
#insert into CurrentID values(1,-1,0,-1,-1);

#User account
insert into User values (0, "admin", "adminpassword", 1, 0);
insert into User values (1, "dummy", "password", 1, 1000000);

#Categories
insert into Category values (0, "Uncategorized");
insert into Category values (1, "Syrup");
insert into Category values (2, "Tablet");
insert into Category values (3, "Capsule");
insert into Category values (4, "Oles");
insert into Category values (5, "Others");

#Items
insert into Item values (0, "Paratusin", 125000, 1, 4.50, "pill.jpg");
insert into Item values (1, "Sanmol", 125000, 1, 4.50, "pill.jpg");
insert into Item values (2, "OBH Combi", 125000, 1, 4.50, "pill.jpg");
insert into Item values (3, "Vicks", 125000, 1, 4.50, "pill.jpg");
insert into Item values (4, "Woods", 125000, 1, 4.50, "pill.jpg");

insert into Item values (5, "Incidal", 125000, 2, 4.50, "pill.jpg");
insert into Item values (6, "Panadol", 125000, 2, 4.50, "pill.jpg");
insert into Item values (7, "Troches", 125000, 2, 4.50, "pill.jpg");
insert into Item values (8, "Mylanta", 125000, 2, 4.50, "pill.jpg");
insert into Item values (9, "Bodrex Migraine", 125000, 2, 4.50, "pill.jpg");

insert into Item values (10, "Diapet", 125000, 3, 4.50, "pill.jpg");
insert into Item values (11, "Obeslim", 125000, 3, 4.50, "pill.jpg");
insert into Item values (12, "Xenical", 125000, 3, 4.50, "pill.jpg");
insert into Item values (13, "Sangobion", 125000, 3, 4.50, "pill.jpg");
insert into Item values (14, "Lian Hua", 125000, 3, 4.50, "pill.jpg");

insert into Item values (15, "Dseolex", 125000, 4, 4.50, "pill.jpg");
insert into Item values (16, "Counterpain", 125000, 4, 4.50, "pill.jpg");
insert into Item values (17, "Medi-Klin TR", 125000, 4, 4.50, "pill.jpg");
insert into Item values (18, "Geliga", 125000, 4, 4.50, "pill.jpg");
insert into Item values (19, "Caladine", 125000, 4, 4.50, "pill.jpg");

insert into Item values (20, "Herocyn", 125000, 5, 4.50, "pill.jpg");
insert into Item values (21, "Salonpas", 125000, 5, 4.50, "pill.jpg");
insert into Item values (22, "Kapas", 125000, 5, 4.50, "pill.jpg");
insert into Item values (23, "Hansaplast", 125000, 5, 4.50, "pill.jpg");
insert into Item values (24, "Betadine", 125000, 5, 4.50, "pill.jpg");

insert into Cart values (1, 0, 3);
insert into Cart values (1, 1, 4);
insert into Cart values (1, 2, 5);