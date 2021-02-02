package com.example.compramobile.view.compra;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.compramobile.R;
import com.example.compramobile.bean.Compra;
import com.example.compramobile.controller.ControleCompra;
import com.example.compramobile.view.Menu;

public class CriaCompra extends AppCompatActivity {

    EditText usu, produto, data, quantidade;

    Compra bean;
    ControleCompra controller;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cria_transacao);

        usu = (EditText) findViewById(R.id.idUTxt);
        produto = (EditText) findViewById(R.id.idPTxt);
        data = (EditText) findViewById(R.id.dataTxt);
        quantidade = (EditText) findViewById(R.id.quantidadeTxt);

    }

    public void cadastrarTransacao(View v) {
        try {
            bean = new Compra();

            bean.setIdU(Integer.valueOf(usu.getText().toString()));
            bean.setIdP(Integer.valueOf(produto.getText().toString()));

            bean.setData(data.getText().toString());
            bean.setQuantidade(Integer.parseInt(quantidade.getText().toString()));


            controller = new ControleCompra(this);
            String resultado = controller.inserir(bean);


            Toast.makeText(this, resultado, Toast.LENGTH_SHORT).show();
            intent = new Intent(CriaCompra.this, Menu.class);
            this.startActivity(intent);
        }
        catch(Exception e) {
            System.out.println("ERRO: " + e.getMessage());
        }
    }

    public void cancelarCadastroTransacao(View v) {
        super.onBackPressed();
        finish();
    }
}