package com.android.agendacontactos.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.android.agendacontactos.Database.SQL;
import com.android.agendacontactos.MainActivity;
import com.android.agendacontactos.R;
import com.android.agendacontactos.adapter.ContactListAdapter;
import com.android.agendacontactos.model.Contact;
import com.android.agendacontactos.Observer;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by James on 28/10/15.
 */
public class DummyFragment extends Fragment implements Observer {  //implémenter la méthode de la classe java observer qu"on a faite avec interface

    @Bind(R.id.list) RecyclerView list;
    private ContactListAdapter adapter;
    private ArrayList<Contact> data;
    private SQL sql;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sql = new SQL(getContext());
        data = new ArrayList<>();
        //leer todos los contactos la primerea vez que inica
        data = sql.getAll();
    }


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View rootView = inflater.inflate(R.layout.fragment_dummy, container, false);
        ButterKnife.bind(this, rootView);

        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        adapter = new ContactListAdapter(data);
        adapter.setObserver(this);     //observer on dit a la adapter contact que l'observateur est ici
        list.setLayoutManager(linearLayoutManager);
        list.setAdapter(adapter);


    }

    public void refresh(long id ,boolean all ){

            if (all){
                data = sql.getAll();
               }
            else{
                Contact contact = sql.getContact(id);
                if(contact != null){
                data.add(contact);
                }
        }

        adapter.setData(data);
        adapter.notifyDataSetChanged();

    }

    @Override
    public void onClickContact(Contact c) {
       ((MainActivity)getActivity()).onClickContactAdapter(c); //observer uniqt avec fragment main est le père
    }
}


