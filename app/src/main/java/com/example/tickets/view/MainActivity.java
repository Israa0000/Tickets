package com.example.tickets.view;

import static com.example.tickets.viewModel.GestionTickets.guardarTickets;

import android.os.Bundle;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.tickets.R;
import com.example.tickets.viewModel.EditarFragment;
import com.example.tickets.model.EstadoTicket;
import com.example.tickets.viewModel.GestionTickets;
import com.example.tickets.viewModel.ListaFragment;
import com.example.tickets.model.Ticket;
import com.example.tickets.viewModel.GestionTickets;

import java.io.File;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditarFragment editarFragment;
    ListaFragment fragmentLista;
    ImageButton btncrearTicket;
    ImageButton btnVerLista;
    ArrayList<Ticket> tickets;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);


        //iniciar los fragments
        editarFragment = new EditarFragment();
        fragmentLista = new ListaFragment();

        //botones
        btncrearTicket = findViewById(R.id.btnCrearTicket);
        btnVerLista = findViewById(R.id.btnVerLista);

        btncrearTicket.setOnClickListener(v -> irCrearTicket());
        btnVerLista.setOnClickListener(v -> irLista());

        //ticket de prueba

        tickets = GestionTickets.leerBBDD(this);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.framelayout, fragmentLista);
        transaction.commit();
    }

    public void guardarCambios() {
        GestionTickets.guardarTickets(this, tickets);
    }

    void irCrearTicket(){
        editarFragment.setTicketActual(null);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.framelayout, editarFragment);

        transaction.commit();
    }

    public void EditarTicket(Ticket ticketSeleccionado) {
        editarFragment.setTicketActual(ticketSeleccionado);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.framelayout, editarFragment);
        transaction.commit();
    }

    void irLista(){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.framelayout, fragmentLista);
        transaction.commit();
    }


    public ArrayList<Ticket> getTickets() {
        return tickets;
    }

    public void test(){

    }
}