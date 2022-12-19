package com.example.adeudados.modelo;

public class Adeudos {

    String Contacto , Deuda , Fecha_Limite , Nombre_Deudor;
    public Adeudos(){}

    public Adeudos(String Contacto , String Deuda , String Fecha_Limite, String Nombre_Deudor){
        this.Contacto = Contacto;
        this.Deuda = Deuda;
        this.Fecha_Limite=Fecha_Limite;
        this.Nombre_Deudor=Nombre_Deudor;
    }

    public String getContacto() {
        return Contacto;
    }

    public void setContacto(String contacto) {
        Contacto = contacto;
    }

    public String getDeuda() {
        return Deuda;
    }

    public void setDeuda(String deuda) {
        Deuda = deuda;
    }

    public String getFecha_Limite() {
        return Fecha_Limite;
    }

    public void setFecha_Limite(String fecha_Limite) {
        Fecha_Limite = fecha_Limite;
    }

    public String getNombre_Deudor() {
        return Nombre_Deudor;
    }

    public void setNombre_Deudor(String nombre_Deudor) {
        Nombre_Deudor = nombre_Deudor;
    }
}
