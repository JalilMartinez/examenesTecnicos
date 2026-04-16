package com.api.dataprocessor.controller;


import com.api.dataprocessor.model.EntityToUpdate;
import com.api.dataprocessor.model.dto.TransactionOutcomeEntityListDto;
import com.api.dataprocessor.model.TransactionOutcomeResponse;
import com.api.dataprocessor.model.dto.*;
import com.api.dataprocessor.services.DataProcessorService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.Map;

@RestController
@RequestMapping("/processortransactionapi")
public class DataProcessorController {

    private final DataProcessorService dataProcessorService;
    public static final Logger log = LoggerFactory.getLogger(AuthenticatorController.class);


    public DataProcessorController (DataProcessorService dataProcessorService){
        this.dataProcessorService = dataProcessorService;
    }

    @PostMapping("/save")
    public ResponseEntity<?> processTransaction (@Valid @RequestBody TransactionRequestDto transactionRequestDto){
        log.info("[DataProcessorController] Recibida solicitud para procesar transaccion cliente : {}, importe : {}, operacion : {}",transactionRequestDto.getCliente(),transactionRequestDto.getImporte(),transactionRequestDto.getOperacion());
        TransactionOutcomeResponse transactionOutcome = dataProcessorService.doTransaction(transactionRequestDto);
        if (!transactionOutcome.isCorrect()){
            return ResponseEntity.badRequest().body( Map.of(
                    "message",transactionOutcome.getMessage()
            ));
        }
        log.info("[dataProcessorService.doTransaction] Transaccion guardada con id : {}", transactionOutcome.getTransactionResponseDto().getId());
        return ResponseEntity.ok(transactionOutcome.getTransactionResponseDto());
    }

    @GetMapping("/getAllTransactions")
    public ResponseEntity<?> getAllTransactions(@RequestParam int page, @RequestParam int size){
        log.info("[DataProcessorController] Recibida solicitud para obtener transacciones");
        TransactionOutcomeEntityListDto transactionOutcome = dataProcessorService.getAllTransactions(page,size);
        if (!transactionOutcome.isCorrect()){
            log.error("[DataProcessorController] Error al obtener elementos : {}",transactionOutcome.getMessage());
            return ResponseEntity.badRequest().body( Map.of(
                    "message",transactionOutcome.getMessage()
            ));
        }
        log.info("[DataProcessorController]  Lisa de elementos obtenida, size : {}",transactionOutcome.getEntityList().size());
        return ResponseEntity.ok(transactionOutcome);
    }

    @PatchMapping("/updateEstatusTransaction")
    public ResponseEntity<?> updateEstatusTransaction(@RequestBody EntityToUpdate entityToUpdate){
        log.info("[DataProcessorController] Recibida solicitud para update tipo : {}", entityToUpdate.getEstatus());
        TransactionOutcomeResponse transactionOutcome = dataProcessorService.updateEstatusTransaction(entityToUpdate);
        if (!transactionOutcome.isCorrect()){
            return ResponseEntity.badRequest().body( Map.of(
                    "message",transactionOutcome.getMessage()
            ));
        }
        log.info("[DataProcessorController] Transaccion actualizada, nuevo estatus : {}, id : {}", transactionOutcome.getTransactionResponseDto().getEstatus(), transactionOutcome.getTransactionResponseDto().getId());
        return ResponseEntity.ok(transactionOutcome.getTransactionResponseDto());
    }

}