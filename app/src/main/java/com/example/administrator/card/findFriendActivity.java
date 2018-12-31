package com.example.administrator.card;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class findFriendActivity extends AppCompatActivity {
    private EditText user_name,id;
    private  Button yonghumingchazhao,zhanghaochazhao,heimingdan,haoyoujiemian;
    public String user,idid;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.find_friends);
        user_name = (EditText) findViewById(R.id.name_field);
        id = (EditText) findViewById(R.id.id);
        yonghumingchazhao = (Button) findViewById(R.id.yonghumingchazhao);
        zhanghaochazhao = (Button) findViewById(R.id.zhanghaochazhao);
        heimingdan = (Button) findViewById(R.id.heimingdan);
        haoyoujiemian = (Button) findViewById(R.id.haoyoujiemian);

        yonghumingchazhao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(findFriendActivity.this, searchResultActivity.class);
                startActivity(intent);
            }
        });
        zhanghaochazhao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(findFriendActivity.this, searchResultActivity.class);
                startActivity(intent);
            }
        });
        /*zhanghaochazhao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(findFriendActivity.this, friendsResultActivity.class);
                intent.putExtra("id",idid);
                intent.putExtra("name",user);
                startActivity(intent);
            }
        });*/
        heimingdan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(findFriendActivity.this, blackListActivity.class);
                startActivity(intent);

            }
        });
        haoyoujiemian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(findFriendActivity.this, friendViewActivity.class);
                startActivity(intent);

            }
        });

    }



}
