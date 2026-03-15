package com.exam.Calculator.Controller;

import com.exam.Calculator.Entities.OperRequest;
import com.exam.Calculator.Entities.OperResponse;
import com.exam.Calculator.Services.OperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Calculatorapi")
public class SumController {

    @Autowired
    OperService operService;

    @PostMapping("")
    private ResponseEntity<OperResponse> sum (@RequestBody String operRequestJson){
        OperResponse operResponse = new OperResponse();
        operService.getOperation(operResponse,operRequestJson);
        return ResponseEntity.ok(operResponse);
    }
}
