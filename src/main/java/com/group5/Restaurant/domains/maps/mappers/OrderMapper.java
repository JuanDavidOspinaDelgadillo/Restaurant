package com.group5.Restaurant.domains.maps.mappers;

import com.group5.Restaurant.constants.responses.rawResponses.Responses;
import com.group5.Restaurant.domains.dtos.OrderDTO;
import com.group5.Restaurant.domains.entities.OrderEntity;
import com.group5.Restaurant.domains.maps.Mapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

@Log4j2
@Component
public class OrderMapper {

    public OrderEntity convertOrderDTOToOrderEntity(OrderDTO orderDTO) {
        OrderEntity orderEntity = new OrderEntity();
        try {
            orderEntity = Mapper.modelMapper().map(orderDTO, OrderEntity.class);
        } catch (Exception e) {
            log.error(Responses.INTERNAL_SERVER_ERROR, e);
        }
        return orderEntity;
    }

    public OrderDTO convertOrderEntityToOrderDTO(OrderEntity orderEntity) {
        OrderDTO orderDTO = new OrderDTO();
        try {
            orderDTO = Mapper.modelMapper().map(orderEntity, OrderDTO.class);
        } catch (Exception e) {
            log.error(Responses.INTERNAL_SERVER_ERROR, e);
        }
        return orderDTO;
    }
}
