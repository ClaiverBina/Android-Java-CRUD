package com.example.compramobile.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.compramobile.bean.Compra;
import com.example.compramobile.util.Banco;

public class DaoCompra {
    private SQLiteDatabase db;
    private Banco banco;


    public DaoCompra(Context context){
        super();
        this.banco = new Banco(context);

        this.db = banco.getWritableDatabase();

    }

    public String inserir(Compra tr) throws Exception {
        ContentValues valores;
        long resultado;

        db = banco.getWritableDatabase();
        valores = new ContentValues();
        valores.put("id_usuario", tr.getIdU());
        valores.put("id_produto", tr.getIdP());
        valores.put("data", tr.getData());
        valores.put("quantidade", tr.getQuantidade());


        resultado = db.insert("compra", null, valores);
        db.close();

        if (resultado == -1) return "Erro ao inserir registro";
        else return "Registro Inserido com sucesso";
    }

    public String alterar(Compra tr) throws Exception {
        ContentValues valores;
        String where;

        db = banco.getWritableDatabase();

        where = "_id = " + tr.getId();

        valores = new ContentValues();
        valores.put("id_usuario", tr.getIdU());
        valores.put("id_produto", tr.getIdP());
        valores.put("data", tr.getData());
        valores.put("quantidade", tr.getQuantidade());

        long resultado = db.update("compra",valores,where,null);
        db.close();

        if (resultado == -1) return "Erro ao alterar registro";
        else return "Registro alterado com sucesso";
    }

    public String remover(Compra tr) {
        String where = "_id = " + tr.getId();

        db = banco.getReadableDatabase();
        long resultado = db.delete("compra",where,null);
        db.close();

        if (resultado == -1) return "Erro ao remover registro";
        else return "Registro removido com sucesso";
    }

    public Cursor listar() {
        Cursor cursor;
        String[] campos = {"_id", "id_usuario", "id_produto", "data","quantidade"};
        db = banco.getReadableDatabase();
        cursor = db.query("compra", campos, null, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }

    public Cursor buscar(Compra tr) {
        Cursor cursor;
        String[] campos = {"_id", "id_usuario", "id_produto", "data","quantidade"};
        String where = "_id = " + tr.getId();
        db = banco.getReadableDatabase();
        cursor = db.query("compra", campos, where, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }

}
