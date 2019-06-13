package br.senai.sp.api.conversao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DataBanco {

	public static String getFormatoBanco(String formato) {
		
		Date hoje = new Date();
		
		SimpleDateFormat df = new SimpleDateFormat(formato);
		
		return df.format(hoje);
	}

}
