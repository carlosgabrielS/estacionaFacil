package br.senai.sp.estacionamento;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import br.senai.sp.estacionamento.model.Movimentacao;
import br.senai.sp.estacionamento.tasks.GravarMovimentacao;

public class CadastroMovimentacao extends AppCompatActivity {

    private Button btGravar;
    private EditText txtPlaca;
    private EditText txtModelo;
    private String array_spinner[];
    private String nome;
    private String tipo;
    private Movimentacao movimentacao;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        btGravar = findViewById(R.id.btn_salvar);
        txtPlaca = findViewById(R.id.txt_placa);
        txtModelo = findViewById(R.id.txt_modelo);


        array_spinner=new String[3];
        array_spinner[0]="Avulso";
        array_spinner[1]="Diarista";
        array_spinner[2]="Mensalista";

        Spinner s = (Spinner) findViewById(R.id.spiner_tipo);
        ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item, array_spinner);
        s.setAdapter(adapter);

        s.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                nome = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        movimentacao = new Movimentacao();

        btGravar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
            movimentacao.setPlaca(txtPlaca.getText().toString());
            movimentacao.setModelo(txtModelo.getText().toString());

                switch (nome){
                    case "Avulso":{
                        tipo = "A";
                        break;
                    }

                    case "Diarista":{
                        tipo = "D";
                        break;
                    }

                    case "Mensalista":{
                        tipo = "M";
                        break;
                    }

                }

            movimentacao.setTipo(tipo);
            GravarMovimentacao gravarMovimentacao = new GravarMovimentacao(movimentacao);
            gravarMovimentacao.execute();
            finish();
            }
        });

    }
}
