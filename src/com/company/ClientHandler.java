package com.company;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Locale;
import java.util.Scanner;
import java.util.concurrent.BlockingQueue;

public class ClientHandler implements Runnable
{

    Dispatcher dispatcher;
    BlockingQueue<String> queue;
    Socket client;
    PrintWriter pw;
    Scanner scanner;

    public ClientHandler(Socket client) throws IOException
    {


        this.client = client;
        this.pw = new PrintWriter(client.getOutputStream(), true);
        this.scanner = new Scanner(client.getInputStream());
    }

    public ClientHandler(Socket client, BlockingQueue<String> queue) throws IOException
    {
        this.queue = queue;
        this.client = client;
        this.pw = new PrintWriter(client.getOutputStream(), true);
        this.scanner = new Scanner(client.getInputStream());

    }
    //TODO lav ny constructor med den delte besked-kø

    public void protocol() throws IOException, InterruptedException
    {
        String msg = "";
        String action;
        pw.println("davs");

        while (!msg.equals("CLOSE#"))
        {
            //TODO lav cl md delt ressource
            msg = scanner.nextLine();
            String[] parts = msg.split("#");
            action = parts[0];
            msg = parts[1];


            switch (action)
            {
                case "ALL":
                    queue.put(msg);
                    //TODO inset besked i delt resusor
                    break;
                case "UPPER":
                    pw.println(msg.toUpperCase());
                    break;
                case "LOWER":
                    pw.println(msg.toLowerCase());
                    break;
                case "REVERS":
                    StringBuilder st = new StringBuilder();
                    st.append(msg);
                    st.reverse();
                    pw.println(st);
                    break;
            }

        }
        client.close();
    }

    @Override
    public void run()
    {
        try
        {
            while (true)
            {
                this.protocol();
            }

        } catch (InterruptedException | IOException e)
        {
            e.printStackTrace();
        }
    }

    public PrintWriter getPw()
    {
        return pw;
    }
}
