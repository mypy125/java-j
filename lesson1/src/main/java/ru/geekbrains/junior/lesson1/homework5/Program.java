package ru.geekbrains.junior.lesson1.homework5;


import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.concurrent.*;

public class Program {
    public static final int Threads = 100;
    public static final int TIME_O = 100;
    public static final int MIN_P = 0;
    public static final int MAX_P = 65535;

    public static void main(String[] args) {

        sc("www.maxecommerce.com");

    }


    public static void sc(String hs){
        System.out.println("scan p:");
        ExecutorService exec = Executors.newFixedThreadPool(Threads);
        for(int p = MIN_P; p < MAX_P; p++){
            var soc = new InetSocketAddress(hs,p);
            int finalP = p;
            exec.execute(()->{
              var address = new InetSocketAddress(hs, finalP);
                try(var sck = new Socket()){
                    sck.connect(address,TIME_O);
                    System.out.printf("hst: %s, p %d is opened\n",hs,finalP);
                }catch (IOException ignored){

                }
            });
        }
        exec.shutdown();
        try {
            exec.awaitTermination(10,TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("finish");

    }



}
