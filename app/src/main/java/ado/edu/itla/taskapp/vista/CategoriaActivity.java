package ado.edu.itla.taskapp.vista;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;




import ado.edu.itla.taskapp.R;
import ado.edu.itla.taskapp.entidad.Categoria;
import ado.edu.itla.taskapp.repositorio.CategoriaRepositorio;
import ado.edu.itla.taskapp.repositorio.db.CategoriaRepositorioDbImp;


public class CategoriaActivity extends AppCompatActivity {

	private static final String LOG_TAG = "CategoriaActivity";
	private CategoriaRepositorio categoriaRepositorio;
    private Categoria categoria;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_categoria);

		 categoriaRepositorio = new CategoriaRepositorioDbImp(this);

		 final EditText txtNombre = findViewById(R.id.txtNombreCategoria);

		 Button btnCategoria = findViewById(R.id.btnCategoria);

		 Bundle paraBundle = getIntent().getExtras();

		 if (paraBundle != null && paraBundle.containsKey("categoria"))
		 {
			 categoria = (Categoria)paraBundle.getSerializable("categoria");
			 txtNombre.setText(categoria.getNombre());
			 btnCategoria.setText("Actualizar");
		 }

		 btnCategoria.setOnClickListener( new View.OnClickListener() {


			 @Override
			 public void onClick(View v) {



			 	if (categoria == null)
				{

					categoria = new Categoria();

				}

				 categoria.setNombre(txtNombre.getText().toString());

				 Log.i(LOG_TAG, categoria.toString());



				  categoriaRepositorio.guardar(categoria);


				 // TODO: 1 - si existe actualizarla, 2 - Sino agregarla

			 }

		 });


	}
}
