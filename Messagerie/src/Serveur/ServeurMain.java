/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Serveur;

import java.io.IOException;
/**
* start a server. Reads the server's port from the command line argument
* @author Remi Watrigant
*
*/
public class ServeurMain {
/**
* creates a new server
* @param args
*/
    public static void main(String[] args) throws IOException {
        if (args.length != 1) {
            printUsage();
        } else {
            Integer port = new Integer(args[0]);
            Server server = new Server(port);
        }
    }

    private static void printUsage() {
        System.out.println("java server.Server <port>");
        System.out.println("\t<port>: server's port");
    }
}