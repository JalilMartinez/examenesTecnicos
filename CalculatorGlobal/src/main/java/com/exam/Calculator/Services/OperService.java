package com.exam.Calculator.Services;

import com.exam.Calculator.Constans.Operations;
import com.exam.Calculator.Entities.OperRequest;
import com.exam.Calculator.Entities.OperResponse;
import com.exam.Calculator.Entities.OperationEntity;
import com.exam.Calculator.Repository.OperationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tools.jackson.databind.ObjectMapper;

import static com.exam.Calculator.Constans.Operations.*;

@Service
public class OperService {

    @Autowired
    private OperationRepository operationRepository;

    public void getOperation(OperResponse operResponse ,String operRequestJson){
        ObjectMapper objectMapper = new ObjectMapper();
        OperRequest operRequest = new OperRequest();
        try{
            operRequest=objectMapper.readValue(operRequestJson,OperRequest.class);
        }catch (Exception e){
            System.out.println(e);
        }
        Operations operation = operRequest.getOperacion();
        switch (operation) {
            case suma:
                this.getSum(operResponse,operRequest,operRequestJson);
                break;
        }

    }

    public void getSum(OperResponse operResponse,OperRequest operRequest, String operRequestJson){
        double sumaR= operRequest.getNumero_1()+ operRequest.getNumero_2();
        operationRepository.save(new OperationEntity(operRequest.getId(),operRequestJson,sumaR));
        OperationEntity operationEntity = operationRepository.getReferenceById((long) operRequest.getId());
        operResponse.setResultado(operationEntity.getResultado());
        operResponse.setId(operationEntity.getId());
    }
}
