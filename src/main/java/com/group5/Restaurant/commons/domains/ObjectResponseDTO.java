package com.group5.Restaurant.commons.domains;

import lombok.Builder;
import lombok.Data;
import java.io.Serializable;

/**
 * Object serialized to send and receive responses
 */
@Builder
@Data
public class ObjectResponseDTO implements Serializable {
    public int httpCode;
    public Object object;
}