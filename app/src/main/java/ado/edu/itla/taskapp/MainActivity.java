package ado.edu.itla.taskapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import ado.edu.itla.taskapp.entidad.Categoria;
import ado.edu.itla.taskapp.vista.CategoriaActivity;
import ado.edu.itla.taskapp.vista.CategoriaListaActivity;

public class MainActivity extends AppCompatActivity {

    private static final String LOG_TAG = ("MainActivity");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        Button btnCategoria = (Button)findViewById(R.id.btnCategoria);
        Button btnlistar =  (Button)findViewById(R.id.btnListar);

        btnCategoria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this,CategoriaActivity.class);
                startActivity(intent);

            }
        });


        btnlistar.setOnClickListener( new View.OnClickListener(){


            @Override
            public void onClick(View v) {
                Intent intent = new Intent (MainActivity.this,CategoriaListaActivity.class );
                startActivity(intent);
            }
        });


    }
}
