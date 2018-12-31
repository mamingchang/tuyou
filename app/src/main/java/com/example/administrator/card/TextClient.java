package com.example.administrator.card;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

public class TextClient {
    public String text = null;
    public void main(String[] args){
        this.connection();
    }
	public void connection() {
		// TODO Auto-generated method stub
		Socket socket = null;

        InputStream is = null;

        OutputStream os = null;
        String serverIP = "172.26.15.13";
        int port = 10000;
        String data = "Hello";

        try {
                 socket = new Socket(serverIP,port);
                 os = socket.getOutputStream();
                 ObjectOutputStream oos  = new ObjectOutputStream(os);
                 os.write(data.getBytes());
                 is = socket.getInputStream();
                 ObjectInputStream ois = new ObjectInputStream(is);
                 byte[] b = new byte[1024];
                 //DataOperation dop = new DataOperation();
                 //dop.id = "success";
                // int n = is.read(b);
                 DataOperation dpo  = (DataOperation)ois.readObject();
                 //System.out.println("" + new String(b,0,n));
                 this.text = dpo.id;
                 System.out.print(this.text);
        } catch (Exception e) {
                 e.printStackTrace();
        }finally{
                 try {
                          is.close();

                          os.close();

                          socket.close();

                 } catch (Exception e2) {}

        }

}

	}

