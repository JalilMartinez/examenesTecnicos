package com.api.validator.config;

import com.api.validator.handlerException.TransactionSaveException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Response;
import feign.codec.ErrorDecoder;

import java.io.IOException;
import java.io.InputStream;

public class FeingErrorDecoder implements ErrorDecoder {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public Exception decode(String s, Response response) {
        String message = "Error desconocido en el servidor externo";
        if(response.body() != null){
            try(InputStream bodyIs = response.body().asInputStream()){
                JsonNode jsonNode = objectMapper.readTree(bodyIs);
                if(jsonNode.has("message")){
                    message=jsonNode.get("message").asText();
                }
            }catch (IOException e){
                message = response.reason()!= null ? response.reason():message;
            }
        }


        return new TransactionSaveException(message,new Exception("Feing HTTP status: "+response.status()));
    }
}
