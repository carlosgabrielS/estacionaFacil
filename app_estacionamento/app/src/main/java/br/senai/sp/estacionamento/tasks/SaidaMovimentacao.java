package br.senai.sp.estacionamento.tasks;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

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
import java.util.Date;

import br.senai.sp.estacionamento.CarregarMovimentacao;
import br.senai.sp.estacionamento.R;
import br.senai.sp.estacionamento.model.Movimentacao;

public class SaidaMovimentacao extends AsyncTask{

    private String dados = "";
    private Double valor;
    private String hora;
    private String dataEntrada;
    private String dataSaida;
    private Movimentacao movimentacao;
    private Context context;

    public SaidaMovimentacao(Movimentacao movimentacao, Context context) {
        this.context = context;
        this.movimentacao = movimentacao;
    }

    @Override
    protected Object doInBackground(Object[] objects) {

        try {
            URL url = new URL("http://10.107.134.10:8080/movimentacoes/placa/" + movimentacao.getPlaca());

            HttpURLConnection conexao = (HttpURLConnection) url.openConnection();

            InputStream dadosStream = conexao.getInputStream();

            InputStreamReader leitorStream = new InputStreamReader(dadosStream);

            BufferedReader bufferedReader = new BufferedReader(leitorStream);

            String registro = "";

            while (registro != null){
                registro = bufferedReader.readLine();
                dados = dados + registro;
            }

            JSONObject jsMovimentacao = new JSONObject(dados);

            try {


                movimentacao.setCodMovimentacao(jsMovimentacao.getInt("codMovimentacao"));
                movimentacao.setPlaca(jsMovimentacao.getString("placa"));
                movimentacao.setModelo(jsMovimentacao.getString("modelo"));
                try {
                    dataEntrada = jsMovimentacao.getString("horaEntrada");
                    dataSaida = jsMovimentacao.getString("horaSaida");

                    SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    Date dateE;
                    Date dateS;
                    dateE = fmt.parse(dataEntrada);
                    SimpleDateFormat fmtInp = new SimpleDateFormat("HH:mm:ss || dd/MM/yyyy");

                    dateS = fmt.parse(dataSaida);
                    SimpleDateFormat fmtOut = new SimpleDateFormat("HH:mm:ss || dd/MM/yyyy");

                    movimentacao.setHoraEntrada(fmtInp.format(dateE));
                    movimentacao.setHoraSaida(fmtOut.format(dateS));
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                if(Double.valueOf(jsMovimentacao.getInt("tempo")) > 1){
                    hora = "Horas";
                }else{
                    hora = "Hora";
                }
                movimentacao.setTempo(String.valueOf(jsMovimentacao.getInt("tempo")+ " " + hora));

                valor = Double.valueOf(jsMovimentacao.getInt("valorPago"));

                movimentacao.setValorPago("R$ " + String.format(String.format("%.2f",valor)));
                movimentacao.setTipo(jsMovimentacao.getString("tipo"));

            } catch (JSONException e) {
                e.printStackTrace();
        }

    } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return movimentacao;
    }


    @Override
    protected void onPostExecute(Object o) {

        super.onPostExecute(o);
        Intent carregarDados = new Intent(context, CarregarMovimentacao.class);
        carregarDados.putExtra("movimentacao", movimentacao);
        context.startActivity(carregarDados);

    }


}
