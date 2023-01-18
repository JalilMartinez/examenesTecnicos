package com.example.demo.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.HorasTrabajadasDTO;
import com.example.demo.entity.Empleado;
import com.example.demo.entity.HorasTrabajadas;
import com.example.demo.entity.Trabajos;
import com.example.demo.repository.HorasTrabajadasRepository;

@Service
public class HorasTrabajadasService {
	private HorasTrabajadasRepository horasTrabajadasRepository;
	@Autowired
	private EmpleadoService empleadoService;
	@Autowired
	private TrabajosService trabajosService;
	
	LocalDate ahora = LocalDate.now();

	
	
	public HorasTrabajadasService(HorasTrabajadasRepository horasTrabajadasRepository) {
		this.horasTrabajadasRepository = horasTrabajadasRepository;
	}
	
	public String guardarHorasTrabajadas(HorasTrabajadasDTO horasN ) {
		boolean conf=true;
		HorasTrabajadas horas = new HorasTrabajadas();
		conf = realizaComprobaciones(horasN);
		if(conf==true) {
			LocalDate fecha = LocalDate.parse(horasN.getWorked_date());
			horas.setEmployee_id(horasN.getEmployee_id());
			horas.setWorked_date(fecha);
			horas.setWorked_hours(horasN.getWorked_hours());
			horasTrabajadasRepository.save(horas);
			return "{ \"total_worked_hours\":"+horas.getWorked_hours()+",\"success\":"+true+"}";
		}
		return "{ \"total_worked_hours\":"+null+",\"success\":"+false+"}";
		
		
		
	}
	public List<HorasTrabajadas> listaHorasTrabajadas(){
		return  horasTrabajadasRepository.findAll();
	}
	public String totalHorasTrabajadas(HorasTrabajadasDTO data) {
		Integer id= data.getEmployee_id();
		LocalDate fecha_inicio = LocalDate.parse(data.getStart_date());
		LocalDate fecha_fin = LocalDate.parse(data.getEnd_date());
		List<HorasTrabajadas> horas=listaHorasTrabajadas();
		int total=0;
		if(fecha_fin.isAfter(fecha_inicio)) {
			for(int i=0; i<horas.size();i++) {
				HorasTrabajadas horasi = horas.get(i);
				LocalDateTime  conv=LocalDateTime.ofInstant(horasi.getWorked_date().toInstant(), ZoneId.systemDefault());
				LocalDate convDate=conv.toLocalDate();
				
				if(horasi.getEmployee_id()==id) {
					if(convDate.isEqual(fecha_inicio)||convDate.isEqual(fecha_fin)) {
						total+=horasi.getWorked_hours();
					}
					if(convDate.isBefore(fecha_fin)) {
						if(convDate.isAfter(fecha_inicio)) {
							total+=horasi.getWorked_hours();
						}
					}
					
				}
			}
		}else {
			return "{ \"total_worked_hours\":"+total+",\"success\":"+false+"}";
		}
		return "{ \"total_worked_hours\":"+total+",\"success\":"+true+"}";
	}
	
	public String totalPago(HorasTrabajadasDTO data) {
		
		LocalDate fecha_inicio = LocalDate.parse(data.getStart_date());
		LocalDate fecha_fin = LocalDate.parse(data.getEnd_date());
		List<HorasTrabajadas> horas=listaHorasTrabajadas();
	
		int total=0;
		if(fecha_fin.isAfter(fecha_inicio)) {
			for(int i=0; i<horas.size();i++) {
				Empleado empleado = empleadoService.obtenerPorId(data.getEmployee_id());
				Trabajos trabajo = trabajosService.obtenerPorId(empleado.getJob_id());
				HorasTrabajadas horasi = horas.get(i);
				LocalDateTime  conv=LocalDateTime.ofInstant(horasi.getWorked_date().toInstant(), ZoneId.systemDefault());
				LocalDate convDate=conv.toLocalDate();
				if(horasi.getEmployee_id()==data.getEmployee_id()) {
					if(convDate.isEqual(fecha_inicio)||convDate.isEqual(fecha_fin)) {
						total+=trabajo.getSalary();
					}
					if(convDate.isBefore(fecha_fin)) {
						if(convDate.isAfter(fecha_inicio)) {
							total+=trabajo.getSalary();
						}
					}	
				}		
			}
		}else {
			return "{ \"payment\":"+total+",\"success\":"+false+"}";
		}
		
		return "{ \"payment\":"+total+",\"success\":"+true+"}";
	}
	
	private boolean realizaComprobaciones(HorasTrabajadasDTO horasN) {
			
			List<HorasTrabajadas> horas=listaHorasTrabajadas();
			
			LocalDate fecha = LocalDate.parse(horasN.getWorked_date());
			
			for(int i=0; i<horas.size();i++) {
				
				HorasTrabajadas horasi = horas.get(i);
				
				Date date = horasi.getWorked_date();
				LocalDate datei = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
				System.out.print(datei);
				//solo realizara el siguiente proceso con el empleado asociado al employee_id
				if(horasi.getEmployee_id()==horasN.getEmployee_id()) {
					//si ya existe la fecha entonces no pasa
					if(datei.isEqual(fecha)) {
						return false;
					}
				}
			}
			
			if(horasN.getWorked_hours()>20) {
				return false;
			}
			if(fecha.isAfter(ahora)) {
				return false;
			}
			return true;
	}
}
