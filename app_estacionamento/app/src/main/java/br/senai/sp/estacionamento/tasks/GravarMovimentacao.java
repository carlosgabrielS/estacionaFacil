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

import br.senai.sp.estacionamento.R;
import br.senai.sp.estacionamento.model.Movimentacao;

public class GravarMovimentacao extends AsyncTask {

    Movimentacao movimentacao;

    public GravarMovimentacao(Movimentacao movimentacao){ //MEDTODO CONSTRUTOR
        this.movimentacao = movimentacao;
    }

    @Override
    protected Object doInBackground(Object[] objects) {

        JSONStringer jsMovimentacao = new JSONStringer();

        try {
            jsMovimentacao.object(); //ABRE O JSON
            jsMovimentacao.key("placa").value(movimentacao.getPlaca());
            jsMovimentacao.key("modelo").value(movimentacao.getModelo());
            jsMovimentacao.key("tipo").value(movimentacao.getTipo());
            jsMovimentacao.endObject();

            URL url = new URL("http://10.107.134.10:8080/movimentacoes");

            HttpURLConnection conexao = (HttpURLConnection) url.openConnection();

            conexao.setRequestProperty("Content-Type", "application/json");
            conexao.setRequestProperty("Accept", "application/json");
            conexao.setRequestMethod("POST");

            conexao.setDoInput(true);

            PrintStream output = new PrintStream(conexao.getOutputStream());
            output.print(jsMovimentacao);

            conexao.connect();

            Scanner scanner = new Scanner(conexao.getInputStream());
            String resposta = scanner.nextLine();

        } catch (JSONException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return null;
    }
}
