package com.example.compramobile.controller;

import android.content.Context;
import android.database.Cursor;

import com.example.compramobile.bean.Produto;
import com.example.compramobile.dao.DaoProduto;

public class ControleProduto {

    private DaoProduto dao;
    private Context context;

    public ControleProduto(Context context) throws Exception, ClassNotFoundException {
        this.context = context;
        this.dao = new DaoProduto(this.context);
    }

    public String inserir(Produto produto) throws ClassNotFoundException, Exception {

        return this.dao.inserir(produto);
    }

    public String alterar(Produto produto) throws ClassNotFoundException, Exception {
        return this.dao.alterar(produto);
    }

    public Cursor buscar(Produto produto) throws ClassNotFoundException, Exception {
        return this.dao.buscar(produto);
    }

    public Cursor listar(Produto produto) throws ClassNotFoundException, Exception {
        return this.dao.listar(produto);
    }

    public String remover(Produto produto) throws ClassNotFoundException, Exception {
        return this.dao.remover(produto);
    }
}
