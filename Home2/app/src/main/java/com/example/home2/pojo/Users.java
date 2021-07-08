package com.example.home2.pojo;

public class Users {
    String nombres;;
    String servicio;
    String numero;

    public Users(String nombres,String numero,String servicio) {
        this.nombres = nombres;

        this.servicio = servicio;
        this.numero = numero;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getServicio() {
        return servicio;
    }

    public void setServicio(String servicio) {
        this.servicio = servicio;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

}
