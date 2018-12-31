package com.example.administrator.card;

public class Warehouse {
    public String ID;
    public String Self_house;
    public String Common_houser;
    public Warehouse() {
    }

    public Warehouse(String ID, String Self_house,String Common_houser) {
        this.Self_house = Self_house;
        this.ID = ID;
        this.Common_houser = Common_houser;
    }
}

