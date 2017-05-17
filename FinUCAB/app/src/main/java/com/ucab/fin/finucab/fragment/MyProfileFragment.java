package com.ucab.fin.finucab.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import com.ucab.fin.finucab.R;
import com.ucab.fin.finucab.activity.MainActivity;



public class MyProfileFragment extends Fragment {


    private View parentView;
    private MainActivity parentActivity;

    EditText etNombre;
    EditText etApellido;
    EditText etCorreo;
    EditText etUsuario;
    EditText etClave;
    EditText etPregunta;
    EditText etRespuesta;
    Button boton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //sqlThread.start();
        View view = inflater.inflate(R.layout.profile_fragment, container, false);

        boton = (Button) view.findViewById(R.id.bBack);


        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentManager man = getFragmentManager();
                FragmentTransaction trans = man.beginTransaction();
                HomeFragment profi = new HomeFragment();
                trans.add(R.id.drawer_layout,profi);
                trans.commit();


            }
        });
        //myEditText.setText( myEditText.getText() + " " + myButton.getText() );
        //etNombre.setText( etNombre.getText() + " " + boton.getText());



        return view;
    }



   /* Thread sqlThread = new Thread() {
        public void run() {
            try {
                Class.forName("org.postgresql.Driver");
                // "jdbc:postgresql://IP:PUERTO/DB", "USER", "PASSWORD");
                // Si estás utilizando el emulador de android y tenes el PostgreSQL en tu misma PC no utilizar 127.0.0.1 o localhost como IP, utilizar 10.0.2.2
                Connection conn = DriverManager.getConnection("jdbc:postgresql://10.0.2.2:5432/ejemplodb", "prueba", "1234");
                //En el stsql se puede agregar cualquier consulta SQL deseada.
                String stsql = "Select nombre from pruebat1;";
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery(stsql);
                rs.next();
                System.out.println( rs.getString(1) );
                etNombre.setText( rs.getString(1) );
                conn.close();
            } catch (SQLException se) {
                System.out.println("oops! No se puede conectar. Error: " + se.toString());
            } catch (ClassNotFoundException e) {
                System.out.println("oops! No se encuentra la clase. Error: " + e.getMessage());
            }
        }
    };*/
}


