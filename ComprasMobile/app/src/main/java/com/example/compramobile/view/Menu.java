package com.example.compramobile.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.compramobile.R;
import com.example.compramobile.view.produto.CriaProduto;
import com.example.compramobile.view.produto.ListaProduto;
import com.example.compramobile.view.compra.CriaCompra;
import com.example.compramobile.view.compra.ListaCompra;
import com.example.compramobile.view.usuario.ListaUsuario;

public class Menu extends AppCompatActivity {

    Intent tela;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        //this.setTitle("Menu de acesso");
       // intent = getIntent();
    }

    public void listarUsuarios(View v) {
        tela = new Intent(Menu.this, ListaUsuario.class);
        this.startActivity(tela);
    }

    public void listarAcoes(View v) {
        tela = new Intent(Menu.this, ListaProduto.class);
        this.startActivity(tela);
    }

    public void novaAcao(View v) {
        tela = new Intent(Menu.this, CriaProduto.class);
        this.startActivity(tela);
    }

    public void listarTransacoes(View v) {
        tela = new Intent(Menu.this, ListaCompra.class);
        this.startActivity(tela);
    }

    public void novaTransacoes(View v) {
        tela = new Intent(Menu.this, CriaCompra.class);
        this.startActivity(tela);
    }

    public void logout(View v) {
        tela = new Intent(Menu.this, Login.class);
        this.startActivity(tela);
    }
}