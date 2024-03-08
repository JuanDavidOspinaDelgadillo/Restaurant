package com.group5.Restaurant.constants.responses.objectResponseDTO;

import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
/**
 * Object serialized to send and receive correct responses
 * Son of ObjectResponseDTO
 */
public class CorrectResponseDTO extends ObjectResponseDTO implements Serializable {
    private Integer httpStatusCode;
    private String code;
    private String description;
    private LocalDateTime timeStamp;
    private Object object;
}