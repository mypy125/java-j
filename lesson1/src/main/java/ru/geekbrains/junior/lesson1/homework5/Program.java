package ru.geekbrains.junior.lesson1.homework5;


import org.hibernate.Cache;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class Program {
    public static <bufferedReader> void main(String[] args) throws IOException {

        HashMap<String,String> client = new HashMap<>();
        client.put("gor","Yerevan");
        client.put("max","Moscow");
        client.put("doc","USA");

        BufferedReader  bufferedReader;
        bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String message = bufferedReader.readLine();
        if(message.startsWith("@")){
            System.out.println(searchNameFromMassageV2(message,client));
        }


    }

    private static Object equalsNameFromMap(String s, HashMap<?,?> clients){
        Object clientManager = null;
        if(s != null && clients.containsKey(s)){
            clientManager = clients.get(s);
        }
        return clientManager;
    }

    private static Object searchNameFromMassageV1(String message, HashMap<String, String> clients){
        Object client = null;
        StringBuilder sb = new StringBuilder();
        int counter = 1;
        for (int i = counter; i < message.length(); i++) {
            sb.append(message.charAt(i));
            client = equalsNameFromMap(sb.toString(), clients);
        }
        return client;
    }
    private static Object searchNameFromMassageV2(String message, HashMap<String, String> clients) {
        Object client = null;
        // Проверяем, что сообщение не пустое и начинается с символа '@'
        if (message != null && message.startsWith("@")) {
            // Извлекаем подстроку после символа '@' и разбиваем её на слова по пробелам
            String[] words = message.substring(1).split("\\s+");

            // Перебираем слова из сообщения
            for (String word : words) {
                // Если слово есть в списке клиентов, возвращаем соответствующего клиента
                if (clients.containsKey(word)) {
                    client = clients.get(word);
                    break; // Прерываем цикл, если найден клиент
                }
            }
        }
        return client;
    }


}
