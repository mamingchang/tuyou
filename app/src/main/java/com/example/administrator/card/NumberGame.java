package com.example.administrator.card;

import android.os.Bundle;
import android.os.Vibrator;
import android.app.Activity;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class NumberGame extends Activity{

    //定义线性布局
    private LinearLayout ll;
    //定义双重textview数组来记录此时各个数字模块的位置
    private TextView[][] tv_group;
    //定义int数组判断是否已经排好序
    private int number[]=new int[9] ;
    //定义振动器类
    Vibrator mVibrator;
    //定义记录分数的Textview
    private TextView tv_socer;
    //定义重新开始按钮
    private Button btnrestart;
    //定义int值socerlai记录步数
    private int socer = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.number_game);
        DataHelper.ChangeSide();
        for(int i=0;i<9;i++){
            //将现在各个模块的顺序传入number判断数组中
            number[i]=DataHelper.number[i];
        }
        //初始化组件
        initView();
        //初始化游戏画面
        initGame();
        //实时更新玩家的移动步数
        tv_socer.setText("移动了 "+String.valueOf(socer)+" 步");
        //获取当前系统的蜂鸣器服务
        mVibrator = (Vibrator)getApplication().getSystemService(VIBRATOR_SERVICE);
        //设置线性布局的事件监听
        ll.setOnTouchListener(new OnTouchListener() {


            //定义记录滑动的起始点坐标和终止点坐标
            private int x1, x2;
            private int y1, y2;

            //复写touch事件
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                //判断事件类型
                switch (event.getAction()) {
                    //在松开时获取结束的坐标点
                    case MotionEvent.ACTION_DOWN:
                        x1 = (int) event.getX();
                        y1 = (int) event.getY();
                        break;
                        //在按下时获取开始的坐标点
                    case MotionEvent.ACTION_UP:
                        x2 = (int) event.getX();
                        y2 = (int) event.getY();

                        //判断滑动方向为下
                        if (y2 - y1 > 20 && y2 - y1 > Math.abs(x2 - x1)) {
                            //调用向下的操作函数
                            down();
                            //调用判断游戏是否结束的方法
                            overGame();
                            //调用重现加载游戏画面的方法
                            initGame();
                        }
                        //判断滑动方向为上
                        else if (y1 - y2 > 20 && y1 - y2 > Math.abs(x2 - x1)) {
                            up();
                            overGame();
                            initGame();
                        }
                        //判断滑动方向为左
                        else if (x2 - x1 > 20 && x2 - x1 > Math.abs(y2 - y1)) {
                            left();
                            overGame();
                            initGame();
                        }
                        //判断滑动方向为右
                        else if (x1 - x2 > 20 && x1 - x2 > Math.abs(y2 - y1)) {
                            right();
                            overGame();
                            initGame();
                        }

                        break;
                }
                return true;
            }
        });

        //设置按钮的监听事件处理
        btnrestart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //将各模块恢复至起始位置
                for(int i=0;i<9;i++){
                    number[i]=DataHelper.number[i];
                }
                //重新加载游戏画面
                initGame();
                //重置分数为0
                socer = 0 ;
                tv_socer.setText("移动了 "+String.valueOf(socer)+" 步");
            }
        });
    }

    //定义向左滑动的实际操作函数
    protected void left() {
        //找到0模块所在的位置
        int numzero = FindZero();
        //定义用于接受交换的模块值
        int exchanged;
        //判断0模块是否在最左方
        if(numzero%3==0){
            //定义震动
            mVibrator.vibrate( new long[]{100,10,100,200}, -1); //第一个｛｝里面是节奏数组， 第二个参数是重复次数，-1为不重复，非-1从pattern的指定下标开始重复
        }else{
            exchanged = number[numzero];
            number[numzero]=number[numzero-1];
            number[numzero-1]=exchanged;
            socer = socer + 1;
            tv_socer.setText("移动了 "+String.valueOf(socer)+" 步");
        }
    }

    //定义向右滑动的实际操作函数
    protected void right() {
        int numzero = FindZero();
        int exchanged;
        if(numzero%3==2){
            //定义震动
            mVibrator.vibrate( new long[]{100,10,100,200}, -1); //第一个｛｝里面是节奏数组， 第二个参数是重复次数，-1为不重复，非-1俄日从pattern的指定下标开始重复
        }else{
            exchanged = number[numzero];
            number[numzero]=number[numzero+1];
            number[numzero+1]=exchanged;
            socer = socer + 1;
            tv_socer.setText("移动了 "+String.valueOf(socer)+" 步");
        }

    }

    //定义向上滑动的实际操作函数
    protected void up() {
        int numzero = FindZero();
        int exchanged;
        if(numzero>5){
            //定义震动
            mVibrator.vibrate( new long[]{100,10,100,200}, -1); //第一个｛｝里面是节奏数组， 第二个参数是重复次数，-1为不重复，非-1俄日从pattern的指定下标开始重复
        }else{
            exchanged = number[numzero];
            number[numzero]=number[numzero+3];
            number[numzero+3]=exchanged;
            socer = socer + 1;
            tv_socer.setText("移动了 "+String.valueOf(socer)+" 步");
        }
    }
    //定义向下滑动的实际操作函数
    public void down() {
        int numzero = FindZero();
        int exchanged;
        if(numzero<3){
            //定义震动
            mVibrator.vibrate( new long[]{100,10,100,200}, -1); //第一个｛｝里面是节奏数组， 第二个参数是重复次数，-1为不重复，非-1俄日从pattern的指定下标开始重复
        }else{
            exchanged = number[numzero];
            number[numzero]=number[numzero-3];
            number[numzero-3]=exchanged;
            socer = socer + 1;
            tv_socer.setText("移动了 "+String.valueOf(socer)+" 步");
        }
    }
    //定义用于找到0模块，即空模块的所在位置，方便调用4个移动方法时判断使用
    public int FindZero(){
        for(int i=0;i<9;i++){
            if(number[i]==0){
                return i;
            }
        }
        return 0;
    }

    //定义初始化组件的方法
    private void initView() {
        tv_group = new TextView[3][3];
        ll= (LinearLayout)findViewById(R.id.ll);
        tv_group[0][0] =(TextView) findViewById(R.id.tv_1);
        tv_group[0][1] = (TextView)findViewById(R.id.tv_2);
        tv_group[0][2] = (TextView)findViewById(R.id.tv_3);
        tv_group[1][0] = (TextView)findViewById(R.id.tv_4);
        tv_group[1][1] = (TextView)findViewById(R.id.tv_5);
        tv_group[1][2] = (TextView)findViewById(R.id.tv_6);
        tv_group[2][0] = (TextView)findViewById(R.id.tv_7);
        tv_group[2][1] = (TextView)findViewById(R.id.tv_8);
        tv_group[2][2] = (TextView)findViewById(R.id.tv_9);
        tv_socer = (TextView)findViewById(R.id.tv_socer);
        btnrestart=(Button)findViewById(R.id.btn_restart);
    }
    //定义初始化或者进行移动操作后重新加载游戏画面的方法
    public void initGame(){
        for(int i=0 ;i<3;i++){
            for(int j=0;j<3;j++){
                if(number[i*3+j]==0){
                    tv_group[i][j].setText(" ");
                }else{
                    tv_group[i][j].setText(String.valueOf(number[i*3+j]));
                }
            }
        }
    }

    //定义判断并执行结束游戏的方法
    public void overGame(){
        int total=0;
        for(int i = 0;i<7;i++){
            if(number[i+1]-number[i]==1){
                total=total+1;
            }
        }
        if(total==7&&number[8]==0){
            Toast.makeText(NumberGame.this, "GameOver", Toast.LENGTH_LONG).show();
            for(int i=0;i<9;i++){
                number[i]=DataHelper.number[i];
            }
            socer = 0 ;
            tv_socer.setText("移动了 "+String.valueOf(socer)+" 步");
            System.out.println("GameOver");
        }
    }
}
