package models.entity.enums;

public enum OrderStatus {
    processing, //started, order is opened, driver now can see this order and try to get this.
    confirmation, // driver is trying to get this order, user confirm the driver or deny him and waiting for another.
    on_route, //driver got order, user confirmed. Driver can confirm that route is done.
    completed, //route is done in the past.
    canceled // canceled for some reasons.
}