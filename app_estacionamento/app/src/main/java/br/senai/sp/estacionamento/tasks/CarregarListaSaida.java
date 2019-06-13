package br.senai.sp.estacionamento.tasks;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.ArrayAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.senai.sp.estacionamento.ListaSaida;
import br.senai.sp.estacionamento.R;
import br.senai.sp.estacionamento.model.Movimentacao;

public class CarregarListaSaida extends AsyncTask{

    private String dados = "";
    private String tipo;
    private String data;
    private ProgressDialog progressDialog;
    private Context context;
    private ArrayAdapter<Movimentacao> adapter;
    private List<Movimentacao> movimentacoes;


    public CarregarListaSaida(Context context) {
        this.context = context;
    }

    @Override
    protected Object doInBackground(Object[] objects) {

        try {
            URL url = new URL("http://10.107.134.10:8080/movimentacoes/estacionados");

            HttpURLConnection conexao = (HttpURLConnection) url.openConnection();

            InputStream dadosStream = conexao.getInputStream();

            InputStreamReader leitorStream = new InputStreamReader(dadosStream);

            BufferedReader bufferedReader = new BufferedReader(leitorStream);

            String registro = "";

            while (registro != null){
                registro = bufferedReader.readLine();
                dados = dados + registro;
            }

            Movimentacao movimentacao;
            movimentacoes = new ArrayList<>();

            try {
                JSONArray dadosArray = new JSONArray(dados);

                for(int i = 0; i < dadosArray.length(); i++){
                    JSONObject jo = (JSONObject) dadosArray.get(i);

                    movimentacao = new Movimentacao();
                    movimentacao.setCodMovimentacao(jo.getInt("codMovimentacao"));
                    movimentacao.setPlaca(jo.getString("placa"));
                    movimentacao.setModelo(jo.getString("modelo"));
                    movimentacao.setHoraEntrada(jo.getString("horaEntrada"));
                    switch (jo.getString("tipo")){
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

                    movimentacao.setTipo(tipo);
                    movimentacoes.add(movimentacao);

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        progressDialog = new ProgressDialog(context);
        progressDialog.setTitle("Carregando dados");
        progressDialog.setMessage("Carregando ... Aguarde...");
        progressDialog.show();
    }

    //ESTE MÉTODO É O QUARTO A SER EXECUTADO
    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);

        adapter = new ArrayAdapter<Movimentacao>(context, android.R.layout.simple_list_item_1, movimentacoes);
        ListaSaida.listViewSaida.setAdapter(adapter);

        progressDialog.dismiss();
    }
}
