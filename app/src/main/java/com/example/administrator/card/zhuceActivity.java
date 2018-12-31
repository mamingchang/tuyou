package com.example.administrator.card;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class zhuceActivity extends AppCompatActivity {
    //用户注册界面
    public String makesure;
    private EditText name,password,again,inputone;
    private Button getone,submit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.zhuce);
        name = (EditText)findViewById(R.id.name);
        password = (EditText)findViewById(R.id.password);
        again = (EditText)findViewById(R.id.again);
        inputone = (EditText)findViewById(R.id.inputone);
        getone = (Button)findViewById(R.id.getone);
        submit = (Button)findViewById(R.id.submit);
        //点击获取验证码按钮进行验证码的输送
        getone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makesure = new Integer(10000+new Double((Math.random()*90000)).intValue()).toString();
                Toast.makeText(zhuceActivity.this, makesure, Toast.LENGTH_SHORT).show();
            }
        });
        //点击提交按钮将注册信息上交并进行后续操作
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nameTemp = name.getText().toString();
                String passwordTemp = password.getText().toString();
                String againTemp = again.getText().toString();
                String inputoneTemp = inputone.getText().toString();
                if(nameTemp.length()<9||nameTemp.length()>13)
                {
                    Toast.makeText(zhuceActivity.this, "用户账号必须在9~12位数!", Toast.LENGTH_SHORT).show();
                }
                for(int i = 0;i<DataOperation.user.size();i++)
                {
                    if(nameTemp.equals(DataOperation.user.get(i).ID))
                    {
                        Toast.makeText(zhuceActivity.this, "该账户已存在!", Toast.LENGTH_SHORT).show();
                        name.setText("");
                        return;
                    }
                }
                if(!passwordTemp.equals(againTemp))
                {
                    Toast.makeText(zhuceActivity.this, "确认密码不正确!", Toast.LENGTH_SHORT).show();
                    password.setText("");
                    again.setText("");

                }
                else if(!makesure.equals(inputoneTemp))
                {
                    Toast.makeText(zhuceActivity.this, "验证码不正确!", Toast.LENGTH_SHORT).show();

                }
                else{
                    //更新User表
                    User user = new User();
                    user.ID = nameTemp;
                    user.name ="用户"+nameTemp;
                    user.password = passwordTemp;
                    DataOperation.user.add(user);
                    //更新UserInformation表
                    UserInformation userInformation = new UserInformation();
                    userInformation.id = nameTemp;
                    userInformation.name = "用户"+nameTemp;
                    DataOperation.userInformation.add(userInformation);
                    //更新black表
                    Black black = new Black();
                    black.ID = nameTemp;
                    DataOperation.blacks.add(black);
                    //更新friends表
                    Friends friends = new Friends();
                    friends.ID = nameTemp;
                    DataOperation.friends.add(friends);
                    //更新commonhouse表
                    CommonHouse commonHouse = new CommonHouse();
                    commonHouse.ID = nameTemp;
                    DataOperation.commonHouses.add(commonHouse);
                    //更新selfhouse表
                    SelfHouse selfHouse = new SelfHouse();
                    selfHouse.ID = nameTemp;
                    DataOperation.selfHouses.add(selfHouse);
                    Toast.makeText(zhuceActivity.this, "用户创建成功", Toast.LENGTH_SHORT).show();
                    MainActivity.id = nameTemp;
                    Intent intent = new Intent();
                    intent.setClass(zhuceActivity.this, usersViewActivity.class);
                    intent.putExtra("name","用户"+nameTemp);
                    startActivity(intent);
                }

            }
        });
    }

}