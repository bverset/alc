package com.android.agendacontactos.adapter;


import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.agendacontactos.Database.SQL;
import com.android.agendacontactos.MainActivity;
import com.android.agendacontactos.Observer;
import com.android.agendacontactos.R;
import com.android.agendacontactos.fragment.DummyFragment;
import com.android.agendacontactos.fragment.FormFragment;
import com.android.agendacontactos.model.Contact;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by James on 29/10/15.
 */
public class ContactListAdapter extends RecyclerView.Adapter<ContactListAdapter.ContactViewHolder>{

    private ArrayList<Contact> data;
    Observer observer;   //observer

    public static Contact contactStatic = null;

    public ContactListAdapter(ArrayList<Contact> data) {
        this.data = data;
    }

    public void setData(ArrayList<Contact> data) {
        this.data = data;
    }

    public void setObserver(Observer observer) {
        this.observer = observer;
    }  //observer appelé depuis dummyFragment qui est le père

    @Override
    public ContactViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false);
        return new ContactViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ContactViewHolder holder, int position) {
        Contact contact = data.get(position);
        holder.bindHolder(contact);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ContactViewHolder extends RecyclerView.ViewHolder{

        @Bind(R.id.tv_name) TextView tvName;
        @Bind(R.id.tv_email) TextView tvMail;
        @Bind(R.id.tv_celular) TextView tvCelular;
        @Bind(R.id.tv_telefono) TextView tvTelefono;
        @Bind(R.id.tv_grupo) TextView tvGrupo;

        Contact contact;


        public ContactViewHolder(final View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);


           itemView.setOnClickListener(new View.OnClickListener() {
               @Override
                public void onClick(View v) {
                     //int pos = getAdapterPosition();
                    //contactStatic = data.get(pos);
                    observer.onClickContact(contact);  // assigné dans bindHolder
                    //Toast.makeText(v.getContext(), "Click element "+ contact.getName(), Toast.LENGTH_SHORT).show();
               }
            });
        }

        public void bindHolder(Contact contact){  // vient de onBindViewHolder
            this.contact = contact;
            tvName.setText(contact.getName());
            tvMail.setText(contact.getEmail());
            tvCelular.setText(contact.getCel());
            tvTelefono.setText(contact.getPhone());
            tvGrupo.setText(contact.getGroup());

        }
    }
}
