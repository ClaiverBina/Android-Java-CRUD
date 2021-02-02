package com.example.compramobile.view.produto;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.compramobile.R;
import com.example.compramobile.bean.Produto;
import com.example.compramobile.controller.ControleProduto;
import com.example.compramobile.view.Menu;

public class CriaProduto extends AppCompatActivity {

    EditText txtNome, txtMarca, txtPreco;

    Produto bean;
    ControleProduto controller;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cria_acao);

        txtNome = (EditText) findViewById(R.id.nomePTxt);
        txtMarca = (EditText) findViewById(R.id.marcaPTxt);
        txtPreco = (EditText) findViewById(R.id.precoTxt);

    }

    public void cadastrarAcao(View v) {
        try {
            bean = new Produto();
            bean.setNome(txtNome.getText().toString());
            bean.setMarca(txtMarca.getText().toString());
            bean.setPreco(Double.parseDouble(txtPreco.getText().toString()));


            controller = new ControleProduto(this);
            String resultado = controller.inserir(bean);


            Toast.makeText(this, resultado, Toast.LENGTH_SHORT).show();
            intent = new Intent(CriaProduto.this, Menu.class);
            this.startActivity(intent);
        }
        catch(Exception e) {
            System.out.println("ERRO: " + e.getMessage());
        }
    }

    public void cancelarCadastroAcao(View v) {
        super.onBackPressed();
        finish();
    }
}