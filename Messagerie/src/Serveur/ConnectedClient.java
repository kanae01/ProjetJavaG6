/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Serveur;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author thoma
 */
public class ConnectedClient implements Runnable {
    
    private static int idCounter = 0;
    private int id;
    private Server server;
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;

    ConnectedClient(Server server, Socket sockNewClient) throws IOException {
        this.server = server;
        this.socket = sockNewClient;
        id = idCounter++;
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream());
        System.out.println("Nouvelle connexion, id = " + id);
    }

    @Override
    public void run() {
        
        String message = null;
        boolean isActive = true;
        
        while (isActive) {
            try {
                message = in.readLine();
            } catch (IOException ex) {
                Logger.getLogger(ConnectedClient.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            if(message == null){
                server.disconnectedClient(this);
                isActive = false;
            }
            else{
                server.broadcastMessage(message, id);
            }
        }
    }
    
    public void sendMessage(String m){
        this.out.println(m);
        this.out.flush();
    }
    
    public void closeClient() throws IOException{
        this.in.close();
        this.out.close();
        this.socket.close();
    }

    public int getId() {
        return id;
    }
}
