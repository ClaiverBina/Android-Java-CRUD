package com.example.compramobile.view.produto;

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
import com.example.compramobile.bean.Produto;
import com.example.compramobile.controller.ControleProduto;
import com.example.compramobile.view.Menu;

public class VerProduto extends AppCompatActivity {

    Intent intent;
    Produto bean;
    ControleProduto controller;
    TextView verId, verNome, verMarca, verPreco;
    int codigo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_acao);

        this.carregarDados();
    }

    public void carregarDados() {
        try {

            intent = getIntent();
            codigo = intent.getExtras().getInt("_id");

            verId = findViewById(R.id.verIdAcao);
            verNome = findViewById(R.id.verTickerAcao);
            verMarca = findViewById(R.id.verEmpresaAcao);
            verPreco = findViewById(R.id.verCotacaoAcao);


            bean = new Produto();
            bean.setId(codigo);
            controller = new ControleProduto(getBaseContext());
            Cursor cursor = controller.buscar(bean);

            bean.setId(cursor.getInt(cursor.getColumnIndexOrThrow("_id")));
            verId.setText(verId.getText().toString() + bean.getId());
            bean.setNome(cursor.getString(cursor.getColumnIndexOrThrow("nome")));
            verNome.setText(verNome.getText().toString() + bean.getNome());
            bean.setMarca(cursor.getString(cursor.getColumnIndexOrThrow("marca")));
            verMarca.setText(verMarca.getText().toString() + bean.getMarca());
            bean.setPreco(cursor.getDouble(cursor.getColumnIndexOrThrow("preco")));
            verPreco.setText(verPreco.getText().toString() + bean.getPreco());

        }
        catch(Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    public void editarAcao(View v) {
        intent = new Intent(VerProduto.this, EditaProduto.class);
        intent.putExtra("_id", bean.getId());
        intent.putExtra("nome", bean.getNome());
        intent.putExtra("marca",bean.getMarca());
        intent.putExtra("preco", bean.getPreco());

        this.startActivity(intent);
    }

    public void removerAcao(View v) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Deseja remover esse evento?")
                .setPositiveButton("SIM!", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i){
                        try {
                            bean = new Produto();
                            bean.setId(codigo);
                            controller = new ControleProduto(getBaseContext());
                            String resultado = controller.remover(bean);
                            Toast.makeText(getBaseContext(), resultado, Toast.LENGTH_SHORT).show();
                            intent = new Intent(VerProduto.this, Menu.class);
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

    public void novaAcao(View v) {
        intent = new Intent(VerProduto.this, CriaProduto.class);
        this.startActivity(intent);
    }
}