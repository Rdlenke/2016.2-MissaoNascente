package com.example.jbbmobile.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;


import com.example.jbbmobile.model.Books;
import com.example.jbbmobile.model.Explorers;

import java.util.ArrayList;
import java.util.List;

public class BookDAO extends SQLiteOpenHelper {
    private static final String NAME_DB="JBB";
    private static final int VERSION=1;

    public BookDAO(Context context) {
        super(context,NAME_DB, null, VERSION);
    }


    public void createTable(SQLiteDatabase sqLiteDatabase){

        sqLiteDatabase.execSQL("CREATE TABLE  IF NOT EXISTS BOOK (idBook integer  primary key autoincrement, nameBook varchar(45), email text not null, " +
                    "FOREIGN KEY (email) REFERENCES EXPLORER(email) )");
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE BOOK");
        onCreate(sqLiteDatabase);
    }

    @NonNull
    private ContentValues getBook(Books books) {
        ContentValues data = new ContentValues();
        data.put("nameBook", books.getNameBook());
        data.put("email", books.getExplorer().getEmail());
        return data;
    }

    public int insertBook(Books books) {

        SQLiteDatabase db = getWritableDatabase();
        int insertReturn = 0;
        ContentValues data = getBook(books);
        try{
            insertReturn = (int) db.insert("BOOK", null, data);
        }catch (SQLiteConstraintException e){

        }

        return  insertReturn;
    }

    public Books findBook(Books books){
        SQLiteDatabase db = getWritableDatabase();
        Cursor c;

        c= db.query("BOOK",new String[] { "idBook" ,"nameBook","email"}, "idBook = " + books.getIdBook() ,null, null , null ,null);
        Books books1 = new Books();

        if(c.moveToFirst()){
            books1.setIdBook(c.getShort(c.getColumnIndex("idBook")));
            books1.setNameBook(c.getString(c.getColumnIndex("nameBook")));
            books1.setExplorer(new Explorers(c.getString(c.getColumnIndex("email"))));
        }
        c.close();
        return books1;
    }

    public List<Books> findBookExplorer(String email){
        SQLiteDatabase db = getWritableDatabase();
        Cursor c;

        c= db.query("BOOK",new String[] { "idBook" ,"nameBook","email"}, "email = '" + email +"'" ,null, null , null ,null);
        List<Books> book = new ArrayList<Books>();

        while(c.moveToNext()){
            Books books1 = new Books();
            books1.setIdBook(c.getShort(c.getColumnIndex("idBook")));
            books1.setNameBook(c.getString(c.getColumnIndex("nameBook")));
            books1.setExplorer(new Explorers(c.getString(c.getColumnIndex("email"))));
            book.add(books1);
        }
        c.close();
        return book;
    }

}