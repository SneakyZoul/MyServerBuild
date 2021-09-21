package com.company;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CopyOnWriteArrayList;

public class Dispatcher extends Thread
{
    CopyOnWriteArrayList<ClientHandler> clients;
    BlockingQueue<String> messages;

    public Dispatcher(BlockingQueue<String> queue)
    {
        this.messages = queue;
    }

    public Dispatcher(BlockingQueue<String> queue, CopyOnWriteArrayList<ClientHandler> clients)
    {
        this.messages = queue;
        this.clients = clients;
    }

    @Override
    public void run()
    {
        try
        {
            String outmsg = "";
            while (true)
            {
                outmsg = messages.take();

                for (ClientHandler cl : clients)
                {
                    cl.getPw().println(outmsg);
                }
            }

        } catch (InterruptedException ie)
        {
            ie.printStackTrace();
        }

    }
}
