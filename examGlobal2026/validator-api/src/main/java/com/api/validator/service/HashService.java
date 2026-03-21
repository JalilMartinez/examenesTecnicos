package com.api.validator.service;

import com.api.validator.handlerException.AlgorithShaException;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
@Service
public class HashService {

    public String generateSha(String forGenerateKey){
        try{
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] sha = md.digest(forGenerateKey.getBytes());

            StringBuilder hasString = new StringBuilder();
            for (byte b:sha){
                hasString.append(String.format("%02x",b));
            }
            return hasString.toString();
        }catch (NoSuchAlgorithmException e) {
            throw new AlgorithShaException("Algoritmo de cifrado no disponible",e);
        }

    }
}
