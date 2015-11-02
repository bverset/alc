package com.android.agendacontactos.model;

import java.util.List;

/**
 * Created by James on 27/10/15.
 */
public class Contact {

    private long id;
    private String name;
    private String email;
    private String cel;
    private String phone;
    private String picture;
    private String group;

    public Contact(long id, String name, String email, String cel, String phone, String picture, String group) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.cel = cel;
        this.phone = phone;
        this.picture = picture;
        this.group = group;
    }

    public Contact(String name, String email, String cel, String phone) {
        this.name = name;
        this.email = email;
        this.cel = cel;
        this.phone = phone;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCel() {
        return cel;
    }

    public void setCel(String cel) {
        this.cel = cel;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }
}
