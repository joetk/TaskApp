package ado.edu.itla.taskapp.vista;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import ado.edu.itla.taskapp.R;
import ado.edu.itla.taskapp.repositorio.db.TareaRepositorioDbImp;
import ado.edu.itla.taskapp.repositorio.LoginInfo;

public class UsuarioNormalActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario_normal);


        Button crearTareas = findViewById(R.id.buttonTareas);
        ListView listView =  findViewById(R.id.listasTareas);
        TareaRepositorioDbImp TareaDbImp = new TareaRepositorioDbImp(this);


        TareasListAdapter tareaAdapter = new TareasListAdapter(this, TareaDbImp.buscarCreadaPor(LoginInfo.getInstance().usuario) );


        listView.setAdapter(tareaAdapter);


        crearTareas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent (UsuarioNormalActivity.this, CrearTareasActivity.class);
                startActivity(intent);

            }
        });


    }
}
