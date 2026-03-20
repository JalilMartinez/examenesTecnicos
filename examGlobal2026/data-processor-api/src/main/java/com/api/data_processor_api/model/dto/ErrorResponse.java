package com.api.data_processor_api.model.dto;

import lombok.Data;

@Data
public class ErrorResponse {
    private String message;
    private int status;
    private long timeStamp;

    public ErrorResponse(String message, int status){
        this.message=message;
        this.status=status;
        this.timeStamp=System.currentTimeMillis();
    }
}
