package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;


import com.example.demo.service.TrabajosService;

@Controller
public class TrabajosController {
	@Autowired
	public TrabajosService trabajosService;
}
