package com.exam.Calculator.Entities;

import com.exam.Calculator.Constans.Operations;

public class OperRequest {
    private int id;
    private double numero_1;
    private double numero_2;
    private Operations operacion;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getNumero_1() {
        return numero_1;
    }

    public void setNumero_1(double numero_1) {
        this.numero_1 = numero_1;
    }

    public double getNumero_2() {
        return numero_2;
    }

    public void setNumero_2(double numero_2) {
        this.numero_2 = numero_2;
    }

    public Operations getOperacion() {
        return operacion;
    }

    public void setOperacion(Operations operacion) {
        this.operacion = operacion;
    }
}

