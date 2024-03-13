package com.group5.Restaurant.constants.endpoints;

public interface IOrderEndpoints {
    String ORDER_END_POINT = "/orders";
    String ORDER_CREATE = "/create";
    String ORDER_UPDATE = "/update/{productUUID}/delivered/{deliveredDate}";
}
