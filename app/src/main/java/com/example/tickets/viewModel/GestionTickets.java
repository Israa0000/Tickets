package com.example.tickets.viewModel;

import android.content.Context;
import android.util.Log;

import android.content.Context;
import com.example.tickets.model.EstadoTicket;
import com.example.tickets.model.Ticket;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class GestionTickets {
    private static final String NOMBRE_DIRECTORIO = "directorio";
    private static final String NOMBRE_ARCHIVO = "BBDD.txt";

    public static void guardarTickets(Context context, ArrayList<Ticket> tickets) {
        File directorio = new File(context.getFilesDir(), NOMBRE_DIRECTORIO);
        if (!directorio.exists()) {
            directorio.mkdirs();
        }

        File archivo = new File(directorio, NOMBRE_ARCHIVO);

        try {
            FileWriter writer = new FileWriter(archivo, false);

            for (Ticket ticket : tickets) {

                writer.write(ticket.TicketToString());
            }
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<Ticket> leerBBDD(Context context) {
        ArrayList<Ticket> lista = new ArrayList<>();
        File directorio = new File(context.getFilesDir(), NOMBRE_DIRECTORIO);
        File archivo = new File(directorio, NOMBRE_ARCHIVO);

        if (!archivo.exists()) {
            return lista;
        }

        try {
            FileReader fileReader = new FileReader(archivo);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String linea;

            while ((linea = bufferedReader.readLine()) != null) {
                String[] partes = linea.split(",");

                if (partes.length >= 5) {
                    try {
                        int id = Integer.parseInt(partes[0]);
                        EstadoTicket estado = EstadoTicket.valueOf(partes[1]);
                        String titulo = partes[2];
                        String descripcion = partes[3];
                        String pasos = partes[4];

                        Ticket ticketRecuperado = new Ticket(id, estado, titulo, descripcion, pasos);
                        lista.add(ticketRecuperado);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lista;
    }
}
