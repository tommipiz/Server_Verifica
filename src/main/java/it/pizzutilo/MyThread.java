package it.pizzutilo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class MyThread extends Thread{
    private static List<String> notes = new ArrayList<>();
    private static List<String> shareNotes = new ArrayList<>();
    private Socket s;
    private String username;

    public MyThread(Socket s) {
        this.s = s;
    }

    public void run() {
        try( BufferedReader in =new BufferedReader(new InputStreamReader(s.getInputStream()));
            PrintWriter out = new PrintWriter(s.getOutputStream(),true)) {

            //System.out.println("Inserisci il tuo username: ");
            //username = in.readLine();
            
            String message;
            while((message = in.readLine()) != null) {
                synchronized (notes) {
                    if (message.equals("!")) {
                        System.out.println("Il client si è disconnesso");
                        break;
                    } else if (message.equals("?")){
                        for (String note : notes) {
                            System.out.println(note);
                        }
                        System.out.println("@");
                    } else {
                        notes.add(message);
                        System.out.println("OK");
                        System.out.println("Nota ricevuta " + message);
                    }
                }
            }
         } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                s.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

