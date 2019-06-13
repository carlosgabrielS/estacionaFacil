package br.senai.sp.estacionamento;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import br.senai.sp.estacionamento.model.Movimentacao;
import br.senai.sp.estacionamento.tasks.CarregarListaSaida;
import br.senai.sp.estacionamento.tasks.FecharMovimentacao;
import br.senai.sp.estacionamento.tasks.SaidaMovimentacao;

public class CarregarMovimentacao extends AppCompatActivity {

    private EditText txtPlaca;
    private EditText txtModelo;
    private EditText txtHoraEntrada;
    private EditText txtHoraSaida;
    private EditText txtTempo;
    private EditText txtValorPago;
    private EditText txtTipo;
    private Movimentacao movimentacao;
    private String tipo;
    private Button btnFecharMovimentacao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fechar_movimentacao);

        txtPlaca = findViewById(R.id.txt_placa_saida);
        txtModelo = findViewById(R.id.txt_modelo_saida);
        txtHoraEntrada = findViewById(R.id.txt_horario_entrada);
        txtHoraSaida = findViewById(R.id.txt_horario_saida);
        txtTempo = findViewById(R.id.txt_tempo);
        txtValorPago = findViewById(R.id.txt_valor);
        txtTipo = findViewById(R.id.txt_tipo_saida);

        btnFecharMovimentacao = findViewById(R.id.btn_fechar);


        Intent intent = getIntent();
        movimentacao = (Movimentacao) intent.getSerializableExtra("movimentacao");

        if(movimentacao.getCodMovimentacao() != 0){

            txtPlaca.setText(movimentacao.getPlaca());
            txtModelo.setText(movimentacao.getModelo());
            txtHoraEntrada.setText(movimentacao.getHoraEntrada());
            txtHoraSaida.setText(movimentacao.getHoraSaida());
            txtTempo.setText(movimentacao.getTempo());
            txtValorPago.setText(movimentacao.getValorPago());

            switch (movimentacao.getTipo()){
                case "A":{
                    tipo = "Avulso";
                    break;
                }

                case "D":{
                    tipo = "Diarista";
                    break;
                }

                case "M":{
                    tipo = "Mensalista";
                    break;
                }

            }
            txtTipo.setText(tipo);

            txtPlaca.setEnabled(false);
            txtModelo.setEnabled(false);
            txtHoraEntrada.setEnabled(false);
            txtHoraSaida.setEnabled(false);
            txtTempo.setEnabled(false);
            txtValorPago.setEnabled(false);
            txtTipo.setEnabled(false);
        }

        // Ao clicar no botão Fecha a movimentação
        btnFecharMovimentacao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder caixa = new AlertDialog.Builder(CarregarMovimentacao.this);

                caixa.setTitle("Saída de Veículo");
                caixa.setMessage("Confirma a saída do veículo " + movimentacao.getPlaca() + " ?");

                caixa.setPositiveButton("SIM", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        FecharMovimentacao fecharMovimentacao = new FecharMovimentacao(movimentacao);

                        fecharMovimentacao.execute();

                        finish();

                        Toast.makeText(CarregarMovimentacao.this,"Saída concluída", Toast.LENGTH_LONG).show();
                    }
                });

                caixa.setNegativeButton("NÃO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                caixa.create().show();
            }
        });

    }
}
