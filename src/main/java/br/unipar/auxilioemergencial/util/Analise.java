package br.unipar.auxilioemergencial.util;

import android.annotation.SuppressLint;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Analise {


    public static ArrayList<String> retornaResulado(String dataNasc, Double rendaMensal) throws ParseException {
        ArrayList<String> resultado = new ArrayList<String>();

        @SuppressLint("SimpleDateFormat") SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date date = sdf.parse(dataNasc);

        Calendar dtNasc = Calendar.getInstance();
        Calendar dtAtual = Calendar.getInstance();
        dtNasc.setTime(date);
        dtAtual.setTime(new Date());
        dtAtual.add(Calendar.DATE, 20);
        long nasc = dtNasc.getTimeInMillis();
        long atual = dtAtual.getTimeInMillis();

        if ((atual - nasc) <= 567648000000L){
            resultado.add(0, "Negado");
            resultado.add(1, "Motivo: Idade abaixo do permitido");
            resultado.add(2,"");
            return resultado;
        }

        if(rendaMensal > 5000.00){
            resultado.add(0, "Negado");
            resultado.add(1, "Motivo: Renda acima do permitido");
            resultado.add(2,"");
            return resultado;
        }

        Date dataAtual = new Date(String.valueOf(dtAtual.getTime()));

        int anoAtual = dataAtual.getYear() + 1900;
        int mesAtual = dataAtual.getMonth();
        int diaAtual = dataAtual.getDate();

        int mesNasc = date.getMonth();

        if(mesNasc <= mesAtual){
            anoAtual += 1;
        }

        Double valorTotal = rendaMensal * 0.7;
        DecimalFormat dec = new DecimalFormat("##0.00");

        if(valorTotal > 475.00){
            String mes = String.valueOf(mesAtual).length() > 1 ? String.valueOf(mesAtual) : "0"+String.valueOf(mesAtual);
            resultado.add(0, "Aprovado");
            resultado.add(1, "Data de pagamento: " + diaAtual + "/" + mes + "/" + anoAtual);
            resultado.add(2,"Valor: R$ 475,00");

            return resultado;
        }

        resultado.add(0, "Aprovado");
        resultado.add(1, "Data de pagamento: " + diaAtual + "/" + mesAtual + "/" + anoAtual);
        resultado.add(2,"Valor: R$ " + dec.format(valorTotal));

        return resultado;
    }

}
