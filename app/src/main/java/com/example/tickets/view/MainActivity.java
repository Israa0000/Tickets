package com.example.tickets.view;

import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.tickets.R;
import com.example.tickets.viewModel.EditarFragment;
import com.example.tickets.model.EstadoTicket;
import com.example.tickets.viewModel.ListaFragment;
import com.example.tickets.model.Ticket;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditarFragment fragmentMain;
    ListaFragment fragmentLista;
    Button btncrearTicket;
    Button btnVerLista;
    Button btnEditarTicket;
    ArrayList<Ticket> tickets;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        tickets = new ArrayList<>();
        tickets.add(new Ticket(EstadoTicket.NUEVO, "Ticket 1", "Descripcion 1","Pasos 1"));
        tickets.add(new Ticket(EstadoTicket.PENDIENTE, "Ticket 2", "Descripcion 2","Pasos 2"));

        setContentView(R.layout.activity_main);

        fragmentLista = new ListaFragment();
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