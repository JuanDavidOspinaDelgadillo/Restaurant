package com.group5.Restaurant.services.interfaces;

import com.group5.Restaurant.constants.responses.objectResponseDTO.ObjectResponseDTO;
import com.group5.Restaurant.domains.dtos.OrderDTO;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

public interface IOrderService {
    ObjectResponseDTO createOrder(OrderDTO orderDTO);
    ObjectResponseDTO updateOrder(String productUUID, LocalDateTime timeStamp);
}
