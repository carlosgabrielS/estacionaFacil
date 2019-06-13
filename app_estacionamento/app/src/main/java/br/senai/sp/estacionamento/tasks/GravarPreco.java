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
import br.senai.sp.estacionamento.model.Preco;

public class GravarPreco extends AsyncTask{

    Preco preco;

    public GravarPreco(Preco preco){
        this.preco = preco;
    }

    @Override
    protected Object doInBackground(Object[] objects) {

        JSONStringer jsPreco = new JSONStringer();

        try {
            jsPreco.object();
            jsPreco.key("valorPrimeiraHora").value(preco.getPrimeiraHora());
            jsPreco.key("valorDemaisHoras").value(preco.getDemaisHoras());
            jsPreco.key("tolerancia").value(preco.getTolerancia());
            jsPreco.endObject();

            URL url = new URL("http://10.107.134.10:8080/precos");

            HttpURLConnection conexao = (HttpURLConnection) url.openConnection();

            conexao.setRequestProperty("Content-Type", "application/json");
            conexao.setRequestProperty("Accept", "application/json");
            conexao.setRequestMethod("POST");

            conexao.setDoInput(true);

            PrintStream output = new PrintStream(conexao.getOutputStream());
            output.print(jsPreco);

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
