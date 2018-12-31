package com.example.administrator.card;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class usersViewActivity extends AppCompatActivity {
    //用户登录后操作主界面
    private Button gerenxinxi,siyoutuku,chazhaohaoyou,gongyoutuku,haoyoutuku;
    private Button tanchishe,shuziyouxi,leitingzhanji,wuziqi;
    public static TextView yonghuming;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.userview);
        Intent it= this.getIntent();
        Bundle bd = it.getExtras();
        String yonghuming_new = bd.getString("name");
        yonghuming = (TextView)findViewById(R.id.name_field);
        yonghuming.setText(yonghuming_new);
        gerenxinxi = (Button)findViewById( R.id.gerenxinxi);
        siyoutuku = (Button)findViewById( R.id.siyoutuku);
        chazhaohaoyou = (Button)findViewById( R.id.chazhaohaoyou);
        gongyoutuku = (Button)findViewById( R.id.gongyoutuku);
        haoyoutuku = (Button)findViewById( R.id.haoyoutuku);
        //游戏按键监听
        tanchishe = (Button)findViewById( R.id.tanchishe);
        shuziyouxi = (Button)findViewById( R.id.numbergame);
        leitingzhanji = (Button)findViewById( R.id.leitingzhanji);
        wuziqi = (Button)findViewById( R.id.wuziqi);
        //点击个人信息按钮进入个人信息界面
        gerenxinxi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(usersViewActivity.this, personal_informationActivity.class);
                startActivity(intent);
            }
        });
        //点击私有图库按钮进入私有图库
        siyoutuku.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(usersViewActivity.this, siyoutukuActivity.class);
                startActivity(intent);
            }
        });
        //点击查找好友按钮进入查找好友界面
        chazhaohaoyou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(usersViewActivity.this, findFriendActivity.class);
                startActivity(intent);
            }
        });
        //点击公有图库按钮进入公有图库界面
        gongyoutuku.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(usersViewActivity.this, gongyoutukuActivity.class);
                startActivity(intent);

            }
        });
        //点击好友图库按钮进入好友图库界面
        haoyoutuku.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(usersViewActivity.this, haoyoutukuActivity.class);
                startActivity(intent);

            }
        });

        wuziqi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(usersViewActivity.this,wuziqimain.class);
                startActivity(intent);
            }
        });
        shuziyouxi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(usersViewActivity.this,NumberGame.class);
                startActivity(intent);
            }
        });
    }
}
