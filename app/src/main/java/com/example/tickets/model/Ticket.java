package com.example.tickets.model;

import java.util.ArrayList;

public class Ticket {

    private static ArrayList <Ticket> BBDDTickets = new ArrayList<>();
    private int id;
    private EstadoTicket estado;
    private String titulo;
    private String descripcion;
    private String pasos;

    public Ticket(EstadoTicket estado, String titulo, String descripcion, String pasos) {
        this.estado = estado;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.pasos = pasos;
    }

    public Ticket (){
        estado = EstadoTicket.NUEVO;
        titulo = "";
        descripcion = "";
        pasos = "";
    }
    public int getId(){return id;}
    public void setId(int id){this.id = id;}

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

    public ArrayList<Ticket>getBBDDTickets(ArrayList BBDDTickets){
        return BBDDTickets;
    }

    public void addTicket(Ticket ticket){
        BBDDTickets.add(ticket);
    }

    public static void updateTicket(int id, Ticket updatedTicket) {
        for (int i = 0; i < BBDDTickets.size(); i++) {
            if (BBDDTickets.get(i).getId() == id) {
                BBDDTickets.set(i, updatedTicket);
            }
        }
    }

    public static Ticket getTicketById(int id) {
        for (Ticket ticket : BBDDTickets) {
            if (ticket.getId() == id) {
                return ticket;
            }
        }
        return null;
    }

    public String TicketToString(){
        return "Ticket{" +
                "id=" + id +
                ", estado=" + estado.toString() +
                ", titulo='" + titulo.toString() + '\'' +
                ", descripcion='" + descripcion.toString() + '\'' +
                ", pasos='" + pasos.toString() + '\'' +
                '}';
    }
}
