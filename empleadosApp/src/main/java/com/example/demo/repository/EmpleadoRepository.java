package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Empleado;

public interface empleadoRepository extends JpaRepository<Empleado, Integer>{

}
