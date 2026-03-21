package com.api.validator.config;

import com.api.validator.handlerException.TransactionSaveException;
import feign.Response;
import feign.codec.ErrorDecoder;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;

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
                    message=jsonNode.get("message").asString();
                }
            }catch (IOException e){
                message = response.reason()!= null ? response.reason():message;
            }
        }


        return new TransactionSaveException(message,new Exception("Feing HTTP status: "+response.status()));
    }
}
