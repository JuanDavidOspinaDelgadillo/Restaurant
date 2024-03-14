package com.group5.Restaurant.constants.endpoints;

/**
 *  Endpoints of the Client Controller
 */
public interface IProductEndpoints {
    String PRODUCT_END_POINT = "/product";
    String PRODUCT_CREATE = "/create";
    String PRODUCT_READ = "/read/{productUUID}";
    String PRODUCT_READ_ALL = "/read_all";
    String PRODUCT_UPDATE = "/update/{productUUID}";
    String PRODUCT_DELETE = "/delete/{productUUID}";
    String PRODUCT_READ_BY_FANTASY_NAME = "/products/search?q={productFantasyName}";
}
