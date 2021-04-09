package com.example.marvelstore.utils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.sql.Timestamp;
import java.text.DecimalFormat;

public class Pratice {
    //Converte de double para String com duas casas decimai
    public static String converterDoubleString(double precoDouble) {
        DecimalFormat fmt = new DecimalFormat("0.00");   //limita o número de casas decimais
        String price = fmt.format(precoDouble);
        return price;
    }

    //Pega o timestamp atual em millisegundos
    public static long getTimestamp(){
        return (new Timestamp(System.currentTimeMillis())).getTime();
    }

    //Gera uma hash md5 baseado no timestamp e nas chaves da API
    public static String getHash(String timestamp, String privateApiKey, String publicApiKey){
        String s = timestamp + privateApiKey + publicApiKey;
        MessageDigest m;

        try{
            m=MessageDigest.getInstance("MD5");
            m.update(s.getBytes(),0,s.length());
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
        return new BigInteger(1,m.digest()).toString(16);
    }
}
