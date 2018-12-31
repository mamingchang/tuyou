package com.example.administrator.card;

import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class MainActivity extends AppCompatActivity {
    /* 服务器地址 */
    private final String SERVER_HOST_IP = "120.79.137.28";
    /* 服务器端口 */
    private final int SERVER_HOST_PORT = 10000;
    private Socket socket;
    private PrintStream output;
    private InputStream input;


    public static String id = "";
    //用户登录主界面
    private EditText user_name;
    private EditText user_password;
    private Button login;
    private Button join;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
       // StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().detectDiskReads().detectDiskWrites().detectNetwork().penaltyLog().build());
        //StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder().detectLeakedSqlLiteObjects().detectLeakedClosableObjects().penaltyLog().penaltyDeath().build());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.enter);

        user_name = (EditText) findViewById(R.id.name_field);
        user_password = (EditText) findViewById(R.id.password_field);
        login = (Button) findViewById(R.id.login);
        join = (Button) findViewById(R.id.join);

        final String serverIP = "120.79.137.28";



        final int port = 10000;

        //登录事件处理
        login.setOnClickListener(new View.OnClickListener() {
                                     @Override
                                     public void onClick(View v) {
                                         String userName = user_name.getText().toString();
                                         String userPassword = user_password.getText().toString();
                                         if(userName.equals("admin")&&userPassword.equals("123"))
                                         {
                                             Intent intent = new Intent();
                                             intent.setClass(MainActivity.this, usersViewActivity.class);
                                             intent.putExtra("name","admin");
                                             startActivity(intent);
                                         }
                                         else if(userName.equals("")||userPassword.equals(""))
                                         {
                                             Toast.makeText(MainActivity.this, "用户名和密码不能为空！", Toast.LENGTH_SHORT).show();
                                         }
                                         else
                                         {
                                             for(int i = 0;i<DataOperation.user.size();i++)
                                             {
                                                 if(userName.equals(DataOperation.user.get(i).ID)&&userPassword.equals(DataOperation.user.get(i).password))
                                                 {
                                                     MainActivity.id = userName;
                                                     Intent intent = new Intent();
                                                     intent.setClass(MainActivity.this, usersViewActivity.class);
                                                     intent.putExtra("name",DataOperation.user.get(i).name);
                                                     startActivity(intent);
                                                 }
                                                 else
                                                 {

                                                 }
                                             }
                                             Toast.makeText(MainActivity.this, "该用户不存在或密码错误！", Toast.LENGTH_SHORT).show();
                                         }

                                     }
                                 }
        );
        //注册事件处理
        join.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Intent intent = new Intent();
                                        intent.setClass(MainActivity.this, zhuceActivity.class);
                                        startActivity(intent);
                                    }
                                }
        );
    }
        public void showToast (final String msg){
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
                }
            });
        }

}
