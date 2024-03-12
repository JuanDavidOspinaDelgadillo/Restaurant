package com.group5.Restaurant.constants.endpoints;

/**
 *  Endpoints of the Client Controller
 */
public interface IClientEndpoints {
    String CLIENT_END_POINT = "/client";
    String CLIENT_CREATE = "/create";
    String CLIENT_READ = "/read/{clientDocument}";
    String CLIENT_DELETE = "/delete/{clientDocument}";
}