package com.group5.Restaurant.constants.responses.objectResponseDTO;

import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
/**
 * Object serialized to send and receive wrong responses
 * Son of ObjectResponseDTO
 */
public class WrongResponseDTO extends ObjectResponseDTO implements Serializable {
    private Integer httpStatusCode;
    private String code;
    private String description;
    private LocalDateTime timeStamp;
    private Exception exception;

}