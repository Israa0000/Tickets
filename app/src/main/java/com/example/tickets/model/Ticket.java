package com.example.tickets.model;

public class Ticket {
    private EstadoTicket estado;
    private String titulo;
    private String descripcion;
    private String pasos;

    public EstadoTicket getEstado() {
        return estado;
    }

    public void setEstado(EstadoTicket estado) {
        this.estado = estado;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getPasos() {
        return pasos;
    }

    public void setPasos(String pasos) {
        this.pasos = pasos;
    }

    public Ticket(EstadoTicket estado, String titulo, String descripcion, String pasos) {
        this.estado = estado;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.pasos = pasos;
    }
}
