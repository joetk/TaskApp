package ado.edu.itla.taskapp.vista;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.net.URLConnection;
import java.util.List;

import ado.edu.itla.taskapp.MainActivity;
import ado.edu.itla.taskapp.R;
import ado.edu.itla.taskapp.entidad.Categoria;
import ado.edu.itla.taskapp.entidad.Usuario;
import ado.edu.itla.taskapp.repositorio.LoginInfo;
import ado.edu.itla.taskapp.repositorio.db.CategoriaRepositorioDbImp;
import ado.edu.itla.taskapp.repositorio.db.ConexionDb;
import ado.edu.itla.taskapp.repositorio.db.EstructuraDb;
import ado.edu.itla.taskapp.repositorio.db.UsuarioRepositorioDbImp;


public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

         TextView textViewRegistrate = findViewById(R.id.textViewRegistrarse);
         Button buttonLogin = findViewById(R.id.buttonLogin);






         textViewRegistrate.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {

                 Intent intent = new Intent(LoginActivity.this, RegistroUsuarioActivity.class);

                 startActivity(intent);
             }
         });
         
         buttonLogin.setOnClickListener( new View.OnClickListener() {


             @Override
             public void onClick(View v) {

                 UsuarioRepositorioDbImp UsuarioRepo = new UsuarioRepositorioDbImp(LoginActivity.this);
                 EditText txtUsuario = findViewById(R.id.editTextUsuario);
                 EditText txtContrasena = findViewById(R.id.editTextContrasena);
                 
                 Usuario usuario = UsuarioRepo.buscar(txtUsuario.getText().toString());
                 
                 if ( usuario != null && usuario.getContrasena().equals(txtContrasena.getText().toString()) )
                 {
                     Intent intent =  usuario.getTipoUsuario() == Usuario.TipoUsuario.NORMAL ?
                                       new Intent (LoginActivity.this, UsuarioNormalActivity.class) :
                                       new Intent (LoginActivity.this, UsuarioTecnicoActivity.class);

                     LoginInfo.getInstance().usuario = usuario;

                     startActivity(intent);
                     
                 }
                 
                 else
                 {
                     Toast.makeText(LoginActivity.this, "el usuario o la contrasena es invalido", Toast.LENGTH_SHORT).show();
                     
                 }
                 
             }
         });

    }


}
