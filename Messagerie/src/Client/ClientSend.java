/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author thoma
 */
public class ClientSend implements Runnable{
    
    private PrintWriter out;
    private String msg;
    
    public ClientSend(PrintWriter pw){
        out = pw;
    }
    
    public void setMsg(String m){
        msg = m;
    }

    @Override
    public void run() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            // System.out.print("Votre message >> ");
            String m = msg;
            out.println(m);
            out.flush();
        }
    }
     
}
