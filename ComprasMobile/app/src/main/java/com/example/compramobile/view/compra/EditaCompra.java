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

public class EditaCompra extends AppCompatActivity {

    Bundle bundle;
    Intent intent;
    EditText usuT, produto, dataT, quantidade;


    Compra bean;
    ControleCompra controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edita_transacao);

        bundle = getIntent().getExtras();


        usuT = (EditText) findViewById(R.id.usuExt2);
        produto = (EditText) findViewById(R.id.prodETxt2);
        dataT = (EditText) findViewById(R.id.dataTxt2);
        quantidade= (EditText) findViewById(R.id.quantidadeTxt2);


        usuT.setText(Integer.valueOf(bundle.getInt("id_usuario")));
        produto.setText(Integer.valueOf(bundle.getInt("id_produto")));
        dataT.setText(bundle.getString("data"));
        quantidade.setText(bundle.getString("quantidade"));



    }

    public void alterarTransacao(View v){
        try {
            bean = new Compra();

            bean.setId(bundle.getInt("_id"));

            bean.setIdU(Integer.valueOf(usuT.getText().toString()));
            bean.setIdP(Integer.valueOf(produto.getText().toString()));

            bean.setData(dataT.getText().toString());
            bean.setQuantidade(Integer.parseInt(quantidade.getText().toString()));


            controller = new ControleCompra(this);
            String resposta = controller.alterar(bean);
            Toast.makeText(this, resposta, Toast.LENGTH_SHORT).show();
            intent = new Intent(EditaCompra.this, Menu.class);
            this.startActivity(intent);
            finish();
        }
        catch(Exception e) {
            System.out.println("ERRO: " + e.getMessage());
        }
    }

    public void cancelarAlteracaoTransacao(View v) {
        intent = new Intent(EditaCompra.this, Menu.class);
        this.startActivity(intent);
        finish();
    }
}