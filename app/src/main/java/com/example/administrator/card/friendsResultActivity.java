package com.example.administrator.card;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.AbsoluteLayout;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class friendsResultActivity extends Activity {
    public static ArrayList<String>yonghuID = new ArrayList<String>();
    public static int number = 0;
    public static String id = "",name = "";
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.friends_result);
        //LinearLayout linearLayout = findViewById(R.id.linearlayout);
        ScrollView sll= findViewById(R.id.scrollview);
        /*

        * */

        Intent intent = getIntent();
        Bundle bd = getIntent().getExtras();
        id = intent.getStringExtra("id");
        name = intent.getStringExtra("name");

            //测试使用
            UserInformation ee= new UserInformation();
            UserInformation ee2= new UserInformation();
            ee.id  ="123";
            ee.name="abc";
            ee2.id = "456";
            ee2.name = "abc";
            DataOperation.userInformation.add(ee);
            DataOperation.userInformation.add(ee2);

        if(name==null||id==null)
        {
            Log.i("ceshi","namekonghuoasd");
            Toast.makeText(friendsResultActivity.this, "name或id为null", Toast.LENGTH_SHORT).show();
        }
        if(DataOperation.userInformation.size()==0)
        {
            Toast.makeText(friendsResultActivity.this, "未找到该用户", Toast.LENGTH_SHORT).show();
        }
        else if((name!=null&&name.equals(""))&&((id!=null)&&(!id.equals(""))))
        {
            Toast.makeText(friendsResultActivity.this, "用户名", Toast.LENGTH_SHORT).show();
        }
        else if((id!=null&&id.equals(""))&&((name!=null)&&(!name.equals(""))))
        {
            Toast.makeText(friendsResultActivity.this, "成功", Toast.LENGTH_SHORT).show();
            friendsResultActivity.number = 0;
            for(int i = 0;i<DataOperation.userInformation.size();i++)
            {
                if(DataOperation.userInformation.get(i).name.equals(name))
                {
                    RelativeLayout yonghu = new RelativeLayout(this);
                    //创建账号视图
                    TextView zhanghao = new TextView(this);
                    //创建添加好友按钮
                    Button tianjiahaoyou = new Button(this);
                    //创建加入黑名单按钮
                    Button jiaruheimingdan = new Button(this);
                    //设置账号文本ID
                    zhanghao.setId(friendsResultActivity.number);
                    //设置账号文本内容为查找的用户ID
                    zhanghao.setText(DataOperation.userInformation.get(i).id);
                    //设置添加好友按钮的ID
                    tianjiahaoyou.setId(friendsResultActivity.number);
                    //设置加入黑名单后的ID
                    jiaruheimingdan.setId(friendsResultActivity.number);
                    //将ID数上升
                    friendsResultActivity.number++;
                    //将文本、两个按钮添加到布局容器中
                    //设置布局内元素的大小和位置
                    RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,RelativeLayout.LayoutParams.WRAP_CONTENT);
                    //
                    //
                    RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams)yonghu.getLayoutParams();
                  //  params.setMargins(10, 5, 100, 5);// 通过自定义坐标来放置你的控件
                    //zhanghao .setLayoutParams(params);

                    //tianjiahaoyou.setBackground();
                    RelativeLayout.LayoutParams params2 = (RelativeLayout.LayoutParams)yonghu.getLayoutParams();
                  //  params.setMargins(10, 5, 200, 5);// 通过自定义坐标来放置你的控件
                    //tianjiahaoyou .setLayoutParams(params2);

                    RelativeLayout.LayoutParams params3 = (RelativeLayout.LayoutParams)yonghu.getLayoutParams();
                  //  params.setMargins(10, 5, 10, 5);// 通过自定义坐标来放置你的控件
                    //jiaruheimingdan .setLayoutParams(params3);
                    //
                    //
                    yonghu.addView(zhanghao,lp);
                    yonghu.addView(tianjiahaoyou,lp);
                    yonghu.addView(jiaruheimingdan,lp);

                    RelativeLayout.LayoutParams lp2 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,RelativeLayout.LayoutParams.WRAP_CONTENT);
                   // sll.addView(yonghu,lp2);
                    //设置布局的大小和位置
                    //将布局容器添加到总布局之中
                    //setContentView(sll);
                    setContentView(yonghu);
                }
            }
        }
    }


}

