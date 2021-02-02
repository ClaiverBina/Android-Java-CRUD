package com.example.compramobile.view;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.compramobile.R;
import com.example.compramobile.bean.Usuario;
import com.example.compramobile.controller.ControleUsuario;
import com.example.compramobile.view.usuario.CriaUsuario;

public class Login extends AppCompatActivity {

    EditText loginTxt, senhaTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        this.setTitle("Login");

        loginTxt = (EditText) findViewById(R.id.loginTxt);
        senhaTxt = (EditText) findViewById(R.id.senhaTxt);
    }

    public void logar(View v) {
        try {
            Usuario bean = new Usuario();
            bean.setLogin(loginTxt.getText().toString());
            bean.setSenha(senhaTxt.getText().toString());

            ControleUsuario controller = new ControleUsuario(this, bean );
            Cursor resposta = controller.login();
            if(resposta.getCount() > 0) {
                Toast.makeText(this, "Login efetuado com sucesso", Toast.LENGTH_SHORT).show();
                Usuario beanparam = new Usuario();
                beanparam.setId(resposta.getInt(resposta.getColumnIndex("_id")));
                beanparam.setNome(resposta.getString(resposta.getColumnIndex("nome")));
                beanparam.setCpf(resposta.getString(resposta.getColumnIndex("cpf")));
                beanparam.setLogin(resposta.getString(resposta.getColumnIndex("login")));
                beanparam.setSenha(resposta.getString(resposta.getColumnIndex("senha")));


                Intent tela = new Intent(Login.this, Menu.class);

                tela.putExtra("_id", beanparam.getId());
                tela.putExtra("nome", beanparam.getNome());
                tela.putExtra("cpf", beanparam.getCpf());
                tela.putExtra("login", beanparam.getLogin());
                tela.putExtra("senha", beanparam.getSenha());


                this.startActivity(tela);
            }
            else {
                Toast.makeText(this, "Falha ao acessar. Verifique os dados.", Toast.LENGTH_SHORT).show();
            }
        }
        catch(Exception e) {
            System.out.println("Erro => " + e.getMessage());
        }
    }

    public void telaCadastro(View v) {
        Intent intent = new Intent(Login.this, CriaUsuario.class);
        this.startActivity(intent);
    }
}