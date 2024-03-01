package ru.geekbrains.junior.lesson1;


import org.hibernate.Cache;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class Program {
    public static <bufferedReader> void main(String[] args) throws IOException {

        HashMap<String,String> client = new HashMap<>();
        client.put("gor","erevan");
        client.put("max","moscow");
        client.put("doc","usa");

        BufferedReader  bufferedReader;
        bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String message = bufferedReader.readLine();
        if(message.startsWith("@")){
            System.out.println(searchNameFromMassage(message,client));
        }


    }

    private static Object equalsNameFromMap(String s, HashMap<?,?> clients){
        Object clientManager = null;
        if(s != null && clients.containsKey(s)){
            clientManager = clients.get(s);
        }
        return clientManager;
    }

    private static Object searchNameFromMassage(String message, HashMap<String, String> clients){
        Object client = null;
        StringBuilder sb = new StringBuilder();
        int counter = 1;
        for (int i = counter; i < message.length(); i++) {
            sb.append(message.charAt(i));
            client = equalsNameFromMap(sb.toString(), clients);
        }
        return client;
    }



}
