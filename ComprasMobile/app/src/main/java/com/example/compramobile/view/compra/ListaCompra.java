package com.example.compramobile.view.compra;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import androidx.appcompat.app.AppCompatActivity;

import com.example.compramobile.R;
import com.example.compramobile.bean.Compra;
import com.example.compramobile.controller.ControleCompra;


public class ListaCompra extends AppCompatActivity {

    ListView lista;
    Compra bean;
    ControleCompra controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_transacao);
        lista = (ListView) findViewById(R.id.listViewTransacao);
        this.carregarDados();
    }

    protected void carregarDados() {
        try {
            bean = new Compra();
            controller = new ControleCompra(this);
            final Cursor cursor = controller.listar();
            String[] nomeCampos = {"_id", "id_usuario", "id_produto", "data","quantidade"};
            int[] idViewLayout = {R.id.idTransacaoLayout, R.id.usuarioTransacaoLayout, R.id.acaoTransacaoLayout, R.id.papelTransacaoLayout,R.id.dataTransacaoLayout};
            SimpleCursorAdapter adapter = new SimpleCursorAdapter(getBaseContext(), R.layout.transacao_layout, cursor, nomeCampos, idViewLayout, 0);
            lista.setAdapter(adapter);

            lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int posicao, long idx) {
                    int codigo;
                    cursor.moveToPosition(posicao);
                    codigo = cursor.getInt(cursor.getColumnIndexOrThrow("_id"));
                    Intent tela = new Intent(ListaCompra.this, VerCompra.class);
                    tela.putExtra("_id", codigo);
                    startActivity(tela);
                }
            });
        }
        catch(Exception e) {
            System.out.println("ERRO => " + e.getMessage());
        }
    }
}