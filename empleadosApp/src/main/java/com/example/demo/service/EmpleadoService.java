package com.example.demo.service;

import com.example.demo.dto.EmpleadoDTO;
import com.example.demo.entity.Empleado;
import com.example.demo.repository.EmpleadoRepository;

public class EmpleadoService {
private EmpleadoRepository empleadoRepository;
	
	public EmpleadoService(EmpleadoRepository empleadoRepository) {
		this.empleadoRepository = empleadoRepository;
	}
	
	public Integer guardarEmpleado(EmpleadoDTO empleadoN ) {
		Empleado empleado = new Empleado();
		empleado.setGender_id(empleadoN.getGender_id());
		empleado.setJob_id(empleadoN.getJob_id());
		empleado.setName(empleadoN.getName());
		empleado.setLast_name(empleadoN.getLast_name());
		empleado.setBirthdate(empleadoN.getBirthdate());
		
		empleado = empleadoRepository.save(empleado);
		return empleado.getId();
	}
	
}
