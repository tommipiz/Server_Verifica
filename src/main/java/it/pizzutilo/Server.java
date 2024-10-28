package it.pizzutilo;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws IOException{
        try(ServerSocket ss = new ServerSocket(3000);) {

        
        while(true) {
            Socket s = ss.accept();
            System.out.println("Nuova connessione ricevuta");
            MyThread t = new MyThread(s);
            t.start();
        }
    } catch (IOException e) {
            e.printStackTrace();
        }
    }   
}
