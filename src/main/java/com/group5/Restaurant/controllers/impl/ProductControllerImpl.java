package com.group5.Restaurant.controllers.impl;


import com.group5.Restaurant.constants.endpoints.IProductEndpoints;
import com.group5.Restaurant.constants.responses.rawResponses.Responses;
import com.group5.Restaurant.domains.dtos.ProductDTO;
import com.group5.Restaurant.constants.responses.objectResponseDTO.ObjectResponseDTO;
import com.group5.Restaurant.controllers.interfaces.IProductController;
import com.group5.Restaurant.services.interfaces.IProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
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

    /**
     * Create a product
     * @param productDTO Data reference object of the product
     * @return createProduct of the service
     */
    @Operation(summary = "Create a new product")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = Responses.OK, content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ObjectResponseDTO.class))}),
            @ApiResponse(responseCode = "400", description = Responses.BAD_REQUEST, content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ObjectResponseDTO.class))}),
            @ApiResponse(responseCode = "409", description = Responses.CONFLICT, content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ObjectResponseDTO.class))}),
            @ApiResponse(responseCode = "500", description = Responses.INTERNAL_SERVER_ERROR, content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ObjectResponseDTO.class))})
    })
    @Override
    @PostMapping(IProductEndpoints.PRODUCT_CREATE)
    public ResponseEntity<ObjectResponseDTO> createProduct(@RequestBody ProductDTO productDTO) {
        return this.service.createProduct(productDTO);
    }

    /**
     * Update a product
     * @param productUUID ID reference of the product
     * @param productDTO Data reference object of the product
     * @return updateProduct of the service
     */
    @Operation(summary = "Update an existing product")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = Responses.OK, content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ObjectResponseDTO.class))}),
            @ApiResponse(responseCode = "400", description = Responses.BAD_REQUEST, content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ObjectResponseDTO.class))}),
            @ApiResponse(responseCode = "409", description = Responses.CONFLICT, content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ObjectResponseDTO.class))}),
            @ApiResponse(responseCode = "500", description = Responses.INTERNAL_SERVER_ERROR, content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ObjectResponseDTO.class))})
    })
    @Override
    @PutMapping(IProductEndpoints.PRODUCT_UPDATE)
    public ResponseEntity<ObjectResponseDTO> updateProduct(@PathVariable String productUUID, @RequestBody ProductDTO productDTO) {
        return this.service.updateProduct(productDTO);
    }

    /**
     *
     * @param productUUID ID reference of the product
     * @param productDTO Data reference object of the product
     * @return deleteProduct of the service
     */
    @Operation(summary = "Delete a product")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = Responses.OK, content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ObjectResponseDTO.class))}),
            @ApiResponse(responseCode = "400", description = Responses.BAD_REQUEST, content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ObjectResponseDTO.class))}),
            @ApiResponse(responseCode = "409", description = Responses.CONFLICT, content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ObjectResponseDTO.class))}),
            @ApiResponse(responseCode = "500", description = Responses.INTERNAL_SERVER_ERROR, content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ObjectResponseDTO.class))})
    })
    @Override
    @DeleteMapping(IProductEndpoints.PRODUCT_DELETE)
    public ResponseEntity<ObjectResponseDTO> deleteProduct(@PathVariable String productUUID, @RequestBody ProductDTO productDTO){
        return this.service.deleteProduct(productDTO);
    }
}