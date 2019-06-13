package br.senai.sp.estacionamento;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.concurrent.Executors;

import br.senai.sp.estacionamento.model.Preco;
import br.senai.sp.estacionamento.tasks.GravarMovimentacao;
import br.senai.sp.estacionamento.tasks.GravarPreco;

public class CadastroPrecoActivity extends AppCompatActivity {

    private Button btnSalvar;
    private EditText txtPrimeiraHora;
    private EditText txtDemaisHora;
    private EditText txtTolerancia;
    private Preco preco;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_preco);

        btnSalvar = findViewById(R.id.btn_salvar);
        txtPrimeiraHora = findViewById(R.id.txt_primeira_hora);
        txtDemaisHora = findViewById(R.id.txt_demais_horas);
        txtTolerancia = findViewById(R.id.txt_tolerancia);

        preco = new Preco();

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                Toast.makeText(CadastroPrecoActivity.this, "oi, to aqui", Toast.LENGTH_LONG).show();

                preco.setPrimeiraHora(Double.parseDouble(txtPrimeiraHora.getText().toString()));
                preco.setDemaisHoras(Double.parseDouble(txtDemaisHora.getText().toString()));
                preco.setTolerancia(Integer.parseInt(txtTolerancia.getText().toString()));

                GravarPreco gravarPreco = new GravarPreco(preco);
                gravarPreco.execute();
                finish();
            }
        });
    }
}
