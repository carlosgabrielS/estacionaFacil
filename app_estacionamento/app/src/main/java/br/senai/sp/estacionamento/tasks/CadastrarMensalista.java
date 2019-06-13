package br.senai.sp.estacionamento.tasks;

import android.content.Context;
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

import br.senai.sp.estacionamento.model.Mensalista;

public class CadastrarMensalista extends AsyncTask {

    private Context context;
    private Mensalista mensalista;

    public CadastrarMensalista (Mensalista mensalista, Context context){
        this.mensalista = mensalista;
        this.context = context;
    }

    @Override
    protected Object doInBackground(Object[] objects) {
        JSONStringer jsMensalista = new JSONStringer();

        try {
            jsMensalista.object(); //ABRE O JSON
            jsMensalista.key("nome").value(mensalista.getNome());
            jsMensalista.key("email").value(mensalista.getEmail());
            jsMensalista.key("cpf").value(mensalista.getCpf());
            jsMensalista.key("valorMensal").value(mensalista.getValorMensal());
            jsMensalista.endObject();

            URL url = new URL("http://10.107.134.10:8080/mensalistas");

            HttpURLConnection conexao = (HttpURLConnection) url.openConnection();

            conexao.setRequestProperty("Content-Type", "application/json");
            conexao.setRequestProperty("Accept", "application/json");
            conexao.setRequestMethod("POST");

            conexao.setDoInput(true);

            PrintStream output = new PrintStream(conexao.getOutputStream());
            output.print(jsMensalista);

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
