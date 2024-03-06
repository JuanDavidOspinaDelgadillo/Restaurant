package com.group5.Restaurant.commons.responsesObjectDTO;

import java.time.LocalDateTime;

/**
 * Object serialized to send and receive responses
 */
public class CorrectResponseDTO extends ResponseObjectDTO {
    public CorrectResponseDTO(Builder builder) {
        super(builder);
    }

    public CorrectResponseDTO(String code, String description, LocalDateTime timeStamp) {
        super(code, description, timeStamp);
    }

    public static class CorrectResponseBuilder extends Builder{
        @Override
        public CorrectResponseDTO build(){
            return new CorrectResponseDTO(this);
        }
    }
}