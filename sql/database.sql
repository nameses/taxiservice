create table Taxi
(
    taxiID   serial      not null primary key,
    status   varchar(16) not null,
    capacity integer not null,
    category varchar(30) not null,
    fare integer not null,
    driverName varchar(30) not null,
    licensePlate varchar(8) not null
);


create table Client
(
    clientID  serial      not null primary key,
    firstname varchar(30) not null,
    lastname  varchar(30) not null,
    username  varchar(30) not null,
    password  varchar(64) not null,
    phone     varchar(13) not null,
    email     varchar(30) not null,
    role      varchar(16) not null
);

create table TaxiOrder
(
    orderID  serial    not null primary key,
    clientID serial    not null,
    taxiID   serial    not null,
    orderOpened     timestamp not null,
    orderAccepted     timestamp not null,
    cost     int       not null,
    FOREIGN KEY (TaxiID) REFERENCES Taxi (TaxiID),
    FOREIGN KEY (ClientID) REFERENCES Client (ClientID)
);

