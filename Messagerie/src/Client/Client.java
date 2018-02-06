/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client;

import Serveur.Connection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 *
 * @author thoma
 */
public class Client {
    
    private String address;
    private int port;
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;
    private ClientSend clientS;
    private ClientReceive clientR;

    public Client(String ip, int port) throws IOException{
        address = ip;
        this.port = port;
        socket = new Socket(ip, port);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream());

        clientS = new ClientSend(out);
        clientR = new ClientReceive(this, in);
        
        Thread threadClientSend = new Thread(clientS);
        threadClientSend.start();
        Thread threadClientReceive = new Thread(clientR);
        threadClientReceive.start();
    }

    public void send(String msg){
        clientS.setMsg(msg);
    }
    
    public String receive(){
        return clientR.getMsg();
    }
    
    public void disconnectedServer() throws IOException{
       in.close();
       out.close();
       socket.close();
       System.exit(0);
    }
}
