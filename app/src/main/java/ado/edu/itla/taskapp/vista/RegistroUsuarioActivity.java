package ado.edu.itla.taskapp.vista;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import ado.edu.itla.taskapp.R;
import ado.edu.itla.taskapp.entidad.Usuario;
import ado.edu.itla.taskapp.repositorio.db.ConexionDb;
import ado.edu.itla.taskapp.repositorio.db.UsuarioRepositorioDbImp;

public class RegistroUsuarioActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_usuario);

        TextView buttonRegistro =  findViewById(R.id.buttonRegistrar);



        buttonRegistro.setOnClickListener(  new View.OnClickListener() {


            @Override
            public void onClick(View v) {


                EditText txtEmail  = findViewById(R.id.editTextEmail);
                EditText txtNombre = findViewById(R.id.editTextNombre);
                EditText txtContrasena = findViewById(R.id.editTextContrasena);
                EditText txtConfirmar =findViewById(R.id.editTextConfimar);
                RadioGroup rbGroup =  findViewById(R.id.radioGroup);
                RadioButton rbTipo =  findViewById(rbGroup.getCheckedRadioButtonId());



                Usuario usuario = new Usuario();

                usuario.setEmail(txtEmail.getText().toString());
                usuario.setNombre(txtNombre.getText().toString());
                usuario.setContrasena(txtContrasena.getText().toString());
                usuario.setTipoUsuario(Usuario.TipoUsuario.valueOf(rbTipo.getText().toString()));

                UsuarioRepositorioDbImp usuarioRepo = new UsuarioRepositorioDbImp(RegistroUsuarioActivity.this);




                if (validarCampos(usuario,txtConfirmar.getText().toString()))
                   {

                       if (usuarioRepo.buscar(usuario.getEmail()) == null)
                       {
                           usuarioRepo.guardar(usuario);
                           Toast.makeText(RegistroUsuarioActivity.this, "se ah registrado el usuario", Toast.LENGTH_SHORT).show();

                       }

                       else
                       {
                           Toast.makeText(RegistroUsuarioActivity.this, "este usuario ya existe", Toast.LENGTH_SHORT).show();
                       }



                   }


                else
                   {
                     Toast.makeText(RegistroUsuarioActivity.this, "hay uno  o mas campos vacio", Toast.LENGTH_SHORT).show();

                   }



            }
        } );
    }


    private boolean validarCampos (Usuario usuario, String txtConfirmar)
    {
        final  String EMPTY_STRING =  "";


      return  !(usuario.getEmail().equals(EMPTY_STRING)
               || usuario.getNombre().equals (EMPTY_STRING)
              || usuario.getContrasena().equals (EMPTY_STRING)   );


    }

}

