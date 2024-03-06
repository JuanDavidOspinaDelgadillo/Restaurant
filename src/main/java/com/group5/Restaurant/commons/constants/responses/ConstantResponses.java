package com.group5.Restaurant.commons.constants.responses;

import com.group5.Restaurant.commons.responsesObjectDTO.CorrectResponseDTO;
import com.group5.Restaurant.commons.responsesObjectDTO.ResponseObjectDTO;
import com.group5.Restaurant.commons.responsesObjectDTO.WrongResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.UUID;
import java.util.function.Function;
import java.util.function.Supplier;

public class ConstantResponses {
    private static final LocalDateTime NOW = LocalDateTime.now();
    public static final Supplier<ResponseEntity<ResponseObjectDTO>> OK = () -> ResponseEntity
                                                                                .ok(new CorrectResponseDTO(HttpStatus.OK.toString(), Responses.OK, NOW));
    public static final Supplier<ResponseEntity<ResponseObjectDTO>> BAD_REQUEST = () -> {
        IllegalArgumentException exception = new IllegalArgumentException();
        return ResponseEntity
                .badRequest()
                .body(new WrongResponseDTO(UUID.randomUUID().toString(), Responses.BAD_REQUEST, NOW, exception));
    };

    public static final Function<RuntimeException, ResponseEntity<ResponseObjectDTO>> INTERNAL_SERVER_ERROR = e ->
            ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new WrongResponseDTO(UUID.randomUUID().toString(), Responses.INTERNAL_SERVER_ERROR, NOW, e));

    public static final Supplier<ResponseEntity<ResponseObjectDTO>> CONFLICT = () -> {
        IllegalStateException exception = new IllegalStateException();
        return ResponseEntity
                .status(HttpStatus.CONFLICT.value())
                .body(new WrongResponseDTO(UUID.randomUUID().toString(), Responses.CONFLICT, NOW, exception));
    };
}