create type carCategory as enum ('budget', 'standart', 'comfort', 'premium');
create type userRole as enum ('client', 'driver', 'admin');
create type driverStatus as enum ('active', 'inactive', 'on route');
create type clientStatus as enum ('processing', 'confirmation', 'on route', 'completed','canceled');

create table taxi
(
    taxiID       serial      not null primary key,
    driverID     integer,
    capacity     integer     not null,
    category     carCategory not null,
    fare         integer     not null,
    licensePlate varchar(8)  not null,
    FOREIGN KEY (driverID) REFERENCES driver (driverID)
);
create table "user"
(
    userID     serial                    not null primary key,
    username   varchar(30)               not null,
    "password" varchar(32)               not null,
    fullname   varchar(36)               not null,
    phone      varchar(13)               not null,
    email      varchar(30)               not null,
    "role"     userRole default 'client' not null
);
create table client
(
    clientID    serial  not null primary key,
    userID      integer not null,
    bonusPoints integer default 0,
    FOREIGN KEY (userID) REFERENCES "user" (userID)
);
create table driver
(
    driverID     serial                          not null primary key,
    userID       integer                         not null,
    driverStatus driverStatus default 'inactive' not null,
    FOREIGN KEY (userID) REFERENCES "user" (userID)
);
create table "order"
(
    orderID       serial       not null primary key,
    clientID      integer      not null,
    driverID      integer      not null,
    orderOpened   timestamp    not null,
    orderAccepted timestamp    not null,
    "cost"        integer      not null,
    carCapacity   integer      not null,
    carCategory   carCategory  not null,
    status        clientStatus not null,
    FOREIGN KEY (driverID) REFERENCES driver (driverID),
    FOREIGN KEY (clientID) REFERENCES client (clientID)
);
create table route
(
    routeid     serial primary key,
    startMarker real[],
    finalMarker real[],
    length      integer,
    orderid     integer
)

