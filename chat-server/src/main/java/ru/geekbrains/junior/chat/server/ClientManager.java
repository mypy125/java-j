package ru.geekbrains.junior.chat.server;

import java.io.*;
import java.net.Socket;
import java.util.HashMap;

public class ClientManager implements Runnable {

    private Socket socket;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    private String name;
    public static HashMap<String,ClientManager> clients = new HashMap<>();

    public ClientManager(Socket socket) {
        try {
            this.socket = socket;
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            //TODO: ...
            name = bufferedReader.readLine();
            clients.put(name,this);
            System.out.println(name + " подключился к чату.");
            broadcastMessage("Server: " + name + " подключился к чату.");
        }
        catch (Exception e){
            closeEverything(socket, bufferedReader, bufferedWriter);
        }
    }

    private void closeEverything(Socket socket, BufferedReader bufferedReader, BufferedWriter bufferedWriter) {
        // Удаление клиента из коллекции
        removeClient();
        try {
            // Завершаем работу буфера на чтение данных
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            // Завершаем работу буфера для записи данных
            if (bufferedWriter != null) {
                bufferedWriter.close();
            }
            // Закрытие соединения с клиентским сокетом
            if (socket != null) {
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Удаление клиента из коллекции
     */
    private void removeClient() {
        clients.remove(name,this);
        System.out.println(name + " покинул чат.");
        broadcastMessage("Server: " + name + " покинул чат.");
    }

    /**
     * Отправка сообщения всем слушателям
     *
     * @param message сообщение
     */
    private void broadcastMessage(String message) {
        for (ClientManager client : clients.values()) {
            try {
                if (!client.equals(this) && message != null){
//                if (!client.name.equals(name) && message != null) {
                    client.bufferedWriter.write(message);
                    client.bufferedWriter.newLine();
                    client.bufferedWriter.flush();
                }
            } catch (Exception e) {
                closeEverything(socket, bufferedReader, bufferedWriter);
            }
        }
    }

    @Override
    public void run() {
        String massageFromClient;
        while (!socket.isClosed()) {
            try {
                // Чтение данных
                massageFromClient = bufferedReader.readLine();
                if(massageFromClient.startsWith("@",5)){
                    ClientManager client = (ClientManager) searchNameFromMassage(massageFromClient,clients);
                    if(client != null)privateMessageClient(client,massageFromClient);

                }else {
                    // Отправка данных всем слушателям
                    broadcastMessage(massageFromClient);
                }


            }
            catch (Exception e){
                closeEverything(socket, bufferedReader, bufferedWriter);
                //break;
            }
        }
    }



    private void privateMessageClient(ClientManager client, String message){
            ClientManager privatMassage = client;
        try {
            privatMassage.bufferedWriter.write(message);
            privatMassage.bufferedWriter.newLine();
            privatMassage.bufferedWriter.flush();

        } catch (Exception e) {
            closeEverything(socket, bufferedReader, bufferedWriter);
        }

    }
    private Object searchNameFromMassage(String message, HashMap<String, ClientManager> clients){
        Object client = null;
        StringBuilder sb = new StringBuilder();
        int counter = 6;
        for (int i = counter; i < message.length(); i++) {
            sb.append(message.charAt(i));
            client = equalsNameFromMap(sb.toString(), clients);
        }
        return client;
    }


    private Object equalsNameFromMap(String s, HashMap<String, ClientManager> clients){
        Object clientManager = null;
        if(s != null && clients.containsKey(s)){
            clientManager = clients.get(s);
        }
        return clientManager;
    }

    public String getName() {
        return name;
    }
}
