/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author thoma
 */
public class ClientReceive implements Runnable{
    
    private Client client;
    private BufferedReader in;
    
    public ClientReceive(Client c, BufferedReader in){
        client = c;
        this.in = in;
    }

    @Override
    public void run() {
        boolean isActive = true ;
        while(isActive) {
            String message = null;
            try {
                message = in.readLine();
            } catch (IOException ex) {
                Logger.getLogger(ClientReceive.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (message != null) {
                System.out.println("\nMessage reçu : " + message);
            } else {
                isActive = false;
            }
        }
        try {
            client.disconnectedServer();
        } catch (IOException ex) {
            Logger.getLogger(ClientReceive.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
