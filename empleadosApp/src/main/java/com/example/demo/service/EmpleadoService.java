package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.EmpleadoDTO;
import com.example.demo.entity.Empleado;
import com.example.demo.repository.EmpleadoRepository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
			empleado.setGender_id(empleadoN.getGender_id());
			empleado.setJob_id(empleadoN.getJob_id());
			empleado.setName(empleadoN.getName());
			empleado.setLast_name(empleadoN.getLast_name());
			empleado.setBirthdate(empleadoN.getBirthdate());
			empleado = empleadoRepository.save(empleado);
		}
		
		
		
		return empleado.getId();
	}
	private boolean realizaComprobaciones(EmpleadoDTO empleadoN) {
		SimpleDateFormat formato =new SimpleDateFormat("yyyy/MM/dd");
		Date fecha = null;
		try {
			fecha = formato.parse("2005/01/01");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<Empleado> empleados=listaEmpleados();
		for(int i=0; i<empleados.size();i++) {
			Empleado empleado=empleados.get(i);
			if(empleado.getName()==empleadoN.getName()) {
				return false;
			}
			if(empleado.getName()==empleadoN.getName()) {
				return false;
			}
			if(empleado.getBirthdate().getYear()>fecha.getYear()) {
				return false;
			}
		}
		
		return true;
	}

	public List<Empleado> listaEmpleados(){
		return  empleadoRepository.findAll();
	}
	
}
