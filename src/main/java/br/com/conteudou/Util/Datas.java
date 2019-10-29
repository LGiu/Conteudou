package br.com.conteudou.Util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Datas {

    public static Date stringToDate(Date data, String formato) {
        try {
            String date = new SimpleDateFormat(formato).format(data);
            return new SimpleDateFormat(formato).parse(date);
        } catch (Exception e) {
            return null;
        }
    }

}