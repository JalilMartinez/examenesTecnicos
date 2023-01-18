package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Trabajos;

import com.example.demo.repository.TrabajosRepository;

@Service
public class TrabajosService {
	private TrabajosRepository trabajosRepository;
	
	public TrabajosService(TrabajosRepository trabajosRepository) {
		this.trabajosRepository = trabajosRepository;
	}
	
	public List<Trabajos> listaPersona(){
		return  trabajosRepository.findAll();
	}
	public Trabajos obtenerPorId(Integer id) {
		return this.trabajosRepository.findById(id).get();
	
	}
}
