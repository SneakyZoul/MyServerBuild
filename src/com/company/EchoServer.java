package com.company;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EchoServer
{
    private int port;

    public EchoServer(int port)
    {
        this.port = port;
    }

    public void statServer() throws IOException
    {
        //TODO lav eb messag qque
        //TODO lav en liste til vlienthandler


        //TODO Instansiere dispatchere med de delte resursor
        ServerSocket serverSocket = new ServerSocket(port);
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        Socket client = serverSocket.accept();
        ClientHandler cl = new ClientHandler(client);
        //TODO pu cl i listen
        //cl.protocol();
        executorService.execute(cl);

    }
}
