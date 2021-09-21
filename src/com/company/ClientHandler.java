package com.company;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Locale;
import java.util.Scanner;

public class ClientHandler
{
    Socket client;
    PrintWriter pw;
    Scanner scanner;

    public ClientHandler(Socket client) throws IOException
    {
        this.client = client;
        this.pw = new PrintWriter(client.getOutputStream(), true);
        this.scanner = new Scanner(client.getInputStream());
    }

    public void protocol() throws IOException
    {
        String msg = "";
        String action = "";
        pw.println("davs");

        while (!msg.equals("CLOSE#"))
        {
            msg = scanner.nextLine();
            String[] parts = msg.split("#");
            action = parts[0];
            msg = parts[1];
            //TODO: split strengene på #
            //TODO: switch på første del og processe anden del (data).

            switch (action)
            {
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
}
