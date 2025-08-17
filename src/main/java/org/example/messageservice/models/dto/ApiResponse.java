package org.example.messageservice.models.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ApiResponse <T> {
    private Integer code ;
    private String message ;
    private T data ;
}
