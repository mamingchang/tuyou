package com.example.administrator.card;

public class DataHelper {
    public static  int number[] = new int[]{1,2,3,4,8,5,7,6,0};
    public static void ChangeSide()
    {
        int judge = new Double(Math.random()*5).intValue();
        switch (judge)
        {
            case 0:
                DataHelper.number = new int[]{2,4,1,7,5,6,3,0,8};
                break;
            case 1:
                DataHelper.number = new int[]{2,4,1,7,5,8,0,3,6};
                break;
            case 2:
                DataHelper.number = new int[]{7,1,4,2,5,6,3,0,8};
                break;
            case 3:
                DataHelper.number = new int[]{2,4,1,3,6,5,7,0,8};
                break;
            default:
                break;
        }
    }
}
