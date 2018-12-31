package com.example.administrator.card;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class personal_informationActivity extends AppCompatActivity {
    //显示用户个人信息界面
    private EditText name,sex,tel,home,email,happy;
    private Button submit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.personal_information);
        submit = (Button)findViewById(R.id.submit);
        name = (EditText)findViewById(R.id.name);
        sex = (EditText)findViewById(R.id.sex);
        tel = (EditText)findViewById(R.id.tel);
        home = (EditText)findViewById(R.id.home);
        email = (EditText)findViewById(R.id.email);
        happy = (EditText)findViewById(R.id.happy);
        for(int i = 0;i<DataOperation.userInformation.size();i++)
        {
            if(MainActivity.id.equals(DataOperation.userInformation.get(i).id))
            {
                name.setText(DataOperation.userInformation.get(i).name);
                sex.setText(DataOperation.userInformation.get(i).sex);
                tel.setText(DataOperation.userInformation.get(i).tel);
                home.setText(DataOperation.userInformation.get(i).home);
                email.setText(DataOperation.userInformation.get(i).email);
                happy.setText(DataOperation.userInformation.get(i).hobby);
            }
        }
        //编辑事件处理，进入当前用户的个人信息编辑界面
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(personal_informationActivity.this,editActivity.class);
                startActivity(intent);
            }
        });
    }
}
