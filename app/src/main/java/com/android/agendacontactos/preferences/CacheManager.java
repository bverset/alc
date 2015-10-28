package com.android.agendacontactos.preferences;

import android.content.Context;
import android.content.SharedPreferences;

import com.android.agendacontactos.model.Contact;

/**
 * Created by James on 27/10/15.
 */
public class CacheManager {

    private final static int PRIVATE_MODE = 0;
    private final static String PREF_NAME = "app.com.name.preferences";

    private SharedPreferences pref;
    private SharedPreferences.Editor mEditor;

    public CacheManager(Context context) {
        this.pref = context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        this.mEditor = pref.edit();
    }

    public void setUser(Contact contact){
        mEditor.putString(KeysShared.KEY_NAME, contact.getName());
        mEditor.putString(KeysShared.KEY_EMAIL, contact.getEmail());
        mEditor.putString(KeysShared.KEY_CEL, contact.getCel());
        mEditor.putString(KeysShared.KEY_PHONE, contact.getPhone());
        mEditor.putBoolean(KeysShared.KEY_FIRST, true);
        mEditor.commit();
    }

    public Contact getUser(){

    }

    public boolean isLoggin(){
        return pref.getBoolean(KeysShared.KEY_FIRST, false);
    }


    class KeysShared{

        final static String KEY_NAME = "_name";
        final static String KEY_EMAIL = "_email";
        final static String KEY_CEL = "_cel";
        final static String KEY_PHONE = "_phone";
        final static String KEY_FIRST = "_first";

    }

}
