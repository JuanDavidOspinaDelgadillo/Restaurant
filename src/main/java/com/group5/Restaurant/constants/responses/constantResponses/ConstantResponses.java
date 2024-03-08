package com.group5.Restaurant.constants.responses.constantResponses;

import com.group5.Restaurant.constants.responses.rawResponses.Responses;
import com.group5.Restaurant.constants.responses.objectResponseDTO.CorrectResponseDTO;
import com.group5.Restaurant.constants.responses.objectResponseDTO.ObjectResponseDTO;
import com.group5.Restaurant.constants.responses.objectResponseDTO.WrongResponseDTO;
import com.group5.Restaurant.constants.responses.responseCodes.ResponseCodes;
import com.group5.Restaurant.exceptions.BadRequestException;
import com.group5.Restaurant.exceptions.ConflictException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * Constants responses in the process of business logic
 */
public class ConstantResponses {
    private static final LocalDateTime NOW = LocalDateTime.now();//Current date and hour

    /**
     * Constant of the successful responses
     */
    public static final Function<String, ResponseEntity<ObjectResponseDTO>> OK = information -> ResponseEntity
                                                                                                    .ok()
                                                                                                    .body(CorrectResponseDTO.builder()
                                                                                                            .object(Responses.OK)
                                                                                                            .code(ResponseCodes.OK)
                                                                                                            .httpStatusCode(HttpStatus.OK.value())
                                                                                                            .description(information)
                                                                                                            .timeStamp(NOW)
                                                                                                            .build());

    /**
     * Constant of the bad request when the data already exist in the DataBase
     */
    public static final Function<String, ResponseEntity<ObjectResponseDTO>> BAD_REQUEST_ALREADY_EXIST = information -> {
        BadRequestException badRequestException = new BadRequestException(Responses.BAD_REQUEST);
        return ResponseEntity
                .badRequest()
                .body(WrongResponseDTO.builder()
                        .httpStatusCode(HttpStatus.BAD_REQUEST.value())
                        .code(ResponseCodes.ALREADY_EXIST)
                        .description(information)
                        .timeStamp(NOW)
                        .exception(badRequestException)
                        .build());
    };


    /**
     * Constant of the bad request general response
     */
    public static final BiFunction<String, String, ResponseEntity<ObjectResponseDTO>> BAD_REQUEST =
            (information, responseCode) -> {
                BadRequestException badRequestException = new BadRequestException(Responses.BAD_REQUEST);
                return ResponseEntity
                        .badRequest()
                        .body(WrongResponseDTO.builder()
                                .exception(badRequestException)
                                .timeStamp(NOW)
                                .description(information)
                                .code(responseCode)
                                .httpStatusCode(HttpStatus.BAD_REQUEST.value())
                                .build());
            };

    /**
     * Constant of the internal server error responses
     */
    public static final BiFunction<String, Exception, ResponseEntity<ObjectResponseDTO>> INTERNAL_SERVER_ERROR =
            (information, exception) ->
                    ResponseEntity
                        .status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body(WrongResponseDTO.builder()
                                .httpStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                                .code(ResponseCodes.INTERNAL_SERVER_ERROR)
                                .description(information)
                                .timeStamp(NOW)
                                .exception(exception)
                                .build());

    /**
     * Constant of the conflict with data responses
     */
    public static final Function<String, ResponseEntity<ObjectResponseDTO>> CONFLICT = information -> {
        ConflictException conflictException = new ConflictException(Responses.CONFLICT);
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(WrongResponseDTO.builder()
                        .httpStatusCode(HttpStatus.CONFLICT.value())
                        .code(ResponseCodes.ALREADY_EXIST)
                        .description(information)
                        .timeStamp(NOW)
                        .exception(conflictException)
                        .build());
    };
}