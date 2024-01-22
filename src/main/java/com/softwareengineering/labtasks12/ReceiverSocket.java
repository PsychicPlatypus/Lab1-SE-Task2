package com.softwareengineering.labtasks12;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class ReceiverSocket extends Application {
    private Parent parent;

    private static ServerSocket setupServerSocket() throws IOException {
        System.out.println("Current server running: " + InetAddress.getLocalHost().getHostAddress());
        int port = 2345;
        ServerSocket serverSocket = new ServerSocket(port);

        InetAddress aInetAddress = serverSocket.getInetAddress();
        int portInteger = serverSocket.getLocalPort();

        String output = String.format("%s:%s", aInetAddress.getHostAddress(), portInteger);
        System.out.println("Server started at: " + output);
        return serverSocket;
    }

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws IOException {
        ServerSocket serverSocket = setupServerSocket();

        boolean run = true;
        while (run) {
            run = clientSocketHandled(serverSocket, run);
        }

        Scene scene = new Scene(this.parent, 320, 240);
        stage.setTitle("Receiver");
        stage.setScene(scene);
        stage.show();
    }

    private boolean clientSocketHandled(ServerSocket serverSocket, boolean run) throws IOException {
        Socket clientSocket;
        try {
            clientSocket = serverSocket.accept();
            ObjectInputStream in;

            in = new ObjectInputStream(clientSocket.getInputStream());
            Object recieved;

            while ((recieved = in.readObject()) != null) {
                if (recieved instanceof Component component) {
                    this.parent = component.getComponent();
                    run = false;
                    break;
                } else {
                    System.out.println("Received non component Object");
                }
            }
        } catch (IOException e) {
            System.out.println("Accept failed:2345");
            serverSocket.close();
            System.exit(-1);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return run;
    }
}