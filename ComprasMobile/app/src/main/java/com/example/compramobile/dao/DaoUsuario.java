package com.example.compramobile.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.compramobile.bean.Usuario;
import com.example.compramobile.util.Banco;

public class DaoUsuario {

    private SQLiteDatabase db;
    private Banco banco;
    private Usuario usu;

    public DaoUsuario(Context context, Usuario bean){
        super();
        this.banco = new Banco(context);
        this.db = banco.getWritableDatabase();
        this.usu = bean;

    }

    public Cursor login() {
        Cursor cursor;
        String[] campos = {"_id", "login", "senha", "cpf", "nome",};
        String where = "login = '" + this.usu.getLogin() + "' and senha = '" + this.usu.getSenha()+"'";
        db = banco.getReadableDatabase();

        cursor = db.query("usuarios", campos, where, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }

    public String inserir() {
        ContentValues valores;
        long resultado;

        db = banco.getWritableDatabase();
        valores = new ContentValues();
        valores.put("login", this.usu.getLogin());
        valores.put("senha", this.usu.getSenha());
        valores.put("cpf", this.usu.getCpf());
        valores.put("nome", this.usu.getNome());



        resultado = db.insert("usuarios", null, valores);
        db.close();

        if (resultado == -1) {return "Erro ao inserir registro";}
        else {return "Registro Inserido com sucesso";}
    }

    public String alterar() {
        ContentValues valores;
        String where;

        db = banco.getWritableDatabase();

        where = "_id = " + this.usu.getId();

        valores = new ContentValues();
        valores.put("login", this.usu.getLogin());
        valores.put("senha", this.usu.getSenha());
        valores.put("cpf", this.usu.getCpf());
        valores.put("nome", this.usu.getNome());


        long resultado = db.update("usuarios", valores, where,null);
        db.close();

        if (resultado == -1) return "Erro ao alterar registro";
        else return "Registro alterado com sucesso";
    }

    public String remover() {
        String where = "_id = " + this.usu.getId();

        db = banco.getReadableDatabase();
        long resultado = db.delete("usuarios",where,null);
        db.close();

        if (resultado == -1) return "Erro ao remover registro";
        else return "Registro removido com sucesso";
    }

    public Cursor listar() {
        Cursor cursor;
        String[] campos = {"_id", "nome", "cpf","login"};
        db = banco.getReadableDatabase();
        cursor = db.query("usuarios", campos, null, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }

        db.close();
        return cursor;
    }

    public Cursor buscar() {
        Cursor cursor;
        String[] campos = {"_id", "login", "senha", "cpf", "nome"};
        String where = "_id = " + this.usu.getId();
        db = banco.getReadableDatabase();
        cursor = db.query("usuarios", campos, where, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }
}
