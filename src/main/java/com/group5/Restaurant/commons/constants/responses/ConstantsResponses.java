package com.group5.Restaurant.commons.constants.responses;

import com.group5.Restaurant.commons.domains.ObjectResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * Constants Responses in each service
 */
public class ConstantsResponses {

    /**
     * Bad Request Operation
     * Code 400
     */
    public static final ResponseEntity<ObjectResponseDTO> BAD_REQUEST = ResponseEntity.badRequest().body(ObjectResponseDTO.builder()
            .httpCode(HttpStatus.BAD_REQUEST.value())
            .object(Responses.BAD_REQUEST)
            .build());

    /**
     * Internal Server Error operation
     * Code 500
     */
    public static final ResponseEntity<ObjectResponseDTO> INTERNAL_SERVER_ERROR = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).
            body(ObjectResponseDTO.builder()
                    .object(Responses.INTERNAL_SERVER_ERROR)
                    .httpCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                    .build());

    /**
     * Successful operation
     * Code 200
     */
    public static final ResponseEntity<ObjectResponseDTO> OK = ResponseEntity.ok(ObjectResponseDTO.builder()
            .httpCode(HttpStatus.OK.value())
            .object(Responses.OK)
            .build());
}