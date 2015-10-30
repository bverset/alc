package com.android.agendacontactos.fragment;


import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Spinner;

import com.android.agendacontactos.R;
import com.android.agendacontactos.Database.SQL;
import com.android.agendacontactos.model.Contact;
import com.android.agendacontactos.preferences.CacheManager;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * Created by James on 28/10/15.
 */
public class FormFragment extends Fragment {

    private CacheManager cacheManager;
    String nombre,correo,celular,telefono,grupo;
    private final String emailPattern = "[a-zA-Z0-9.-_]+@[a-z]+\\.+[a-z]+";
    private SQL sql;

    @Bind(R.id.edt_nombre) EditText nombre_et;
    @Bind(R.id.edt_correo) EditText correo_et;
    @Bind(R.id.edt_celular) EditText celular_et;
    @Bind(R.id.edt_telefono) EditText telefono_et;
    @Bind(R.id.spi_grupo) Spinner grupo_sp;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        cacheManager = new CacheManager(getContext());
        sql = new SQL(getContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)

    {
        super.onCreateView(inflater, container, savedInstanceState);
        View rootView = inflater.inflate(R.layout.form_dummy, container, false);



        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Contact c = cacheManager.getUser();
        //tvDummy.setText(c.getName());
    }

    @OnClick(R.id.btn_guardar)
    public void guardar(View v){
        nombre = nombre_et.getText().toString();
        correo = correo_et.getText().toString();
        celular = celular_et.getText().toString();
        telefono = telefono_et.getText().toString();
        grupo = grupo_sp.getSelectedItem().toString();


        if(nombre.length() > 0 && correo.length() > 0 && (celular.length() > 0 || telefono.length() > 0)){

            if(nombre.length() < 4){
                showErrorF("El nombre debe tener 4 caracteres", v);
            }else if(!correo.matches(emailPattern)){
                showErrorF("El email es invalido", v);
            }else if(celular.length() > 0 && celular.length() != 10){
                showErrorF("No es un celular valido", v);
            }else if(telefono.length() >0 && telefono.length() != 7) {
                showErrorF("No es un telefono valido", v);
            }

            else{
                Contact contact = new Contact(0,nombre,correo,celular,telefono,"",grupo);
                long id = sql.insertContact(contact);
                Log.d("SQLITE","-->"+id);
                Log.d("SQLITE",sql.getContact(id).getName());
                showErrorF("OK", v);
            }
        }else{
            showErrorF("Error, Todos los campos son requeridos", v);
        }
    }

    private void showErrorF(String err, View v){
        Snackbar snackbar = Snackbar.make(v,err, Snackbar.LENGTH_LONG);
        snackbar.show();
    }

}
