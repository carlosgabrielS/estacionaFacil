package br.senai.sp.estacionamento.tasks;

import android.os.AsyncTask;

import org.json.JSONException;
import org.json.JSONStringer;

import java.io.IOException;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Scanner;

import br.senai.sp.estacionamento.model.Endereco;

public class GravarEndereco extends AsyncTask {

    Endereco endereco;

    public GravarEndereco(Endereco endereco){
        this.endereco = endereco;
    }

    @Override
    protected Object doInBackground(Object[] objects) {

        JSONStringer jsEndereco = new JSONStringer();

        try {
            jsEndereco.object();
            jsEndereco.key("cep").value(endereco.getCep());
            jsEndereco.key("complemento").value(endereco.getComplemento());
            jsEndereco.key("logradouro").value(endereco.getLogradouro());
            jsEndereco.key("numero").value(endereco.getNumero());
            jsEndereco.key("bairro").value(endereco.getBairro());
            jsEndereco.key("cidade").value(endereco.getCidade());
            jsEndereco.key("uf").value(endereco.getUf());
            jsEndereco.endObject();

            URL url = new URL("http://10.107.134.10:8080/enderecos");

            HttpURLConnection conexao = (HttpURLConnection) url.openConnection();
            conexao.setRequestProperty("Content-Type", "application/json");
            conexao.setRequestProperty("Accept", "application/json");
            conexao.setRequestMethod("POST");

            conexao.setDoInput(true);

            PrintStream output = new PrintStream(conexao.getOutputStream());
            output.print(jsEndereco);

            conexao.connect();

            Scanner scanner = new Scanner(conexao.getInputStream());
            String resposta = scanner.nextLine();


        } catch (JSONException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return null;
    }
}
