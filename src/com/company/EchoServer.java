package com.company;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.*;

public class EchoServer
{
    Dispatcher dispatcher;
    private int port;

    public EchoServer(int port)
    {
        this.port = port;
    }

    public void statServer() throws IOException
    {
        CopyOnWriteArrayList<ClientHandler> clients = new CopyOnWriteArrayList<>();
        ArrayBlockingQueue<String> queue = new ArrayBlockingQueue<String>(10);
        Dispatcher dp = new Dispatcher(queue, clients);
        dp.start();

        //TODO lav en message que

        //TODO lav en liste til clienthandler


        //TODO Instansiere dispatchere med de delte resursor
        ServerSocket serverSocket = new ServerSocket(port);
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        while (true)
        {

            Socket client = serverSocket.accept();
            ClientHandler cl = new ClientHandler(client, queue);
            clients.add(cl);
            //ClientHandler cl = new ClientHandler(client);
            //TODO put cl i listen
            //cl.protocol();
            executorService.execute(cl);
        }


    }
}
