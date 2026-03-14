package com.exam.Calculator.Entities;

import jakarta.persistence.*;
import org.hibernate.annotations.AnyDiscriminatorImplicitValues;

@Entity
@Table (name="operaciones")
public class OperationEntity {
    @Id
    private int id;

    private String contenido;
    private double resultado;
    public OperationEntity (){

    }
    public OperationEntity(int id, String operRequestJson, double sumaR) {
        setId(id);
        setContenido(operRequestJson);
        setResultado(sumaR);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getResultado() {
        return resultado;
    }

    public void setResultado(double resultado) {
        this.resultado = resultado;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }
}
