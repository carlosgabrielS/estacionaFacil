package br.senai.sp.estacionamento;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.concurrent.ExecutionException;

import br.senai.sp.estacionamento.model.Endereco;
import br.senai.sp.estacionamento.model.Mensalista;
import br.senai.sp.estacionamento.tasks.CadastrarMensalista;
import br.senai.sp.estacionamento.tasks.CarregarEndereco;

public class CadastroMensalista extends AppCompatActivity {

    private LinearLayout linearLayout;
    private EditText txtCep;
    private EditText txtNumero;
    private EditText txtComplemento;
    private EditText txtLogradouro;
    private EditText txtBairro;
    private EditText txtCidade;
    private EditText txtUF;
    private EditText txtNome;
    private EditText txtCPF;
    private EditText txtEmail;
    private EditText txtValorMensal;
    private EditText txtTelefone;
    private Button btnCadastro;
    private Endereco endereco;
    private Mensalista mensalista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_mensalista);




        linearLayout = findViewById(R.id.lista_endereco);
        txtCep = findViewById(R.id.txt_cep_mensalista);
        txtNumero = findViewById(R.id.txt_numero_mensalista);
        txtComplemento = findViewById(R.id.txt_complemento_mensalista);
        txtCidade = findViewById(R.id.txt_cidade_mensalista);
        txtLogradouro = findViewById(R.id.txt_logradouro_mensalista);
        txtBairro = findViewById(R.id.txt_bairro_mensalista);
        txtNome = findViewById(R.id.txt_nome_mensalista);
        txtEmail = findViewById(R.id.txt_email_mensalista);
        txtCPF = findViewById(R.id.txt_cpf_mensalista);
        txtTelefone = findViewById(R.id.txt_telefone_mensalista);
        txtValorMensal = findViewById(R.id.txt_valor_mensal_mensalista);
        txtUF = findViewById(R.id.txt_uf_mensalista);
        btnCadastro = findViewById(R.id.btn_cadastro_mensalista);

        mensalista = new Mensalista();
        endereco = new Endereco();



        btnCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mensalista.setNome(txtNome.getText().toString());
                mensalista.setCpf(txtCPF.getText().toString());
                mensalista.setEmail(txtEmail.getText().toString());
                mensalista.setValorMensal(txtValorMensal.getText().toString());


                CadastrarMensalista cadastrarMensalista = new CadastrarMensalista(mensalista, CadastroMensalista.this);
                cadastrarMensalista.execute();
            }
        });

/*
        txtCep.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                txtCidade.setText("");
                txtLogradouro.setText("");
                txtBairro.setText("");
                txtUF.setText("");
                linearLayout.setVisibility(View.GONE);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                txtCidade.setText("");
                txtLogradouro.setText("");
                txtBairro.setText("");
                txtUF.setText("");
                linearLayout.setVisibility(View.GONE);
            }

            @Override
            public void afterTextChanged(Editable s) {
                endereco.setCep(txtCep.getText().toString());
                if(txtCep.length() == 8){

                    CarregarEndereco carregarEndereco = new CarregarEndereco(endereco, CadastroMensalista.this);
                    carregarEndereco.execute();

                    try {
                        Endereco endereco = (Endereco) carregarEndereco.get();

                        txtCidade.setText(endereco.getCidade());
                        txtLogradouro.setText(endereco.getLogradouro());
                        txtBairro.setText(endereco.getBairro());
                        txtUF.setText(endereco.getUf());
                        linearLayout.setVisibility(View.VISIBLE);
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }


                }
            }
        });
*/
    }

}
