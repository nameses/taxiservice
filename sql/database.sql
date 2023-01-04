create type carCategory as enum('Budget', 'Standart', 'Comfort', 'Premium');
create type userRole as enum('client', 'driver', 'admin');
create type driverStatus as enum('active', 'inactive', 'on route');
create type clientStatus as enum('processing', 'confirmation', 'on route', 'completed');

create table Taxi
(
    taxiID       serial                                           not null primary key,
    capacity     integer                                          not null,
    category     carCategory not null,
    fare         integer                                          not null,
    licensePlate varchar(8)                                       not null
);
create table "User"
(
    userID     serial                                             not null primary key,
    username   varchar(30)                                        not null,
    "password" varchar(64)                                        not null,
    fullname   varchar(36)                                        not null,
    phone      varchar(13)                                        not null,
    email      varchar(30)                                        not null,
    "role"     userRole default 'client' not null
);
create table Client
(
    clientID     serial  not null primary key,
    userID       integer not null,
    bonus_points integer default 0,
    FOREIGN KEY (userID) REFERENCES "User" (userID)
);
create table Driver
(
    driverID serial                                                    not null primary key,
    userID   integer                                                   not null,
    taxiID   integer                                                   not null,
    status   driverStatus default 'inactive' not null,
    FOREIGN KEY (userID) REFERENCES "User" (userID),
    FOREIGN KEY (TaxiID) REFERENCES Taxi (TaxiID)
);
create table "Order"
(
    orderID       serial                                                      not null primary key,
    clientID      integer                                                     not null,
    driverID      integer                                                     not null,
    orderOpened   timestamp                                                   not null,
    orderAccepted timestamp                                                   not null,
    "cost"        integer                                                     not null,
    capacity      integer                                                     not null,
    category      carCategory            not null,
    status        clientStatus not null,
    FOREIGN KEY (driverID) REFERENCES Driver (driverID),
    FOREIGN KEY (clientID) REFERENCES Client (clientID)
);

