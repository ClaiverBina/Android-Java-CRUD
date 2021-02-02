package com.example.compramobile.view.usuario;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.compramobile.bean.Usuario;
import com.example.compramobile.R;

import com.example.compramobile.controller.ControleUsuario;
import com.example.compramobile.view.Menu;

public class EditaUsuario extends AppCompatActivity {

    Bundle bundle;
    Intent intent;
    EditText editaNomeUsuario, editaCpfUsuario, editaLoginUsuario, editaSenhaUsuario,editaSobrenomeUsuario;
    Usuario bean;
    ControleUsuario controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edita_usuario);

        bundle = getIntent().getExtras();
        editaNomeUsuario = (EditText) findViewById(R.id.nomeTxt2);
        editaCpfUsuario = (EditText) findViewById(R.id.cpfTxt2);
        editaLoginUsuario = (EditText) findViewById(R.id.userloginTxt2);
        editaSenhaUsuario = (EditText) findViewById(R.id.usersenhaTxt2);


        editaNomeUsuario.setText(bundle.getString("nome"));
        editaCpfUsuario.setText(bundle.getString("cpf"));
        editaLoginUsuario.setText(bundle.getString("login"));
        editaSenhaUsuario.setText(bundle.getString("senha"));

    }

    public void alteraUsuario(View v){
        try {
            bean = new Usuario();
            bean.setId(bundle.getInt("_id"));
            bean.setNome(editaNomeUsuario.getText().toString());
            bean.setCpf(editaCpfUsuario.getText().toString());
            bean.setLogin(editaLoginUsuario.getText().toString());
            if(editaSenhaUsuario.getText().toString().length() > 0) {
                bean.setSenha(editaSenhaUsuario.getText().toString());
            }
            else {
                bean.setSenha(bundle.getString("senha"));
            }


            controller = new ControleUsuario(this, bean);
            String resposta = controller.alterar();
            Toast.makeText(this, resposta, Toast.LENGTH_SHORT).show();
            intent = new Intent(EditaUsuario.this, Menu.class);
            this.startActivity(intent);
            finish();
        }
        catch(Exception e) {
            System.out.println("ERRO: " + e.getMessage());
        }
    }

    public void cancelarAlteracaoUsuario(View v) {
        intent = new Intent(EditaUsuario.this, Menu.class);
        this.startActivity(intent);
        finish();

    }
}