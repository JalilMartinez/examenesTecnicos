package com.example.demo.service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.dto.HorasTrabajadasDTO;

import com.example.demo.entity.HorasTrabajadas;
import com.example.demo.repository.HorasTrabajadasRepository;

@Service
public class HorasTrabajadasService {
	private HorasTrabajadasRepository horasTrabajadasRepository;
	LocalDate ahora = LocalDate.now();

	
	
	public HorasTrabajadasService(HorasTrabajadasRepository horasTrabajadasRepository) {
		this.horasTrabajadasRepository = horasTrabajadasRepository;
	}
	
	public Integer guardarHorasTrabajadas(HorasTrabajadasDTO horasN ) {
		boolean conf=true;
		HorasTrabajadas horas = new HorasTrabajadas();
		conf = realizaComprobaciones(horasN);
		if(conf==true) {
			LocalDate fecha = LocalDate.parse(horasN.getWorked_date());
			horas.setEmployee_id(horasN.getEmployee_id());
			horas.setWorked_date(fecha);
			horas.setWorked_hours(horasN.getWorked_hours());
			horasTrabajadasRepository.save(horas);
		}
		
		
		return horas.getId();
	}
	public List<HorasTrabajadas> listaHorasTrabajadas(){
		return  horasTrabajadasRepository.findAll();
	}
	
	private boolean realizaComprobaciones(HorasTrabajadasDTO horasN) {
			
			List<HorasTrabajadas> horas=listaHorasTrabajadas();
			
			LocalDate fecha = LocalDate.parse(horasN.getWorked_date());
			
			for(int i=0; i<horas.size();i++) {
				System.out.print(i);
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
