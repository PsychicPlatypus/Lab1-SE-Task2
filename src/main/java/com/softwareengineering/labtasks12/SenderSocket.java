package com.softwareengineering.labtasks12;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class SenderSocket {
    private final String server;
    private final Integer port;

    public SenderSocket(String server, Integer port) {
        this.server = server;
        this.port = port;
    }

    public static void main(String[] args) throws UnknownHostException {
        String server = InetAddress.getLocalHost().getHostAddress();
        SenderSocket echoClient = new SenderSocket(server, 2345);
        echoClient.establish();
    }

    public void establish() {
        try {
            Socket echoSocket = new Socket(this.server, this.port);
            ObjectOutputStream out = new ObjectOutputStream(echoSocket.getOutputStream());
            Component component = new Component();
            out.writeObject(component);

            System.out.println("Sent Component object to " + this.server);
            out.close();
            echoSocket.close();
        } catch (UnknownHostException uhe) {
            System.err.println("Cannot find server...");
            System.exit(-1);
        } catch (IOException ioe) {
            System.err.println("Failed");
            System.exit(-1);
        }
    }
}