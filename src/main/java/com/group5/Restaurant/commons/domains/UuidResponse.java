package com.group5.Restaurant.commons.domains;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.UUID;

@Data
@Builder
public class UuidResponse  implements Serializable {
    public String message;
    public int httpCode;
    public UUID uuid;
}