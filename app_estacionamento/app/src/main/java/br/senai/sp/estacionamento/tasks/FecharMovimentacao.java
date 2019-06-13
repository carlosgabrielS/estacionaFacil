package br.senai.sp.estacionamento.tasks;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

import java.io.IOException;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Scanner;

import br.senai.sp.estacionamento.CarregarMovimentacao;
import br.senai.sp.estacionamento.R;
import br.senai.sp.estacionamento.model.Movimentacao;

public class FecharMovimentacao extends AsyncTask{

    Movimentacao movimentacao;


    //Método Construtor
    public FecharMovimentacao(Movimentacao movimentacao){
        this.movimentacao = movimentacao;
    }

    @Override
    protected Object doInBackground(Object[] objects) {

        JSONStringer jsFecharMovimentacao = new JSONStringer();

        try {
            jsFecharMovimentacao.object();
            jsFecharMovimentacao.key("placa").value(movimentacao.getPlaca());
            jsFecharMovimentacao.endObject();

            URL url = new URL("http://10.107.134.10:8080/movimentacoes/" + movimentacao.getPlaca());

            HttpURLConnection conexao = (HttpURLConnection) url.openConnection();

            conexao.setRequestProperty("Content-Type", "application/json");
            conexao.setRequestProperty("Accept", "application/json");
            conexao.setRequestMethod("PUT");

            conexao.setDoInput(true);

            PrintStream output = new PrintStream(conexao.getOutputStream());
            output.print(jsFecharMovimentacao);

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

//    @Override
//    protected void onPostExecute(Object o) {
//        super.onPostExecute(o);
//    }
}
