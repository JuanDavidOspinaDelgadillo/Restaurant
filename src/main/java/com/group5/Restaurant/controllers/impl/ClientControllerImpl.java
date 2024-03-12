package com.group5.Restaurant.controllers.impl;

import com.group5.Restaurant.constants.endpoints.IClientEndpoints;
import com.group5.Restaurant.constants.responses.rawResponses.Responses;
import com.group5.Restaurant.domains.dtos.ClientDTO;
import com.group5.Restaurant.constants.responses.objectResponseDTO.ObjectResponseDTO;
import com.group5.Restaurant.controllers.interfaces.IClientController;
import com.group5.Restaurant.services.interfaces.IClientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
@OpenAPIDefinition(
        info = @Info(
                title = "Grandma's food app by Group 5",
                version = "MVP 1.0",
                description = "Application of Grandma's food Restaurant"
        ),
        tags = {
                @Tag(name = "Juan David Ospina Delgadillo", description = "Contact: juandavid.ospina@globant.com"),
                @Tag(name = "Jessika Mariana Hernandez Chapucero", description = "Contact: jessika.hernandez@globant.com"),
                @Tag(name = "Jeimy Alejandra Barbosa Avila", description = "Contact: jeimy.barbosa@globant.com"),
                @Tag(name = "Karen Mileidy Murillo Ramirez", description = "Contact: karen.murillo@globant.com")
        }
)
*/

/**
 * Controller of the ClientService
 * Heard to the url "/client"
 */
@RestController
@RequestMapping(IClientEndpoints.CLIENT_END_POINT)
@AllArgsConstructor
public class ClientControllerImpl implements IClientController {

    final IClientService service;//Service dependency injected

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
    @PostMapping(value = IClientEndpoints.CLIENT_CREATE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ObjectResponseDTO> createClient(@RequestBody ClientDTO clientDTO) {
        return this.service.createClient(clientDTO);
    }

    @Override
    @Operation(summary = "Create a new client")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = Responses.OK, content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ObjectResponseDTO.class))}),
            @ApiResponse(responseCode = "400", description = Responses.BAD_REQUEST, content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ObjectResponseDTO.class))}),
            @ApiResponse(responseCode = "409", description = Responses.CONFLICT, content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ObjectResponseDTO.class))}),
            @ApiResponse(responseCode = "500", description = Responses.INTERNAL_SERVER_ERROR, content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ObjectResponseDTO.class))})
    })
    @GetMapping(IClientEndpoints.CLIENT_READ)
    public ResponseEntity<ObjectResponseDTO> readClient(@PathVariable String clientDocument) {
        return this.service.readClient(clientDocument);
    }

    @Override
    @DeleteMapping(IClientEndpoints.CLIENT_DELETE)
    @Operation(summary = "Create a new client")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = Responses.OK, content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ObjectResponseDTO.class))}),
            @ApiResponse(responseCode = "400", description = Responses.BAD_REQUEST, content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ObjectResponseDTO.class))}),
            @ApiResponse(responseCode = "409", description = Responses.CONFLICT, content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ObjectResponseDTO.class))}),
            @ApiResponse(responseCode = "500", description = Responses.INTERNAL_SERVER_ERROR, content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ObjectResponseDTO.class))})
    })
    public ResponseEntity<ObjectResponseDTO> deleteClient(@PathVariable String clientDocument) {
        return this.service.deleteClient(clientDocument);
    }
}