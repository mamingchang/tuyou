package com.example.administrator.card;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

public class wuziqiOperation extends View {
    private int mPanelWidth;//定义棋盘的宽度
    private float mLineHight;//定义棋盘的线长
    private int MAX_LINE = 10;//定义棋盘的最多线数量

    private Paint mPaint = new Paint();
    private Bitmap mWhitePiece;//定义白棋
    private Bitmap mBlackPiece;//定义黑棋
    private float ratioPieceOfLineHight = 3 * 1.0f / 4;

    private boolean mIsWith = true;//用于转换双方交替下子
    private List<Point> mWitharry = new ArrayList<Point>();//定义保存白棋的泛型链表
    private List<Point> mBlackarry = new ArrayList<Point>();//定义保存黑棋的泛型列表

    private boolean mIsGemOver;//定义判断游戏是否结束的判断变量
    private boolean mIsWhiteWinner;//定义是否白棋胜利的变量

    //继承View，覆盖构造方法
    public wuziqiOperation(Context context, AttributeSet attrs) {
        super(context, attrs);
        // setBackgroundColor(0x44ff0000);
        //进行棋盘的初始化
        init();
    }

    //封装棋盘的初始化操作
    private void init() {
        //设置绘图的颜色
        mPaint.setColor(0x44ff0000);
        //设置抗锯齿
        mPaint.setAntiAlias(true);
        //设置是否抖动
        mPaint.setDither(true);
        //设置风格为描边
        mPaint.setStyle(Paint.Style.STROKE);
        //通过BitmapFactory的静态方法资源获取创建位图
        mWhitePiece = BitmapFactory.decodeResource(getResources(), R.drawable.stone_w2);
        mBlackPiece = BitmapFactory.decodeResource(getResources(), R.drawable.stone_b1);

    }

    //设置时间监听
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //判断是否结束游戏，如果是，返回false
        if (mIsGemOver) {
            return false;
        }
        //定义int值来获取事件类型
        int action = event.getAction();
        //判断获取到的事件类型是否是手指离开屏幕时
        if (action == MotionEvent.ACTION_UP) {
            //获取时间发生坐标
            int x = (int) event.getX();
            int y = (int) event.getY();
            //调用封装的获取坐标点函数返回精确棋盘坐标点
            Point p = getVaLidPoint(x, y);
            //判断白棋列表或黑棋列表中是否已经包含点击的坐标点，若包含就返回空
            if (mWitharry.contains(p) || mBlackarry.contains(p)) {
                return false;
            }
            //判断是否是白棋走的，是就将坐标点添加入白棋链表
            if (mIsWith) {
                mWitharry.add(p);
            }
            //否则加入黑棋列表
            else {
                mBlackarry.add(p);
            }
            //使客户窗口交互区无效并进行窗口重绘
            invalidate();
            //转换棋子双方
            mIsWith = !mIsWith;
        }
        return true;
    }

    //封装获取精确坐标点的函数
    private Point getVaLidPoint(int x, int y) {
        //通过类型强制转换将坐标精度准确到棋盘棋子定位处
        return new Point((int) (x / mLineHight), (int) (y / mLineHight));
    }

    //重写onMeasure方法测量宽高
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        /*
        其中makeMeasureSpec（）方法的作用将size 和 mode 打包成一个32位的int值，之所以这样做就是为了减少内存的分配。
        返回值为打包成的int类型值measureSpec 。
        getMode 和 getSize 则是根据传入的int 类型值，解包成为 mode 和 size
        * */
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);

        int heighSize = MeasureSpec.getSize(heightMeasureSpec);
        int heighMode = MeasureSpec.getMode(heightMeasureSpec);

        int width = Math.min(widthSize, heighSize);

        //UNSPECIFIED未使用过的
        if (widthMode == MeasureSpec.UNSPECIFIED) {
            width = heighSize;
        } else if (heighMode == MeasureSpec.UNSPECIFIED) {
            width = widthSize;
        }
        //设置复写view的大小
        setMeasuredDimension(width, width);
    }

    //onSizeChanged() 在控件大小发生改变时调用。所以这里初始化会被调用一次
    //作用：获取控件的宽和高度
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        //棋盘宽度更新
        mPanelWidth = w;
        //棋盘高度更新
        mLineHight = mPanelWidth * 1.0f / MAX_LINE;
        //
        int PiceWhite = (int) (mLineHight * ratioPieceOfLineHight);
        //根据原来已有的位图重新绘制位图
        mWhitePiece = Bitmap.createScaledBitmap(mWhitePiece, PiceWhite, PiceWhite, false);
        mBlackPiece = Bitmap.createScaledBitmap(mBlackPiece, PiceWhite, PiceWhite, false);
    }

    //重写onDraw方法绘制控件，这个方法在每次控件发生变化的时候调用一次
    @Override
    protected void onDraw(Canvas canvas) {
        //调用父类方法
        super.onDraw(canvas);
        //调用绘制棋盘线的函数
        drawBoard(canvas);
        //调用绘制棋子的函数
        drawPicec(canvas);
        //判断游戏是否结束
        checkGameOver();
    }

    //判断游戏是否结束
    private void checkGameOver() {
        //判断是否白棋赢
        boolean whithWin = chechFiveInLine(mWitharry);
        //判断是否黑棋赢
        boolean blickWin = chechFiveInLine(mBlackarry);
        if (whithWin || blickWin) {
            //将游戏结束变量设置为true
            mIsGemOver = true;
            //将判断是否白棋胜的变量赋值
            mIsWhiteWinner = whithWin;
            //使用三元运算符将对决信息以消息框的形式发出
            String text = mIsWhiteWinner ? "汪星人胜利" : "喵星人胜利";
            Toast.makeText(getContext(), text, Toast.LENGTH_LONG).show();
            ;
        }
    }

    //判断是否五连子的调用函数
    private boolean chechFiveInLine(List<Point> mWitharry2) {
        //循环判断棋子链表的每一个Point类
        for (Point p : mWitharry2) {
            int x = p.x;
            int y = p.y;
            //检查水平方向是否连成五子
            boolean win = checkHorizontal(x, y, mWitharry2);
            if (win) return true;
            //检查数值方向是否连成五子
            win = checkVertIcal(x, y, mWitharry2);
            if (win) return true;
            //检查左斜方向是否连成五子
            win = checkLeftDiagonal(x, y, mWitharry2);
            if (win) return true;
            //检查右斜方向是否连成五子
            win = checkRightDiagonl(x, y, mWitharry2);
            if (win) return true;
        }
        return false;
    }

    //检查水平方向是否连成五子的检测函数
    private boolean checkHorizontal(int x, int y, List<Point> mWitharry2) {
        int count = 1;
        for (int i = 1; i < 5; i++) {
            if (mWitharry2.contains(new Point(x-i,y))) {
                count++;
            }else {
                break;
            }
        }
        if (count==5) return true;
        for (int i = 1; i < 5; i++) {
            if (mWitharry2.contains(new Point(x+i,y))) {
                count++;
            }else {
                break;
            }
            if (count==5) return true;
        }
        return false;
    }
    //检查右斜方向是否连成五子的检测函数
    private boolean checkRightDiagonl(int x, int y, List<Point> mWitharry2) {
        int count = 1;
        for (int i = 1; i < 5; i++) {
            if (mWitharry2.contains(new Point(x-i,y-i))) {
                count++;
            }else {
                break;
            }
        }
        if (count==5) return true;
        for (int i = 1; i < 5; i++) {
            if (mWitharry2.contains(new Point(x+i,y+i))) {
                count++;
            }else {
                break;
            }
            if (count==5) return true;
        }
        return false;
    }
    //检查左斜方向是否连成五子的检测函数
    private boolean checkLeftDiagonal(int x, int y, List<Point> mWitharry2) {
        int count = 1;
        for (int i = 1; i < 5; i++) {
            if (mWitharry2.contains(new Point(x-i,y+i))) {
                count++;
            }else {
                break;
            }
        }
        if (count==5) return true;
        for (int i = 1; i < 5; i++) {
            if (mWitharry2.contains(new Point(x+i,y-i))) {
                count++;
            }else {
                break;
            }
            if (count==5) return true;
        }
        return false;
    }
    //检查垂直方向是否连成五子的检测函数
    private boolean checkVertIcal(int x, int y, List<Point> mWitharry2) {
        int count = 1;
        for (int i = 1; i < 5; i++) {
            if (mWitharry2.contains(new Point(x,y-i))) {
                count++;
            }else {
                break;
            }
        }
        if (count==5) return true;
        for (int i = 1; i < 5; i++) {
            if (mWitharry2.contains(new Point(x,y+i))) {
                count++;
            }else {
                break;
            }
            if (count==5) return true;
        }
        return false;
    }

    //绘制棋子的操作函数
    private void drawPicec(Canvas canvas) {
        for (int i = 0, n = mWitharry.size(); i < n; i++) {
            Point whitePoint = mWitharry.get(i);
            canvas.drawBitmap(mWhitePiece, (whitePoint.x + (1 - ratioPieceOfLineHight) / 2) * mLineHight,
                    (whitePoint.y + (1 - ratioPieceOfLineHight) / 2) * mLineHight, null);
        }
        for (int i = 0, n = mBlackarry.size(); i < n; i++) {
            Point blackPoint = mBlackarry.get(i);
            canvas.drawBitmap(mBlackPiece, (blackPoint.x + (1 - ratioPieceOfLineHight) / 2) * mLineHight,
                    (blackPoint.y + (1 - ratioPieceOfLineHight) / 2) * mLineHight, null);
        }
    }

    //绘制棋盘线的操作函数
    private void drawBoard(Canvas canvas) {
        int w = mPanelWidth;
        float lineHeight = mLineHight;
        for (int i = 0; i < MAX_LINE; i++) {
            int startX = (int) (lineHeight / 2);
            int endX = (int) (w - lineHeight / 2);

            int y = (int) ((0.5 + i) * lineHeight);
            canvas.drawLine(startX, y, endX, y, mPaint);
            canvas.drawLine(y, startX, y, endX, mPaint);
        }
    }

}
