package com.example.compramobile.view.produto;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.compramobile.bean.Produto;
import com.example.compramobile.controller.ControleProduto;
import com.example.compramobile.R;

import com.example.compramobile.view.Menu;

public class EditaProduto extends AppCompatActivity {

    Bundle bundle;
    Intent intent;
    EditText editaNome, editaMarca, editaPreco;

    Produto bean;
    ControleProduto controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edita_acao);

        bundle = getIntent().getExtras();

        editaNome = (EditText) findViewById(R.id.nomePTxt2);
        editaMarca = (EditText) findViewById(R.id.marcaTxt2);
        editaPreco = (EditText) findViewById(R.id.precoTxt2);


        editaNome.setText(bundle.getString("nome"));
        editaMarca.setText(bundle.getString("marca"));
        editaPreco.setText(String.valueOf(bundle.getDouble("preco")));

    }

    public void alterarAcao(View v){
        try {
            bean = new Produto();
            bean.setId(bundle.getInt("_id"));
            bean.setNome(editaNome.getText().toString());
            bean.setMarca(editaMarca.getText().toString());
            bean.setPreco(Double.parseDouble(editaPreco.getText().toString()));


            controller = new ControleProduto(this);
            String resposta = controller.alterar(bean);
            Toast.makeText(this, resposta, Toast.LENGTH_SHORT).show();
            intent = new Intent(EditaProduto.this, Menu.class);
            this.startActivity(intent);
            finish();
        }
        catch(Exception e) {
            System.out.println("ERRO: " + e.getMessage());
        }
    }

    public void cancelarAlteracaoAcao(View v) {
        intent = new Intent(EditaProduto.this, Menu.class);
        this.startActivity(intent);
        finish();
    }
}