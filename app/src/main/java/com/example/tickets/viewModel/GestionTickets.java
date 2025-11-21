package com.example.tickets.viewModel;

import android.content.Context;
import android.util.Log;

import com.example.tickets.model.Ticket;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class GestionTickets {

    public static void crearArchivo(Context context) {
        // /data/user/0/com.example.tickets/files/directorio/
        File directorio = new File("/data/user/0/com.example.tickets/files/directorio");
        if (!directorio.exists()) {
            directorio.mkdirs();
        }

        File archivo = new File(directorio, "BBDD.txt");
        try {
            if (archivo.mkdirs()) {
                archivo.createNewFile();

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // crear funciones para eliminar texto y leer
    // con los apuntes de acceso a datos

    static ArrayList<Ticket> leerBBDD() {
        ArrayList<Ticket> lista = new ArrayList<>();
        File archTickets = new File("/data/user/0/com.example.tickets/files/directorio");

        int espacio = 32; //Representa un espacio de texto en binario
        int saltoLinea = 10; // Representa un salto de linea
        int cr = 13; // Representa un carriage return

        String id = "";
        String estado = "";
        String titulo = "";
        String descripcion = "";
        String pasos = "";

        if (!archTickets.exists()) return lista;
        try {
            FileReader filereader = new FileReader(archTickets);
            int i;
            String linea = "";
            while ((i = filereader.read()) != -1) {
                if ((char) i == "\n"){
                    String[] partes = linea.split(",");
                    if (partes.length == 5){
                        id = partes[0];
                        estado = partes[1];
                        titulo = partes[2];
                        descripcion = partes[3];
                        pasos = partes[4];

                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
