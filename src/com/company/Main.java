package com.company;

import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        EchoServer echoServer = new EchoServer(8285);
        try
        {
echoServer.statServer();
        }catch (IOException io){
            io.printStackTrace();


        }
    }
}
