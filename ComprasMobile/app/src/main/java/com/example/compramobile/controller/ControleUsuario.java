package com.example.compramobile.controller;

import android.content.Context;
import android.database.Cursor;

import com.example.compramobile.bean.Usuario;
import com.example.compramobile.dao.DaoUsuario;

public class ControleUsuario {
    private DaoUsuario dao;
    private Context context;
    private Usuario usu;

    public ControleUsuario(Context context, Usuario usuario) throws Exception, ClassNotFoundException {
        this.context = context;
        this.usu = usuario;
        this.dao = new DaoUsuario(this.context, this.usu);
    }

    public Cursor login() throws ClassNotFoundException, Exception {

        return this.dao.login();
    }
    public String inserir() throws ClassNotFoundException, Exception {

        return this.dao.inserir();
    }

    public String alterar() throws ClassNotFoundException, Exception {
        return this.dao.alterar();
    }

    public Cursor buscar() throws ClassNotFoundException, Exception {
        return this.dao.buscar();
    }

    public Cursor listar() throws ClassNotFoundException, Exception {
        return this.dao.listar();
    }

    public String remover() throws ClassNotFoundException, Exception {
        return this.dao.remover();
    }
}
