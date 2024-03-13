package com.group5.Restaurant.controllers.impl;


import com.group5.Restaurant.constants.endpoints.IProductEndpoints;
import com.group5.Restaurant.constants.responses.rawResponses.Responses;
import com.group5.Restaurant.domains.dtos.ProductDTO;
import com.group5.Restaurant.constants.responses.objectResponseDTO.ObjectResponseDTO;
import com.group5.Restaurant.controllers.interfaces.IProductController;
import com.group5.Restaurant.errors.exceptions.BadRequestException;
import com.group5.Restaurant.errors.validations.ProductDTOValidation;
import com.group5.Restaurant.services.interfaces.IProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controller of the ClientService
 * Heard to the url "/product"
 */
@RestController
@RequestMapping(IProductEndpoints.PRODUCT_END_POINT)
@AllArgsConstructor
public class ProductControllerImpl implements IProductController {


    final IProductService service;//Service dependency injected
    final ProductDTOValidation productDTOValidation;//Product DTO validation dependency injected

    /**
     * Create a product
     * @param productDTO Data reference object of the product
     * @return createProduct of the service
     */
    @Override
    @Operation(summary = "Create a new product")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = Responses.OK, content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ObjectResponseDTO.class))}),
            @ApiResponse(responseCode = "400", description = Responses.BAD_REQUEST, content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ObjectResponseDTO.class))}),
            @ApiResponse(responseCode = "409", description = Responses.CONFLICT, content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ObjectResponseDTO.class))}),
            @ApiResponse(responseCode = "500", description = Responses.INTERNAL_SERVER_ERROR, content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ObjectResponseDTO.class))})
    })
    @PostMapping(IProductEndpoints.PRODUCT_CREATE)
    public ResponseEntity<ObjectResponseDTO> createProduct(@RequestBody ProductDTO productDTO) throws BadRequestException {
        this.productDTOValidation.validateProductDTO(productDTO);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(this.service.createProduct(productDTO));
    }

    @Override
    @Operation(summary = "Consult a product")
    @GetMapping(IProductEndpoints.PRODUCT_READ)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = Responses.OK, content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ObjectResponseDTO.class))}),
            @ApiResponse(responseCode = "400", description = Responses.BAD_REQUEST, content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ObjectResponseDTO.class))}),
            @ApiResponse(responseCode = "409", description = Responses.CONFLICT, content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ObjectResponseDTO.class))}),
            @ApiResponse(responseCode = "500", description = Responses.INTERNAL_SERVER_ERROR, content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ObjectResponseDTO.class))})
    })
    public ResponseEntity<ObjectResponseDTO> readProduct(@PathVariable String productUUID) throws BadRequestException {
        this.productDTOValidation.validateProductUUID(productUUID);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(this.service.readProduct(productUUID));
    }

    @Override
    @GetMapping(IProductEndpoints.PRODUCT_READ_ALL)
    @Operation(summary = "Consult a product")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = Responses.OK, content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ObjectResponseDTO.class))}),
            @ApiResponse(responseCode = "400", description = Responses.BAD_REQUEST, content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ObjectResponseDTO.class))}),
            @ApiResponse(responseCode = "409", description = Responses.CONFLICT, content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ObjectResponseDTO.class))}),
            @ApiResponse(responseCode = "500", description = Responses.INTERNAL_SERVER_ERROR, content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ObjectResponseDTO.class))})
    })
    public ResponseEntity<ObjectResponseDTO> readAllProducts() {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(this.service.readAllProducts());
    }

    /**
     * Update a product
     * @param productUUID ID reference of the product
     * @param productDTO Data reference object of the product
     * @return updateProduct of the service
     */
    @Override
    @Operation(summary = "Update a product")
    @PutMapping(IProductEndpoints.PRODUCT_UPDATE)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = Responses.OK, content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ObjectResponseDTO.class))}),
            @ApiResponse(responseCode = "400", description = Responses.BAD_REQUEST, content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ObjectResponseDTO.class))}),
            @ApiResponse(responseCode = "409", description = Responses.CONFLICT, content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ObjectResponseDTO.class))}),
            @ApiResponse(responseCode = "500", description = Responses.INTERNAL_SERVER_ERROR, content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ObjectResponseDTO.class))})
    })
    public ResponseEntity<ObjectResponseDTO> updateProduct(@PathVariable String productUUID, @RequestBody ProductDTO productDTO) throws BadRequestException {
        this.productDTOValidation.validateProductDTO(productDTO);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(this.service.updateProduct(productDTO));
    }

    /**
     *
     * @param productUUID ID reference of the product
     * @return deleteProduct of the service
     */
    @Override
    @Operation(summary = "Delete a Product")
    @DeleteMapping(IProductEndpoints.PRODUCT_DELETE)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = Responses.OK, content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ObjectResponseDTO.class))}),
            @ApiResponse(responseCode = "400", description = Responses.BAD_REQUEST, content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ObjectResponseDTO.class))}),
            @ApiResponse(responseCode = "409", description = Responses.CONFLICT, content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ObjectResponseDTO.class))}),
            @ApiResponse(responseCode = "500", description = Responses.INTERNAL_SERVER_ERROR, content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ObjectResponseDTO.class))})
    })
    public ResponseEntity<ObjectResponseDTO> deleteProduct(@PathVariable String productUUID) throws BadRequestException {
        this.productDTOValidation.validateProductUUID(productUUID);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(this.service.deleteProduct(productUUID));
    }
}