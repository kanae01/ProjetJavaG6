/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Serveur;

import java.io.IOException;
import java.net.SocketImpl;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author thoma
 */
public class Server {
    
    private int port;
    private List<ConnectedClient> clients;
            
    public Server(Integer port) throws IOException {
        this.port = port;
        this.clients = new ArrayList<ConnectedClient>();
        
        Thread threadConnection = new Thread(new Connection(this));
        threadConnection.start();
    }

    public int getPort() {
        return port;
    }

    void addClient(ConnectedClient newClient) {
        for (ConnectedClient client : clients) {
            client.sendMessage("Le client "+newClient.getId()+" vient de se connecter");
        }
        this.clients.add(newClient);
    }

    void broadcastMessage(String message, int id) {
        for (ConnectedClient client : clients) {
            if (client.getId() != id) {
                client.sendMessage("Message de " + id + " : " + message);
            }
        }
    }

    void disconnectedClient(ConnectedClient discClient) {
        for (ConnectedClient client : clients) {
            client.sendMessage("Le client " + discClient.getId()+" nous a quitté");
        }
    }
    
}
