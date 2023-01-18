package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


import com.example.demo.dto.EmpleadoDTO;
import com.example.demo.entity.Empleado;
import com.example.demo.service.EmpleadoService;


@Controller
public class EmpleadoController {
	@Autowired
	public EmpleadoService empleadoService;
	
	@PutMapping("/guardarEmpleado")
	public ResponseEntity <Integer> guardarPersona(@RequestBody EmpleadoDTO data){
		Integer id = 0;
		try {
			id=this.empleadoService.guardarEmpleado(data);
		}catch(Exception e){
			System.out.println("Error");
		}
		if(id==0) {
			return new ResponseEntity<>(id,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(id,HttpStatus.OK);
	}
	@GetMapping("/listaEmpleadosPorPuesto")
    public ResponseEntity<List<Empleado>> listaEmpleadosPorPuesto(@RequestBody EmpleadoDTO data){
        List<Empleado> empleados = empleadoService.listaEmpleadosPorPuesto(data.getJob_id());
        return new ResponseEntity<List<Empleado>>(empleados, HttpStatus.OK);
    }
	
	
}
