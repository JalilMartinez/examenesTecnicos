package com.example.demo.service;


import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.dto.EmpleadoDTO;
import com.example.demo.entity.Empleado;
import com.example.demo.repository.EmpleadoRepository;


import java.time.LocalDate;

 


@Service
public class EmpleadoService {
private EmpleadoRepository empleadoRepository;
	
	public EmpleadoService(EmpleadoRepository empleadoRepository) {
		this.empleadoRepository = empleadoRepository;
	}
	
	public Integer guardarEmpleado(EmpleadoDTO empleadoN ) {
		boolean conf;
		Empleado empleado = new Empleado();
		conf = realizaComprobaciones(empleadoN);
		if(conf==true) {
			LocalDate fecha = LocalDate.parse(empleadoN.getBirthdate());
			empleado.setGender_id(empleadoN.getGender_id());
			empleado.setJob_id(empleadoN.getJob_id());
			empleado.setName(empleadoN.getName());
			empleado.setLast_name(empleadoN.getLast_name());
			empleado.setBirthdate(fecha);
			empleado = empleadoRepository.save(empleado);
		}
		
		
		return empleado.getId();
	}
	public List<Empleado> listaEmpleados(){
		return  empleadoRepository.findAll();
	}
	
	
	
	
	private boolean realizaComprobaciones(EmpleadoDTO empleadoN) {
		
		List<Empleado> empleados=listaEmpleados();
		int año=0;
		LocalDate fecha = LocalDate.parse(empleadoN.getBirthdate());
		año=fecha.getYear();
		
		
		for(int i=0; i<empleados.size();i++) {
			Empleado empleado=empleados.get(i);
			
			if(año>2005) {
				return false;
			}
			if(empleado.getName().equals(empleadoN.getName())) {
				return false;
			}
			if(empleado.getName().equals(empleadoN.getName())) {
				return false;
			}
			
		}

		return true;
	}

	
	
}
