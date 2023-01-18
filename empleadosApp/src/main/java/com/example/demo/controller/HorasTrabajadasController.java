package com.example.demo.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


import com.example.demo.dto.HorasTrabajadasDTO;
import com.example.demo.service.HorasTrabajadasService;

@Controller
public class HorasTrabajadasController {
	@Autowired
	public HorasTrabajadasService horasTrabajadasService;
	
	
	@PutMapping("/guardarHoras")
	public ResponseEntity <String> guardarHoras(@RequestBody HorasTrabajadasDTO data){
		String id = "";
		try {
			id=this.horasTrabajadasService.guardarHorasTrabajadas(data);
		}catch(Exception e){
			System.out.println("Error");
		}
		return new ResponseEntity<>(id,HttpStatus.OK);
	}
	
	@GetMapping("/totalHorasTrabajadas")
	public ResponseEntity<String> totalHorasTrabajadas(@RequestBody HorasTrabajadasDTO data){
        String resultado = horasTrabajadasService.totalHorasTrabajadas(data);
        return new ResponseEntity<>(resultado, HttpStatus.OK);
    }
	
	@GetMapping("/totalPago")
	public ResponseEntity<String> totalPago(@RequestBody HorasTrabajadasDTO data){
		
        String total = horasTrabajadasService.totalPago(data);
        return new ResponseEntity<>(total, HttpStatus.OK);
    }
}
