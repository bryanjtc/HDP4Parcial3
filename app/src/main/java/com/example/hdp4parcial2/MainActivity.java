package com.example.hdp4parcial2;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    Spinner spp;
    EditText cantidad_boletos;
    TextView total_Adultos, total_ninos, total;
    CheckBox check;
    float cantidad, cantidad_total, cantidad_ninos, cantidad_adultos, cantidad_jubilado;
    Button button;
    boolean isNino, isAdulto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cantidad_boletos = findViewById(R.id.cantidad_boletos);
        total_Adultos = findViewById(R.id.total_nino);
        total_ninos = findViewById(R.id.total_adultos);
        total = findViewById(R.id.total);
        spp = findViewById(R.id.spinner);
        check = findViewById(R.id.checkBox);
        button = findViewById(R.id.button2);
        String[] options = {"Seleccione una opcion","Niños", "Adultos"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, options);
        spp.setAdapter(adapter);
        spp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String opcion = spp.getSelectedItem().toString();
                if (opcion.equals("Niños")) {
                    isAdulto = false;
                    isNino = true;
                    check.setVisibility(View.INVISIBLE);
                    if(cantidad_boletos.getText().toString().equals("")){
                        cantidad = 0;
                    }
                    else {
                        cantidad = Integer.parseInt(cantidad_boletos.getText().toString());
                    }
                }
                if (opcion.equals("Adultos")) {
                    isAdulto = true;
                    isNino = false;
                    check.setVisibility(View.VISIBLE);
                    if(cantidad_boletos.getText().toString().equals("")){
                        cantidad = 0;
                    }
                    else {
                        cantidad = Integer.parseInt(cantidad_boletos.getText().toString());
                    }
                }
            }

            public void onNothingSelected(AdapterView<?> parent) {

            }

        });
        button.setOnClickListener(view -> {
            if(isNino){
                cantidad_ninos = cantidad_ninos+(5*cantidad);
                cantidad_total = cantidad_total+(5*cantidad);
            }
            if(!check.isChecked() & isAdulto){
                cantidad_adultos = cantidad_adultos + (10*cantidad);
                cantidad_total = cantidad_total+ (10*cantidad);
            }
            if (check.isChecked() & isAdulto) {
                cantidad_adultos = (float) (cantidad_adultos+((10-(10*0.20))*cantidad));
                cantidad_total = (float) (cantidad_total+((10-(10*0.20))*cantidad));
            }
            total.setText("Total a pagar: "+cantidad_total);
        });
    }
}