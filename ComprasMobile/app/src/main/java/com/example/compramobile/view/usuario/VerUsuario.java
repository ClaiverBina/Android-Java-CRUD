package com.example.compramobile.view.usuario;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.compramobile.R;
import com.example.compramobile.bean.Usuario;
import com.example.compramobile.controller.ControleUsuario;
import com.example.compramobile.view.Menu;

public class VerUsuario extends AppCompatActivity {

    TextView verNomeUsuario, verCpfUsuario, verLoginUsuario, verIdUsuario;
    Intent intent = null;
    Usuario bean;
    ControleUsuario controller;
    int codigo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_usuario);

        this.carregarDados();
    }

    protected void carregarDados() {
        try {
        intent = getIntent();
        codigo = intent.getExtras().getInt("_id");
        verIdUsuario = (TextView) findViewById(R.id.verIdUsuario);
        verNomeUsuario = (TextView) findViewById(R.id.verNomeUsuario);
        verCpfUsuario = (TextView) findViewById(R.id.verCpfUsuario);
        verLoginUsuario = (TextView) findViewById(R.id.verLoginUsuario);


        bean = new Usuario();
        bean.setId(codigo);
        controller = new ControleUsuario(getBaseContext(), bean);
        Cursor cursor = controller.buscar();

        bean.setId(cursor.getInt(cursor.getColumnIndexOrThrow("_id")));
        verIdUsuario.setText(verIdUsuario.getText().toString() + bean.getId());
        bean.setNome(cursor.getString(cursor.getColumnIndexOrThrow("nome")));
        verNomeUsuario.setText(verNomeUsuario.getText().toString() + bean.getNome());
        bean.setCpf(cursor.getString(cursor.getColumnIndexOrThrow("cpf")));
        verCpfUsuario.setText(verCpfUsuario.getText().toString() + bean.getCpf());
        bean.setLogin(cursor.getString(cursor.getColumnIndexOrThrow("login")));
        verLoginUsuario.setText(verLoginUsuario.getText().toString() + bean.getLogin());

        bean.setSenha(cursor.getString(cursor.getColumnIndexOrThrow("senha")));
        }
        catch (Exception e) {
            System.out.println("Erro -> " + e.getMessage());
        }
    }

    public void novoUsuario(View v) {
        intent = new Intent(VerUsuario.this, CriaUsuario.class);
        this.startActivity(intent);
    }

    public void editarUsuario(View v) {
        intent = new Intent(VerUsuario.this, EditaUsuario.class);
        intent.putExtra("_id", bean.getId());
        intent.putExtra("nome", bean.getNome());
        intent.putExtra("cpf", bean.getCpf());
        intent.putExtra("login", bean.getLogin());
        intent.putExtra("senha", bean.getSenha());

        this.startActivity(intent);
    }

    public void removerUsuario(View v) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Deseja remover esse usuário?")
            .setPositiveButton("SIM!", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i){
                    try {
                        bean = new Usuario();
                        bean.setId(codigo);
                        controller = new ControleUsuario(getBaseContext(), bean);
                        String resultado = controller.remover();
                        Toast.makeText(getBaseContext(), resultado, Toast.LENGTH_SHORT).show();
                        intent = new Intent(VerUsuario.this, Menu.class);
                        startActivity(intent);
                    }
                    catch (Exception e) {
                        System.out.println("ERRO: " + e.getMessage());
                    }
                }
            })
            .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(getBaseContext(), "Ação cancelada com sucesso.", Toast.LENGTH_SHORT).show();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}