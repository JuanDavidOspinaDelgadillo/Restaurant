package com.group5.Restaurant.services.impl;

import com.group5.Restaurant.constants.responses.objectResponseDTO.CorrectResponseDTO;
import com.group5.Restaurant.constants.responses.objectResponseDTO.ObjectResponseDTO;
import com.group5.Restaurant.constants.responses.objectResponseDTO.WrongResponseDTO;
import com.group5.Restaurant.constants.responses.rawResponses.Responses;
import com.group5.Restaurant.constants.responses.responseCodes.ResponseCodes;
import com.group5.Restaurant.domains.dtos.OrderDTO;
import com.group5.Restaurant.domains.entities.ClientEntity;
import com.group5.Restaurant.domains.entities.OrderEntity;
import com.group5.Restaurant.domains.entities.ProductEntity;
import com.group5.Restaurant.domains.maps.mappers.OrderMapper;
import com.group5.Restaurant.errors.exceptions.ConflictException;
import com.group5.Restaurant.errors.exceptions.NotFoundException;
import com.group5.Restaurant.repositories.IClientRepository;
import com.group5.Restaurant.repositories.IOrderRepository;
import com.group5.Restaurant.repositories.IProductRepository;
import com.group5.Restaurant.services.interfaces.IOrderService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Log4j2
@Service
@AllArgsConstructor
public class OrderServiceImpl implements IOrderService {

    private final IOrderRepository repository;//Repository dependency injected
    private final OrderMapper mapper;//Mapper dependency injected
    private final IProductRepository productRepository;//Product repository dependency injected
    private final IClientRepository clientRepository;//Client repository dependency injected

    /**
     * Create an order
     * @param orderDTO Data object reference with the data of the order
     * @return information related with the result of the process
     */
    @Override
    public ObjectResponseDTO createOrder(OrderDTO orderDTO) {
        try {
            Optional<ProductEntity> productExist = this.productRepository.findByProductUUID(orderDTO.getProductUUID());
            Optional<ClientEntity> clientExist = this.clientRepository.findById(orderDTO.getClientDocument());
            if(productExist.isPresent()) {
                if(clientExist.isPresent()) {
                    OrderEntity orderEntity = this.mapper.convertOrderDTOToOrderEntity(orderDTO);
                    orderEntity.setOrderCreationDateAndTime(LocalDateTime.now());
                    orderEntity.setProductEntity(productExist.get());
                    orderEntity.setClientEntity(clientExist.get());
                    this.repository.save(orderEntity);
                    return CorrectResponseDTO.builder()
                            .httpStatusCode(HttpStatus.OK.value())
                            .code(ResponseCodes.OK)
                            .object(orderDTO)
                            .timeStamp(LocalDateTime.now())
                            .description(Responses.OK)
                            .build();
                } else {
                    return WrongResponseDTO.builder()
                            .code(ResponseCodes.CLIENT_RELATED_DOESNT_EXIST)
                            .httpStatusCode(HttpStatus.NOT_FOUND.value())
                            .description("The client related doesnt exist")
                            .timeStamp(LocalDateTime.now())
                            .exception(new NotFoundException())
                            .build();
                }
            } else {
                return WrongResponseDTO.builder()
                        .code(ResponseCodes.PRODUCT_RELATED_DOESNT_EXIST)
                        .httpStatusCode(HttpStatus.NOT_FOUND.value())
                        .exception(new NotFoundException())
                        .timeStamp(LocalDateTime.now())
                        .description("The product related doesnt exist")
                        .build();
            }
        } catch (Exception e) {
            log.error(Responses.INTERNAL_SERVER_ERROR, e);
            return WrongResponseDTO.builder()
                    .code(ResponseCodes.INTERNAL_SERVER_ERROR)
                    .httpStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                    .description(Responses.INTERNAL_SERVER_ERROR)
                    .exception(e)
                    .timeStamp(LocalDateTime.now())
                    .build();
        }
    }

    @Override
    public ObjectResponseDTO updateOrder(String productUUID, LocalDateTime timeStamp) {
        try {
            Optional<ProductEntity> productExist = this.productRepository.findByProductUUID(productUUID);
            Optional<OrderEntity> orderRelatedWithProductExist = this.repository.findBy
            if()
        } catch (Exception e) {
            log.error(Responses.INTERNAL_SERVER_ERROR, e);
            return WrongResponseDTO.builder()
                    .exception(e)
                    .timeStamp(LocalDateTime.now())
                    .httpStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                    .code(ResponseCodes.INTERNAL_SERVER_ERROR)
                    .description(Responses.INTERNAL_SERVER_ERROR)
                    .build();
        }
    }
}
