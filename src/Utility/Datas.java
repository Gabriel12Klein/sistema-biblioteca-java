package Utility;

import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author Gabriel
 */




//Classe utilitária para evitar as excessoes da data
public class Datas {
     // Monta Date sem try/catch: dia, mes, ano (mes 1..12)
    public static Date montarData(int dia, int mes, int ano) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, ano);
        c.set(Calendar.MONTH, mes - 1);
        c.set(Calendar.DAY_OF_MONTH, dia);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        return c.getTime();
    }

    public static Date truncDia(Date d) {
        Calendar c = Calendar.getInstance();
        c.setTime(d);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        return c.getTime();
    }

    public static String formatar(Date d) {
        Calendar c = Calendar.getInstance();
        c.setTime(d);
        int dia = c.get(Calendar.DAY_OF_MONTH);
        int mes = c.get(Calendar.MONTH) + 1;
        int ano = c.get(Calendar.YEAR);
        String sd = (dia < 10 ? "0" + dia : "" + dia);
        String sm = (mes < 10 ? "0" + mes : "" + mes);
        return sd + "/" + sm + "/" + ano;
    }
}
