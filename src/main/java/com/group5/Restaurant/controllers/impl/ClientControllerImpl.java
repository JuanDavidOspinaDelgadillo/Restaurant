package com.group5.Restaurant.controllers.impl;

import com.group5.Restaurant.constants.endpoints.IClientEndpoints;
import com.group5.Restaurant.constants.responses.rawResponses.Responses;
import com.group5.Restaurant.domains.dtos.ClientDTO;
import com.group5.Restaurant.constants.responses.objectResponseDTO.ObjectResponseDTO;
import com.group5.Restaurant.controllers.interfaces.IClientController;
import com.group5.Restaurant.errors.exceptions.BadRequestException;
import com.group5.Restaurant.errors.validations.ClientDTOValidation;
import com.group5.Restaurant.services.interfaces.IClientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(IClientEndpoints.CLIENT_END_POINT)
@AllArgsConstructor
public class ClientControllerImpl implements IClientController {

    final IClientService service;//Service dependency injected
    final ClientDTOValidation clientDTOValidation;//Client DTO validation dependency injected

    /**
     * Create a client
     * @param clientDTO Data reference object of the client
     * @return createClient of the service
     */
    @Override
    @Operation(summary = "Create a new client")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = Responses.OK, content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ObjectResponseDTO.class))}),
            @ApiResponse(responseCode = "400", description = Responses.BAD_REQUEST, content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ObjectResponseDTO.class))}),
            @ApiResponse(responseCode = "409", description = Responses.CONFLICT, content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ObjectResponseDTO.class))}),
            @ApiResponse(responseCode = "500", description = Responses.INTERNAL_SERVER_ERROR, content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ObjectResponseDTO.class))})
    })
    @PostMapping(value = IClientEndpoints.CLIENT_CREATE)
    public ResponseEntity<ObjectResponseDTO> createClient(@RequestBody ClientDTO clientDTO) throws BadRequestException {
        this.clientDTOValidation.validateClientDTO(clientDTO);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(this.service.createClient(clientDTO));
    }

    /**
     * Consult a client
     * @param clientDocument ID reference of the client
     * @return readClient of the service
     */
    @Override
    @Operation(summary = "Consult a client")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = Responses.OK, content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ObjectResponseDTO.class))}),
            @ApiResponse(responseCode = "400", description = Responses.BAD_REQUEST, content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ObjectResponseDTO.class))}),
            @ApiResponse(responseCode = "409", description = Responses.CONFLICT, content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ObjectResponseDTO.class))}),
            @ApiResponse(responseCode = "500", description = Responses.INTERNAL_SERVER_ERROR, content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ObjectResponseDTO.class))})
    })
    @GetMapping(IClientEndpoints.CLIENT_READ)
    public ResponseEntity<ObjectResponseDTO> readClient(@PathVariable Long clientDocument) throws BadRequestException {
        this.clientDTOValidation.validateClientDocument(clientDocument);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(this.service.readClient(clientDocument));
    }

    /**
     * Read all the clients
     * @return readClients of the service;
     */
    @Override
    @Operation(summary = "Read all the clients")
    @GetMapping(IClientEndpoints.CLIENT_READ_ALL)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = Responses.OK, content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ObjectResponseDTO.class))}),
            @ApiResponse(responseCode = "400", description = Responses.BAD_REQUEST, content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ObjectResponseDTO.class))}),
            @ApiResponse(responseCode = "409", description = Responses.CONFLICT, content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ObjectResponseDTO.class))}),
            @ApiResponse(responseCode = "500", description = Responses.INTERNAL_SERVER_ERROR, content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ObjectResponseDTO.class))})
    })
    public ResponseEntity<ObjectResponseDTO> readAllClients() {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(this.service.readAllClients());
    }

    /**
     * Update a client
     * @param clientDocument ID reference of the client
     * @param clientDTO Data reference of the client
     * @return updateClient from the service
     */
    @Override
    @PutMapping(IClientEndpoints.CLIENT_UPDATE)
    @Operation(summary = "Update a client")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = Responses.OK, content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ObjectResponseDTO.class))}),
            @ApiResponse(responseCode = "400", description = Responses.BAD_REQUEST, content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ObjectResponseDTO.class))}),
            @ApiResponse(responseCode = "409", description = Responses.CONFLICT, content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ObjectResponseDTO.class))}),
            @ApiResponse(responseCode = "500", description = Responses.INTERNAL_SERVER_ERROR, content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ObjectResponseDTO.class))})
    })
    public ResponseEntity<ObjectResponseDTO> updateClient(@PathVariable Long clientDocument, @RequestBody ClientDTO clientDTO) throws BadRequestException {
        this.clientDTOValidation.validateClientDTO(clientDTO);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(this.service.updateClient(clientDocument, clientDTO));
    }

    /**
     * Delete a client
     * @param clientDocument ID reference of the client
     * @return deleteClient from the service
     */
    @Override
    @Operation(summary = "Delete a client")
    @DeleteMapping(IClientEndpoints.CLIENT_DELETE)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = Responses.OK, content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ObjectResponseDTO.class))}),
            @ApiResponse(responseCode = "400", description = Responses.BAD_REQUEST, content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ObjectResponseDTO.class))}),
            @ApiResponse(responseCode = "409", description = Responses.CONFLICT, content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ObjectResponseDTO.class))}),
            @ApiResponse(responseCode = "500", description = Responses.INTERNAL_SERVER_ERROR, content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ObjectResponseDTO.class))})
    })
    public ResponseEntity<ObjectResponseDTO> deleteClient(@PathVariable Long clientDocument) throws BadRequestException {
        this.clientDTOValidation.validateClientDocument(clientDocument);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(this.service.deleteClient(clientDocument));
    }

    @Override
    @GetMapping(IClientEndpoints.CLIENT_ORDER)
    @Operation(summary = "Delete a client")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = Responses.OK, content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ObjectResponseDTO.class))}),
            @ApiResponse(responseCode = "400", description = Responses.BAD_REQUEST, content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ObjectResponseDTO.class))}),
            @ApiResponse(responseCode = "409", description = Responses.CONFLICT, content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ObjectResponseDTO.class))}),
            @ApiResponse(responseCode = "500", description = Responses.INTERNAL_SERVER_ERROR, content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ObjectResponseDTO.class))})
    })
    public ResponseEntity<ObjectResponseDTO> getClientsOrdered(@PathVariable String typeOfData,@PathVariable String direction) throws BadRequestException {
        this.clientDTOValidation.validateTypeOfDataAndDirection(typeOfData, direction);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(this.service.readOrderedClients(typeOfData, direction));
    }
}