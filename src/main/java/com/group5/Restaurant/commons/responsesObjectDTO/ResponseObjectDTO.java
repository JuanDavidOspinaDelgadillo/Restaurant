package com.group5.Restaurant.commons.responsesObjectDTO;

import java.io.Serializable;
import java.time.LocalDateTime;

public class ResponseObjectDTO implements Serializable {
    protected String code;
    protected String description;
    protected LocalDateTime timeStamp;

    public static class Builder {
        protected String code;
        protected String description;
        protected LocalDateTime timeStamp;
        protected Exception exception;
        public Builder(){}
        public Builder code(String code) {
            this.code = code;
            return this;
        }
        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Builder timeStamp(LocalDateTime timeStamp) {
            this.timeStamp = timeStamp;
            return this;
        }

        public Builder exception(RuntimeException exception){
            this.exception = exception;
            return this;
        }

        public ResponseObjectDTO build(){
            return new ResponseObjectDTO(this);
        }
    }

    public ResponseObjectDTO(Builder builder) {
        this.code = builder.code;
        this.description = builder.description;
        this.timeStamp = builder.timeStamp;
    }

    public ResponseObjectDTO(String code, String description, LocalDateTime timeStamp) {
        this.code = code;
        this.timeStamp = timeStamp;
        this.description = description;
    }
}