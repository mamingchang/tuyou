package com.example.administrator.card;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class threadtext  implements  Runnable{
    private Socket socket;

    public threadtext(Socket socket){
        this.socket=socket;
    }

public static String id = null;
    @Override
    public void run() {

        InputStream is = null;

        OutputStream os = null;




        String data = "Hello";

        try {

            os = socket.getOutputStream();
            Log.e("STREAM",os.toString()+"123");
           // ObjectOutputStream oos  = new ObjectOutputStream(os);
            os.write(data.getBytes());
            os.flush();
            os.close();
            is = socket.getInputStream();
            ObjectInputStream ois = new ObjectInputStream(is);
//            byte[] b = new byte[1024];
            //DataOperation dop = new DataOperation();
            //dop.id = "success";
            // int n = is.read(b);
            DataOperation dpo  = (DataOperation)ois.readObject();
            //System.out.println("" + new String(b,0,n));
            threadtext.id = dpo.id;
            Log.e("id",id+"111");
           // System.out.print(this.id);

            is.close();

            os.close();

            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("ERROR",e.getMessage());
        }
    }
}
