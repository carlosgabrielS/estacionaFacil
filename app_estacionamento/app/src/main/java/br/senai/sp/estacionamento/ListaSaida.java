package br.senai.sp.estacionamento;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import br.senai.sp.estacionamento.model.Movimentacao;
import br.senai.sp.estacionamento.tasks.CarregarListaSaida;
import br.senai.sp.estacionamento.tasks.SaidaMovimentacao;

public class ListaSaida extends AppCompatActivity {
    public static ListView listViewSaida;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_saida);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        listViewSaida = findViewById(R.id.lista_saida);

        listViewSaida.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Movimentacao movimentacao = (Movimentacao) listViewSaida.getItemAtPosition(position);
                SaidaMovimentacao saidaMovimentacao = new SaidaMovimentacao(movimentacao, ListaSaida.this);

                saidaMovimentacao.execute();
            }
        });
    }


    @Override
    protected void onResume() {
        super.onResume();

        CarregarListaSaida carregarListaSaida = new CarregarListaSaida(this);
        carregarListaSaida.execute();
    }
}
