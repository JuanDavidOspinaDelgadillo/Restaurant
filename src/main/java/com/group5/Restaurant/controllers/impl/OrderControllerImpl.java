package com.group5.Restaurant.controllers.impl;

import com.group5.Restaurant.constants.endpoints.IOrderEndpoints;
import com.group5.Restaurant.constants.responses.objectResponseDTO.ObjectResponseDTO;
import com.group5.Restaurant.constants.responses.rawResponses.Responses;
import com.group5.Restaurant.controllers.interfaces.IOrderController;
import com.group5.Restaurant.domains.dtos.OrderDTO;
import com.group5.Restaurant.errors.exceptions.BadRequestException;
import com.group5.Restaurant.errors.validations.OrderDTOValidation;
import com.group5.Restaurant.services.interfaces.IOrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping(IOrderEndpoints.ORDER_END_POINT)
public class OrderControllerImpl implements IOrderController {

    private final IOrderService service;
    private final OrderDTOValidation validator;
    @Override
    @PostMapping(IOrderEndpoints.ORDER_CREATE)
    @Operation(summary = "Create a new order")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = Responses.OK, content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ObjectResponseDTO.class))}),
            @ApiResponse(responseCode = "400", description = Responses.BAD_REQUEST, content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ObjectResponseDTO.class))}),
            @ApiResponse(responseCode = "409", description = Responses.CONFLICT, content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ObjectResponseDTO.class))}),
            @ApiResponse(responseCode = "500", description = Responses.INTERNAL_SERVER_ERROR, content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ObjectResponseDTO.class))})
    })
    public ResponseEntity<ObjectResponseDTO> createOrder(OrderDTO orderDTO) throws BadRequestException {
        this.validator.validateOrderDTO(orderDTO);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(this.service.createOrder(orderDTO));
    }
}
