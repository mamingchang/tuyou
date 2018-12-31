package com.example.administrator.card;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class haoyoutukuActivity extends AppCompatActivity {
    private Button enter;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.haoyoutuku);
        enter=(Button)findViewById(R.id.enter);

        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(haoyoutukuActivity.this, tuku.class);
                startActivity(intent);
            }
        });
    }
}
