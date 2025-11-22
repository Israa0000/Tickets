package com.example.tickets.viewModel;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.tickets.R;
import com.example.tickets.model.EstadoTicket;
import com.example.tickets.model.Ticket;
import com.example.tickets.view.MainActivity;


public class EditarFragment extends Fragment {

    EditText tituloTicket;
    EditText descripcionTicket;
    EditText pasosTicket;
    Spinner mySpinner;
    Button btnCrearTicket;
    Button btnEditarTicket;

    private Ticket ticketActual;
    private boolean esModoEdicion = false;

    @Override
    public void onStart() {
        super.onStart();
        if (getActivity() instanceof MainActivity) {
            ((MainActivity) getActivity()).test();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_blank, container, false);

        mySpinner = (Spinner) rootView.findViewById(R.id.spinner);
        mySpinner.setAdapter(new ArrayAdapter<>(requireContext(), android.R.layout.simple_spinner_item, EstadoTicket.values()));

        tituloTicket = (EditText) rootView.findViewById(R.id.tituloTicket);
        descripcionTicket = (EditText) rootView.findViewById(R.id.descripcionTicket);
        pasosTicket = (EditText) rootView.findViewById(R.id.pasosTicket);

        btnCrearTicket = rootView.findViewById(R.id.crearTicket);
        btnEditarTicket = rootView.findViewById(R.id.btneditarTicket);

        btnCrearTicket.setOnClickListener(v -> crearTicket());
        btnEditarTicket.setOnClickListener(v -> {
            if (tituloTicket.isEnabled()) {
                guardarCambios();
            } else {
                habilitarEdicion(true);
                btnEditarTicket.setText("Guardar Cambios");
            }
        });

        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        actualizarVista();
    }

    public void setTicketActual(Ticket ticket) {
        this.ticketActual = ticket;
        this.esModoEdicion = (ticket != null);

        if (tituloTicket != null && tituloTicket.getText().toString().isEmpty() && descripcionTicket.getText().toString().isEmpty() && pasosTicket.getText().toString().isEmpty()) {
            actualizarVista();
        }
    }

    public void actualizarVista() {
        if (!esModoEdicion) {
            limpiarCampos();
            habilitarEdicion(true);
            btnCrearTicket.setVisibility(View.VISIBLE);
            btnEditarTicket.setVisibility(View.GONE);
        } else {
            rellenarCampos(ticketActual);
            habilitarEdicion(false);
            btnCrearTicket.setVisibility(View.GONE);
            btnEditarTicket.setVisibility(View.VISIBLE);
            btnEditarTicket.setText("Editar Ticket");
        }
    }

    public void habilitarEdicion(boolean habilitar) {
        tituloTicket.setEnabled(habilitar);
        descripcionTicket.setEnabled(habilitar);
        pasosTicket.setEnabled(habilitar);
        mySpinner.setEnabled(habilitar);
    }

    public void rellenarCampos(Ticket ticket) {
        tituloTicket.setText(ticket.getTitulo());
        descripcionTicket.setText(ticket.getDescripcion());
        pasosTicket.setText(ticket.getPasos());
        int spinnerPosition = ((ArrayAdapter) mySpinner.getAdapter()).getPosition(ticket.getEstado());
        mySpinner.setSelection(spinnerPosition);
    }

    public void limpiarCampos() {
        tituloTicket.setText("");
        descripcionTicket.setText("");
        pasosTicket.setText("");
        mySpinner.setSelection(0);
    }

    public void crearTicket() {
        String titulo = tituloTicket.getText().toString();
        String descripcion = descripcionTicket.getText().toString();
        String pasos = pasosTicket.getText().toString();
        EstadoTicket estado = (EstadoTicket) mySpinner.getSelectedItem();

        if (titulo.isEmpty() || descripcion.isEmpty() || pasos.isEmpty()) {
            Toast.makeText(getContext(), "Faltan datos", Toast.LENGTH_SHORT).show();
            return;
        }

        Ticket newTicket = new Ticket(estado, titulo, descripcion, pasos);
        if (getActivity() instanceof MainActivity) {
            MainActivity mainActivity = (MainActivity) getActivity();
            mainActivity.getTickets().add(newTicket);

            // GUARDAR EN ARCHIVO
            mainActivity.guardarCambios();

            Toast.makeText(getContext(), "Ticket Creado", Toast.LENGTH_SHORT).show();
            limpiarCampos();
        }

    }

    public void guardarCambios() {
        String titulo = tituloTicket.getText().toString();
        String descripcion = descripcionTicket.getText().toString();
        String pasos = pasosTicket.getText().toString();
        EstadoTicket estado = (EstadoTicket) mySpinner.getSelectedItem();

        if (!titulo.isEmpty() && !descripcion.isEmpty() && !pasos.isEmpty()) {

            ticketActual.setTitulo(titulo);
            ticketActual.setDescripcion(descripcion);
            ticketActual.setPasos(pasos);
            ticketActual.setEstado(estado);

            if (getActivity() instanceof MainActivity) {
                ((MainActivity) getActivity()).guardarCambios();
            }

            Toast.makeText(getContext(), "Cambios guardados", Toast.LENGTH_SHORT).show();
            habilitarEdicion(false);
            btnEditarTicket.setText("Editar Ticket");
        } else {
            Toast.makeText(getContext(), "Faltan datos", Toast.LENGTH_SHORT).show();
        }
    }
}