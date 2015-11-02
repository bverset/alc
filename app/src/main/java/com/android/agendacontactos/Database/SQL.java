package com.android.agendacontactos.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import com.android.agendacontactos.model.Contact;

import java.util.ArrayList;

/**
 * Created by James on 29/10/15.
 */
public class SQL extends SQLiteOpenHelper {

    private final static String DATABASE_NAME = "nombrebd";
    private final static int DATABASE_VERSION = 1;

    public SQL(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_CONTACT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    private String TABLE_CONTACT = "contact"; // nombre tabla
    private String CONTACT_ID = "_id";
    private String CONTACT_NAME = "_name";
    private String CONTACT_EMAIL = "_email";
    private String CONTACT_CEL = "_cel";
    private String CONTACT_PHONE = "_phone";
    private String CONTACT_PICTURE = "_picture";
    private String CONTACT_GROUP = "_group";

    private String CREATE_TABLE_CONTACT
            = "CREATE TABLE " + TABLE_CONTACT + " ("
            + CONTACT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + CONTACT_NAME + " VARCHAR(30), "
            + CONTACT_EMAIL + " VARCHAR(30), "
            + CONTACT_CEL + " VARCHAR(30), "
            + CONTACT_PHONE + " VARCHAR(30), "
            + CONTACT_PICTURE + " VARCHAR(30), "
            + CONTACT_GROUP + " VARCHAR(30));";

    private String DROP_TABLE_CONTACT
            = "DROP TABLE IF EXISTS '" + TABLE_CONTACT + "'";

    private final String[] COLUMNS =
            {CONTACT_ID, CONTACT_NAME, CONTACT_EMAIL,
                    CONTACT_CEL, CONTACT_PHONE, CONTACT_PICTURE, CONTACT_GROUP};

    private ContentValues createContent(Contact contact){
        ContentValues values = new ContentValues();
        values.put(CONTACT_NAME, contact.getName());
        values.put(CONTACT_EMAIL, contact.getEmail());
        values.put(CONTACT_CEL, contact.getCel());
        values.put(CONTACT_PHONE, contact.getPhone());
        values.put(CONTACT_PICTURE, contact.getPicture());
        values.put(CONTACT_GROUP, contact.getGroup());

        return values;
    }

    public long insertContact(Contact contact){
        long rowId = -1;
        SQLiteDatabase db = getWritableDatabase();
        try {
            rowId = db.insert(TABLE_CONTACT, null, createContent(contact));
        }catch (SQLiteException e){}

        db.close();

        return rowId;

    }

    public boolean updateContact(Contact contact){
        long rowId = -1;
        SQLiteDatabase db = getWritableDatabase();
        try {
            rowId = db.update(
                    TABLE_CONTACT,
                    createContent(contact),
                    CONTACT_ID + "=" + contact.getId(),
                    null
            );

        }catch (SQLiteException e){}

        db.close();

        return rowId > 0;
    }

    public boolean deleteContact(long contactId){
        long rowId = -1;
        SQLiteDatabase db = getWritableDatabase();
        try {
            rowId = db.delete(
                    TABLE_CONTACT,
                    CONTACT_ID + "=" + contactId,
                    null
            );

        }catch (SQLiteException e){}

        db.close();

        return rowId > 0;
    }

    public Contact getContact(long contactId){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = null;
        Contact contact = null;

        try {

            cursor = db.query(TABLE_CONTACT,
                    COLUMNS,
                    CONTACT_ID + "=?",
                    new String[]{String.valueOf(contactId)},
                    null,
                    null,
                    null,
                    null
            );

            if(cursor != null && cursor.getCount() > 0){
                cursor.moveToFirst();

                contact = new Contact(cursor.getLong(0),
                        cursor.getString(1), cursor.getString(2), cursor.getString(3),
                        cursor.getString(4), cursor.getString(5), cursor.getString(6));
                cursor.close();
            }

        }catch (SQLiteException e){}

        db.close();

        return contact;
    }

    //obligatorio
    public ArrayList<Contact> getAll(){
        ArrayList<Contact> al = new ArrayList<>();
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = null;
        Contact contact = null;

        try {

            cursor = db.rawQuery("SELECT  * FROM " + TABLE_CONTACT, null);

            if(cursor != null && cursor.getCount() > 0){
                cursor.moveToFirst();
                do {
                contact = new Contact(cursor.getLong(0),
                        cursor.getString(1), cursor.getString(2), cursor.getString(3),
                        cursor.getString(4), cursor.getString(5), cursor.getString(6));
                    al.add(contact);
                } while (cursor.moveToNext());
                cursor.close();
            }

        }catch (SQLiteException e){}

        db.close();

        return al;
    }

    //opcional
    public long checkContact(String nombre){
        //true si es existe
        long rowId = -1;
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = null;

        try {

            cursor = db.query(TABLE_CONTACT,
                    COLUMNS,
                    CONTACT_NAME + "=?",
                    new String[]{nombre},
                    null,
                    null,
                    null,
                    null
            );

            //cursor = db.rawQuery("SELECT * FROM " + TABLE_CONTACT + " where "+ CONTACT_NAME + " = " + nombre, null);

            if(cursor != null && cursor.getCount() > 0){
                cursor.moveToFirst();
                rowId = cursor.getLong(0);
                cursor.close();

            }

        }catch (SQLiteException e){}

        db.close();

        return rowId;

    }

}