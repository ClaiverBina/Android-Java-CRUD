package com.example.compramobile.view.usuario;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import androidx.appcompat.app.AppCompatActivity;

import com.example.compramobile.bean.Usuario;
import com.example.compramobile.controller.ControleUsuario;
import com.example.compramobile.R;


public class ListaUsuario extends AppCompatActivity {
    private Usuario bean;
    private ControleUsuario controller;
    private ListView lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_usuario);
        lista = (ListView) findViewById(R.id.listViewUsuario);
        this.carregarDados();
    }

    protected void carregarDados() {
        try {
            bean = new Usuario();
            controller = new ControleUsuario(this, bean);
            final Cursor cursor = controller.listar();

            String[] nomeCampos = {"_id", "nome", "cpf", "login"};
            int[] idViewLayout = {R.id.idUsuarioLayout, R.id.nomeUsuarioLayout, R.id.cpfUsuarioLayout, R.id.loginUsuarioLayout};
            SimpleCursorAdapter adapter = new SimpleCursorAdapter(getBaseContext(), R.layout.usuario_layout, cursor, nomeCampos, idViewLayout, 0);
            lista.setAdapter(adapter);
            lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int posicao, long idx) {
                    int codigo;
                    cursor.moveToPosition(posicao);
                    codigo = cursor.getInt(cursor.getColumnIndexOrThrow("_id"));
                    Intent tela = new Intent(ListaUsuario.this, VerUsuario.class);
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