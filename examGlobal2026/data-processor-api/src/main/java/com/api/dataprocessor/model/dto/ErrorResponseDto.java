package com.api.dataprocessor.model.dto;

import lombok.Data;

@Data
public class ErrorResponseDto {
    private String message;
    private int status;
    private long timeStamp;

    public ErrorResponseDto(String message, int status){
        this.message=message;
        this.status=status;
        this.timeStamp=System.currentTimeMillis();
    }
}
