package com.example.administrator.card;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class editActivity extends AppCompatActivity {
    //编辑用户信息界面
    private EditText name,sex,tel,home,email,happy;
    private Button submit;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit);
        submit = (Button)findViewById(R.id.submit);
        name = (EditText)findViewById(R.id.name);
        sex = (EditText)findViewById(R.id.sex);
        tel = (EditText)findViewById(R.id.tel);
        home = (EditText)findViewById(R.id.home);
        email = (EditText)findViewById(R.id.email);
        happy = (EditText)findViewById(R.id.happy);
        //提交事件处理，将设置的信息保存至数据库中
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name_text = submit.getText().toString();
                String sex_text = sex.getText().toString();
                String tel_text = tel.getText().toString();
                String home_text = home.getText().toString();
                String email_text = email.getText().toString();
                String happy_text = happy.getText().toString();
                for(int i = 0;i<DataOperation.userInformation.size();i++)
                {
                    if(MainActivity.id.equals(DataOperation.userInformation.get(i).id))
                    {
                        DataOperation.userInformation.get(i).name = name_text;
                        DataOperation.userInformation.get(i).sex = sex_text;
                        DataOperation.userInformation.get(i).tel = tel_text;
                        DataOperation.userInformation.get(i).home = home_text;
                        DataOperation.userInformation.get(i).email = email_text;
                        DataOperation.userInformation.get(i).hobby = happy_text;
                        Toast.makeText(editActivity.this, "用户信息修改成功", Toast.LENGTH_SHORT).show();

                    }
                }
                Intent intent = new Intent();
                intent.setClass(editActivity.this,usersViewActivity.class);
                //将新用户名传回给用户主界面
                usersViewActivity.yonghuming.setText(name_text);
                startActivity(intent);

            }
        });
    }
}
