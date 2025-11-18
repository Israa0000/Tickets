package com.example.tickets.model;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tickets.R;
import com.example.tickets.view.MainActivity;

import java.util.ArrayList;


public class EditarFragment extends Fragment {

    @Override
    public void onStart() {
        super.onStart();
        if (getActivity() instanceof MainActivity) {
            ((MainActivity) getActivity()).test();
        }

    }

    EditText tituloTicket;
    EditText descripcionTicket;
    EditText pasosTicket;
    Spinner mySpinner;
    Button myButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_blank,container, false);

        mySpinner = (Spinner) rootView.findViewById(R.id.spinner);
        mySpinner.setAdapter(new ArrayAdapter<>(requireContext(),
                android.R.layout.simple_spinner_item,
                EstadoTicket.values()));

        tituloTicket = (EditText) rootView.findViewById(R.id.tituloTicket);
        descripcionTicket = (EditText) rootView.findViewById(R.id.descripcionTicket);
        pasosTicket = (EditText) rootView.findViewById(R.id.pasosTicket);

        myButton = (Button) ((MainActivity) getActivity()).findViewById(R.id.crearTicket);

        String titulo = tituloTicket.toString();
        String descripcion = descripcionTicket.toString();
        String pasos= pasosTicket.toString();

        myButton.setOnClickListener(v -> {
            Ticket newTicket = new Ticket(EstadoTicket.NUEVO,titulo, descripcion,pasos);

        });



    return rootView;
    }

}