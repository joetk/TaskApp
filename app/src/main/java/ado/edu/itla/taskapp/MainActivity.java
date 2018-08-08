package ado.edu.itla.taskapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import ado.edu.itla.taskapp.entidad.Categoria;
import ado.edu.itla.taskapp.repositorio.LoginInfo;
import ado.edu.itla.taskapp.vista.CategoriaActivity;
import ado.edu.itla.taskapp.vista.CategoriaListaActivity;

public class MainActivity extends AppCompatActivity {

    private static final String LOG_TAG = ("MainActivity");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        URL gitApi = null;

        try {
             gitApi = new URL("https://api.github.com/gists/public");
        }

        catch (MalformedURLException e)
        {
            e.printStackTrace();
        }

        try {
            HttpsURLConnection connection = (HttpsURLConnection) gitApi.openConnection();


            if (connection.getResponseCode() == 200)
            {
                InputStreamReader reader = new InputStreamReader (connection.getInputStream());
                BufferedReader result = new BufferedReader(reader);
                String line;
                StringBuffer stringBuffer = new StringBuffer();

                while ( ( line = result.readLine()) != null)
                {
                     stringBuffer.append (line);
                }

                reader.close();
                result.close();

                Log.i("githupApi", stringBuffer.toString());
            }

        }

        catch (IOException e)
        {

        }

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
