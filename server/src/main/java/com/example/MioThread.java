package com.example;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class MioThread extends Thread {
    Socket s;

    public MioThread(Socket s) {
        this.s = s;
    }

    public void run() {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            DataOutputStream out = new DataOutputStream(s.getOutputStream());
            do {
                String stringaRicevuta = in.readLine();
                if (stringaRicevuta.equals("!")) {
                    System.out.println("Il client vuole chiudere");
                    s.close();
                    break;
                }
                System.out.println("Stringa ricevuta sul thread" + Thread.currentThread().getName() + ": " + stringaRicevuta);

                String stringaTrasformata = stringaRicevuta.toUpperCase();
                out.writeBytes(stringaTrasformata + "\n");
            } while (true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
