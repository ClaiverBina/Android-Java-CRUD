package com.example.compramobile.view.compra;

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
import com.example.compramobile.bean.Compra;
import com.example.compramobile.controller.ControleCompra;
import com.example.compramobile.view.Menu;

public class VerCompra extends AppCompatActivity {

    Intent intent;
    Compra bean;
    ControleCompra controller;
    TextView verId, usu, produto, data, quantidade;
    int codigo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_transacao);

        this.carregarDados();
    }

    public void carregarDados() {
        try {

            intent = getIntent();
            codigo = intent.getExtras().getInt("_id");

            verId = findViewById(R.id.verIdTransacao);
            usu = findViewById(R.id.verUsuarioTransacao);
            produto = findViewById(R.id.verAcaoTransacao);
            data = findViewById(R.id.verDataTransacao);
            quantidade = findViewById(R.id.verQuantidadeTransacao);



            bean = new Compra();
            bean.setId(codigo);
            controller = new ControleCompra(getBaseContext());
            Cursor cursor = controller.buscar(bean);

            bean.setId(cursor.getInt(cursor.getColumnIndexOrThrow("_id")));
            verId.setText(verId.getText().toString() + bean.getId());
            bean.setIdU(cursor.getInt(cursor.getColumnIndexOrThrow("id_usuario")));
            usu.setText(usu.getText().toString() + bean.getIdU());
            bean.setIdP(cursor.getInt(cursor.getColumnIndexOrThrow("id_produto")));
            produto.setText(produto.getText().toString() + bean.getIdP());
            bean.setData(cursor.getString(cursor.getColumnIndexOrThrow("data")));
            data.setText(data.getText().toString() + bean.getData());
            bean.setQuantidade(cursor.getInt(cursor.getColumnIndexOrThrow("quantidade")));
            quantidade.setText(quantidade.getText().toString() + bean.getQuantidade());

        }
        catch(Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    public void editarTransacao(View v) throws Exception {
        intent = new Intent(VerCompra.this, EditaCompra.class);

        intent.putExtra("_id", bean.getId());
        intent.putExtra("id_usuario", bean.getIdU());
        intent.putExtra("id_produto", bean.getIdP());
        intent.putExtra("data", bean.getData());
        intent.putExtra("quantidade", bean.getQuantidade());

        this.startActivity(intent);
    }

    public void removerTransacao(View v) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Deseja remover essa transação?")
                .setPositiveButton("SIM!", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i){
                        try {
                            bean = new Compra();
                            bean.setId(codigo);
                            controller = new ControleCompra(getBaseContext());
                            String resultado = controller.remover(bean);
                            Toast.makeText(getBaseContext(), resultado, Toast.LENGTH_SHORT).show();
                            intent = new Intent(VerCompra.this, Menu.class);
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
                        Toast.makeText(getBaseContext(), "Ação cancelada com sucesso!", Toast.LENGTH_SHORT).show();
                    }
                });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void novaTransacao(View v) {
        intent = new Intent(VerCompra.this, CriaCompra.class);
        this.startActivity(intent);
    }
}