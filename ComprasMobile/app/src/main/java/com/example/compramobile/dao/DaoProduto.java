package com.example.compramobile.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.compramobile.bean.Produto;
import com.example.compramobile.util.Banco;

public class DaoProduto {

    private SQLiteDatabase db;
    private Banco banco;

    public DaoProduto(Context context ){
        super();
        this.banco = new Banco(context);
        this.db = banco.getWritableDatabase();

    }

    public String inserir(Produto produto) {
        ContentValues valores;
        long resultado;

        db = banco.getWritableDatabase();
        valores = new ContentValues();
        valores.put("nome", produto.getNome());
        valores.put("marca", produto.getMarca());
        valores.put("preco", produto.getPreco());


        resultado = db.insert("produto", null, valores);
        db.close();

        if (resultado == -1) return "Erro ao inserir registro";
        else return "Registro Inserido com sucesso";
    }

    public String alterar(Produto produto) {
        ContentValues valores;
        String where;

        db = banco.getWritableDatabase();

        where = "_id = " + produto.getId();

        valores = new ContentValues();
        valores.put("nome", produto.getNome());
        valores.put("marca", produto.getMarca());
        valores.put("preco", produto.getPreco());

        long resultado = db.update("produto",valores,where,null);
        db.close();

        if (resultado == -1) return "Erro ao alterar registro";
        else return "Registro alterado com sucesso";
    }

    public String remover(Produto produto) {
        String where = "_id = " + produto.getId();

        db = banco.getReadableDatabase();
        long resultado = db.delete("produto",where,null);
        db.close();

        if (resultado == -1) return "Erro ao remover registro";
        else return "Registro removido com sucesso";
    }

    public Cursor listar(Produto produto) {
        Cursor cursor;
        String[] campos = {"_id", "nome", "marca", "preco"};
        db = banco.getReadableDatabase();
        cursor = db.query("produto", campos, null, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }

    public Cursor buscar(Produto produto) {
        Cursor cursor;
        String[] campos = {"_id", "nome", "marca", "preco"};
        String where = "_id = " + produto.getId();
        db = banco.getReadableDatabase();
        cursor = db.query("produto", campos, where, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }

}
