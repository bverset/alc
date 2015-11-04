package com.android.agendacontactos.fragment;


import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.agendacontactos.MainActivity;
import com.android.agendacontactos.R;
import com.android.agendacontactos.Database.SQL;
import com.android.agendacontactos.adapter.ContactListAdapter;
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
    private Contact contactSel;

    @Bind(R.id.edt_nombre) EditText nombre_et;
    @Bind(R.id.edt_correo) EditText correo_et;
    @Bind(R.id.edt_celular) EditText celular_et;
    @Bind(R.id.edt_telefono) EditText telefono_et;
    @Bind(R.id.spi_grupo) Spinner grupo_sp;
    @Bind(R.id.btn_guardar) Button button_btn;
    @Bind(R.id.btn_supprimir) Button supprimir_btn;
    @Bind(R.id.btn_new) Button new_btn;

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

        //if (ContactListAdapter.contactStatic.getName().length()>0){
        //nombre_et.setText("VB");
   // }

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
        nombre = nombre_et.getText().toString().trim();
        correo = correo_et.getText().toString().trim();
        celular = celular_et.getText().toString().trim();
        telefono = telefono_et.getText().toString().trim();
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
                long rowId = sql.checkContact(nombre);
                if (rowId==-1){  // pas trouve insert
                    Contact contact = new Contact(0,nombre,correo,celular,telefono,"",grupo);
                    long id = sql.insertContact(contact);
                    ((MainActivity)getActivity()).newData(id,false);
                    Log.d("SQLITE","-->"+id);
                    Log.d("SQLITE",sql.getContact(id).getName());
                    showErrorF("OK Insert", v);
                }
                else {
                    Contact contact = new Contact(rowId,nombre,correo,celular,telefono,"",grupo);
                    sql.updateContact(contact);
                    ((MainActivity)getActivity()).newData(rowId, true);
                    showErrorF("OK Update", v);
                }
                clearForm();
                ((MainActivity)getActivity()).setTab(0);
            }
        }else{
            showErrorF("Error, Todos los campos son requeridos", v);
        }
    }
    @OnClick(R.id.btn_supprimir)
    public void supprimir(View v){
        sql.deleteContact(contactSel.getId());
        ((MainActivity)getActivity()).newData(contactSel.getId(), true);
        showErrorF("OK Delete", v);
        clearForm();
        ((MainActivity)getActivity()).setTab(0);
    }

    @OnClick(R.id.btn_new)
    public void newForm(View v){
        clearForm();
    }

    private void showErrorF(String err, View v){
        Snackbar snackbar = Snackbar.make(v,err, Snackbar.LENGTH_LONG);
        snackbar.show();
    }

    public void setForm(Contact contact){
       // if (ContactListAdapter.contactStatic != null){
        //    contact = new Contact(ContactListAdapter.contactStatic.getId(),ContactListAdapter.contactStatic.getName(),ContactListAdapter.contactStatic.getEmail(),ContactListAdapter.contactStatic.getCel(),ContactListAdapter.contactStatic.getPhone(),"",ContactListAdapter.contactStatic.getGroup());
            nombre_et.setText(contact.getName());
            nombre_et.setEnabled(false); //comme c'est la clef on ne permet pa de modifier le nom
            correo_et.setText(contact.getEmail());
            celular_et.setText(contact.getCel());
            telefono_et.setText(contact.getPhone());
            grupo_sp.setSelection(getIndex(grupo_sp, contact.getGroup()));
            button_btn.setText("ACTUALIZAR");
            //contactSel = ContactListAdapter.contactStatic;
            //ContactListAdapter.contactStatic = null;
            supprimir_btn.setVisibility(View.VISIBLE);
            new_btn.setVisibility(View.VISIBLE);
       // }
       // else {
       //     clearForm();
       // }
    }
    private int getIndex(Spinner spinner, String myString){
        int index = 0;
        for (int i=0;i<spinner.getCount();i++){
            if (spinner.getItemAtPosition(i).equals(myString)){
                index = i;
            }
        }
        return index;
    }

    private void clearForm(){
        nombre_et.setText("");
        nombre_et.setEnabled(true);
        correo_et.setText("");
        celular_et.setText("");
        telefono_et.setText("");
        grupo_sp.setSelection(0);
        button_btn.setText("GUARDAR");
        supprimir_btn.setVisibility(View.INVISIBLE);
        new_btn.setVisibility(View.INVISIBLE);
    }

    public void clickRecycler() {
        Log.d("SQLITE", "click");
    }
}
