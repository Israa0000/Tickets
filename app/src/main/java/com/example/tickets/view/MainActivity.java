package com.example.tickets.view;

import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.tickets.R;
import com.example.tickets.model.EditarFragment;

public class MainActivity extends AppCompatActivity {

    EditarFragment fragmentMain;
    Button btncrearTicket;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();

        fragmentMain = new EditarFragment();

        transaction.replace(R.id.framelayout, fragmentMain);

        transaction.commit();

        btncrearTicket.setOnClickListener(v -> {
            FragmentTransaction writeTransact = fragmentManager.beginTransaction();
            //writeTransact.replace(R.id.fragmentMain,)
        });
    }

    public void test(){

    }
}