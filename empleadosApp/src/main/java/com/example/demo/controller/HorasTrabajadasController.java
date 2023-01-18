package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.demo.dto.HorasTrabajadasDTO;
import com.example.demo.service.HorasTrabajadasService;

@Controller
public class HorasTrabajadasController {
	@Autowired
	public HorasTrabajadasService horasTrabajadasService;
	
	@PutMapping("/guardarHoras")
	public ResponseEntity <Integer> guardarHoras(@RequestBody HorasTrabajadasDTO data){
		Integer id = 0;
		try {
			id=this.horasTrabajadasService.guardarHorasTrabajadas(data);
		}catch(Exception e){
			System.out.println("Error");
		}
		if(id==0) {
			return new ResponseEntity<>(id,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(id,HttpStatus.OK);
	}
}
