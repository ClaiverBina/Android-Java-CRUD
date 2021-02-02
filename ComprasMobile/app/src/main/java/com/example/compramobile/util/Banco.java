package com.example.compramobile.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class Banco extends SQLiteOpenHelper {

    public Banco (Context context) {
        super(context, "banco.db", null, 2);

    }

    @Override
    public void onOpen(SQLiteDatabase db){

        super.onOpen(db);
        db.execSQL("PRAGMA foreign_keys=ON");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        Log.i("SQLITE", "CREATED!");

        db.execSQL("create table if not exists usuarios (_id INTEGER PRIMARY KEY AUTOINCREMENT,login VARCHAR(255) not null unique ,senha VARCHAR(255), cpf VARCHAR(255), nome VARCHAR(255));");

        db.execSQL("create table if not exists produto (_id INTEGER PRIMARY KEY AUTOINCREMENT,nome VARCHAR(255) not null unique ,marca VARCHAR(255),preco DOUBLE);");

        db.execSQL("create table if not exists compra ( _id INTEGER PRIMARY KEY AUTOINCREMENT, id_usuario INTEGER, id_produto INTEGER, data varchar(255), quantidade INT, foreign key (id_usuario) references usuarios (_id), foreign key (id_produto) references produto (_id))");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        Log.i("SQLITE", "UPGRADED!");
        db.execSQL("DROP TABLE IF EXISTS `produto`");
        db.execSQL("DROP TABLE IF EXISTS `compra`");
        db.execSQL("DROP TABLE IF EXISTS `usuarios`");
        onCreate(db);

    }
}
