package com.example.chronicle972.siblings;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button course;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        course= (Button)findViewById(R.id.btncourse);
    }

   public void clickcourse(View V)
    {
        Intent intent = new Intent(MainActivity.this, listecourse.class);
        startActivity(intent);

    }


}
