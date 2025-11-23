package com.example.tickets.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.tickets.R;
import com.example.tickets.model.Ticket;

import java.util.ArrayList;

public class ListaFragment extends Fragment {

    private LinearLayout itemLista;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_lista, container, false);

        itemLista = rootView.findViewById(R.id.itemLista);

        MainActivity mainActivity = (MainActivity) getActivity();
        agregarItems(mainActivity.getTickets());
        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();
        if (getActivity() instanceof MainActivity) {
            ((MainActivity) getActivity()).test();
        }
    }

    private void agregarItems(ArrayList<Ticket> tickets) {

        itemLista.removeAllViews();

        LayoutInflater inflater = LayoutInflater.from(getContext());
        for (Ticket ticket : tickets) {
            View itemView = inflater.inflate(R.layout.ticket_list_item, itemLista, false);

            TextView estadoItem = itemView.findViewById(R.id.estadoItem);
            TextView tituloItem = itemView.findViewById(R.id.tituloItem);

            estadoItem.setText(ticket.getEstado().toString());
            tituloItem.setText(ticket.getTitulo());

            itemView.setOnClickListener(v -> {
                if(getActivity() instanceof MainActivity){
                    ((MainActivity) getActivity()).EditarTicket(ticket);
                }
            });

            itemLista.addView(itemView);
        }
    }
}