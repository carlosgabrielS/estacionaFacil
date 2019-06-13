package br.senai.sp.estacionamento;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import br.senai.sp.estacionamento.tasks.CarregarListaMovimentacoes;

public class ListaMovimentacao extends AppCompatActivity {
    public static ListView listViewMovimentacao;
    private Toolbar toolbar;
    private Button btNovo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_movimentacao);


        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        listViewMovimentacao = findViewById(R.id.lista_movimentacao);
        btNovo = findViewById(R.id.bt_nova_movimentacao);

        btNovo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentCadastro = new Intent(ListaMovimentacao.this, CadastroMovimentacao.class);
                startActivity(intentCadastro);
            }
        });

    }


    @Override
    protected void onResume() {
        super.onResume();

        CarregarListaMovimentacoes carregarListaMovimentacoes = new CarregarListaMovimentacoes(this);
        carregarListaMovimentacoes.execute();
    }

}
