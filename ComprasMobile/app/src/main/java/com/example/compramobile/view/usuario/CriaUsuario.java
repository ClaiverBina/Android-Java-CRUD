package com.example.compramobile.view.usuario;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.compramobile.bean.Usuario;
import com.example.compramobile.R;

import com.example.compramobile.controller.ControleUsuario;

public class CriaUsuario extends AppCompatActivity {

    EditText txtNome, txtCpf, txtLogin, txtSenha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cria_usuario);

        txtNome = (EditText) findViewById(R.id.nomeTxt);
        txtCpf = (EditText) findViewById(R.id.cpfTxt);
        txtLogin = (EditText) findViewById(R.id.userloginTxt);
        txtSenha = (EditText) findViewById(R.id.usersenhaTxt);

    }

    public void cadastrarUsuario(View v) {
        try {

            Usuario bean = new Usuario();
            bean.setId(0);
            bean.setNome(txtNome.getText().toString());
            bean.setCpf(txtCpf.getText().toString());
            bean.setLogin(txtLogin.getText().toString());
            bean.setSenha(txtSenha.getText().toString());


            ControleUsuario controller = new ControleUsuario(this, bean);
            String resposta = controller.inserir();
            Toast.makeText(this, resposta, Toast.LENGTH_SHORT).show();
            super.onBackPressed();
        }
        catch(Exception e) {
            System.out.println("Erro " + e.getMessage());
        }
    }

    public void cancelarCadastroUsuario(View v) {
//        Intent intent = new Intent(CriaUsuario.this, Login.class);
//        this.startActivity(intent);
        super.onBackPressed();
    }
}