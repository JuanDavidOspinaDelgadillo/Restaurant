package com.group5.Restaurant.commons.responsesObjectDTO;

import java.time.LocalDateTime;

public class WrongResponseDTO extends ResponseObjectDTO {
    private Exception exception;
    public WrongResponseDTO(Builder builder) {
        super(builder);
    }

    public WrongResponseDTO(String code, String description, LocalDateTime timeStamp, Exception exception){
        super(code, description, timeStamp);
        this.exception = exception;
    }

    public static class WrongResponseBuilder extends Builder {
        @Override
        public WrongResponseDTO build(){
            return new WrongResponseDTO(this);
        }
    }
}