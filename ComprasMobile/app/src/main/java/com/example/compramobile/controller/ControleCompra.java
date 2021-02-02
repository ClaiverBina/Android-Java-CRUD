package com.example.compramobile.controller;

import android.content.Context;
import android.database.Cursor;

import com.example.compramobile.bean.Compra;
import com.example.compramobile.dao.DaoCompra;

public class ControleCompra {
    private DaoCompra dao;
    private Context context;

    public ControleCompra(Context context) throws Exception, ClassNotFoundException {
        this.context = context;
        this.dao = new DaoCompra(this.context);
    }

    public String inserir(Compra tr) throws ClassNotFoundException, Exception {

        return this.dao.inserir(tr);
    }

    public String alterar(Compra tr) throws ClassNotFoundException, Exception {
        return this.dao.alterar(tr);
    }

    public Cursor buscar(Compra tr) throws ClassNotFoundException, Exception {
        return this.dao.buscar(tr);
    }

    public Cursor listar() throws ClassNotFoundException, Exception {
        return this.dao.listar();
    }

    public String remover(Compra tr) throws ClassNotFoundException, Exception {
        return this.dao.remover(tr);
    }
}
