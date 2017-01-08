package com.example.android.speakortext;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void St(View v)
    {
        Intent i=new Intent(this,SpeakText.class);
        startActivity(i);

    }
    public  void  ts(View v)
    {
        Intent i1=new Intent(this,TextSpeech.class);
        startActivity(i1);

    }
}
