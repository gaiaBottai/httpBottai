package com.example;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class MioThread extends Thread {
    Socket s;
    public MioThread(Socket s){
        this.s=s;
    }
    public void run(){
        
        try {
            BufferedReader in =new BufferedReader(new InputStreamReader(s.getInputStream()));
            DataOutputStream out= new DataOutputStream(s.getOutputStream());
            String risposta=in.readLine();
            if(risposta.split(" ")[0].equals("GET")){
            do{
                    String linea=in.readLine();
                    if(linea.equals("")){
                        String bodyRequest="<html><body>pagina non trovata</body></html>";
                        out.writeBytes("HTTP/1.1 404 Not Found \r\n");
                        out.writeBytes("Content-Length:"+bodyRequest.length()+"\r\n");
                        out.writeBytes("Content-type:text/html \r\n");
                        out.writeBytes("\r\n");
                        out.writeBytes(bodyRequest);
                    }else{
                        System.out.println(linea);
                    }
            }while(true);
        }
        } catch (IOException e) {
           
            e.printStackTrace();
        }
        
    }
}
