package br.senai.sp.estacionamento.tasks;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import br.senai.sp.estacionamento.CadastroMensalista;
import br.senai.sp.estacionamento.R;
import br.senai.sp.estacionamento.model.Endereco;


public class CarregarEndereco extends AsyncTask {

    private Context context;
    private Endereco endereco;
    private String dados = "";
    private ProgressDialog progressDialog;

    public CarregarEndereco(Endereco endereco, Context context){
        this.context = context;
        this.endereco = endereco;
    }

    @Override
    protected Object doInBackground(Object[] objects) {

        try {
            URL url = new URL("https://viacep.com.br/ws/"+ endereco.getCep() +"/json");

            HttpURLConnection conexao = (HttpURLConnection) url.openConnection();

            InputStream dadosStream = conexao.getInputStream();

            InputStreamReader leitorStream = new InputStreamReader(dadosStream);

            BufferedReader bufferedReader = new BufferedReader(leitorStream);

            String registro = "";

            while (registro != null){
                registro = bufferedReader.readLine();
                dados = dados + registro;
            }


            try {
                JSONObject jsEndereco = new JSONObject(dados);

                endereco.setBairro(jsEndereco.getString("bairro"));
                endereco.setLogradouro(jsEndereco.getString("logradouro"));
                endereco.setCidade(jsEndereco.getString("localidade"));
                endereco.setUf(jsEndereco.getString("uf"));


            } catch (JSONException e) {
                e.printStackTrace();
            }


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return endereco;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog = new ProgressDialog(context);
        progressDialog.setTitle("Carregando dados");
        progressDialog.setMessage("Aguarde...");
        progressDialog.show();
    }

    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);

        progressDialog.dismiss();
    }
}
