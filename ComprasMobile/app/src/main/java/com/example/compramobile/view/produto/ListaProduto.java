package com.example.compramobile.view.produto;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import androidx.appcompat.app.AppCompatActivity;

import com.example.compramobile.bean.Produto;
import com.example.compramobile.controller.ControleProduto;
import com.example.compramobile.R;


public class ListaProduto extends AppCompatActivity {

    ListView lista;
    Produto bean;
    ControleProduto controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_acao);
        lista = (ListView) findViewById(R.id.listViewAcao);
        this.carregarDados();
    }

    protected void carregarDados() {
        try {
            bean = new Produto();
            controller = new ControleProduto(this);
            final Cursor cursor = controller.listar(bean);
            String[] nomeCampos = {"_id", "nome", "marca", "preco"};
            int[] idViewLayout = {R.id.idAcaoLayout, R.id.tickerAcaoLayout, R.id.empresaAcaoLayout, R.id.cotacaoAcaoLayout};
            SimpleCursorAdapter adapter = new SimpleCursorAdapter(getBaseContext(), R.layout.acao_layout, cursor, nomeCampos, idViewLayout, 0);
            lista.setAdapter(adapter);

            lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int posicao, long idx) {
                    int codigo;
                    cursor.moveToPosition(posicao);
                    codigo = cursor.getInt(cursor.getColumnIndexOrThrow("_id"));
                    Intent tela = new Intent(ListaProduto.this, VerProduto.class);
                    tela.putExtra("_id", codigo);
                    startActivity(tela);
                }
            });
        }
        catch(Exception e) {
            System.out.println("ERRO ---> " + e.getMessage());
        }
    }
}