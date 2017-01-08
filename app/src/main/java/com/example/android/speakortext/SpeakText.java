package com.example.android.speakortext;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.support.v7.app.AppCompatActivity;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

/**
 * Created by Divyani on 07-01-2017.
 */
public class SpeakText extends AppCompatActivity
{
    TextView tset1;

       private final int REQ_CODE_SPEECH_INPUT=100;
    TextView  tv;
    ArrayAdapter<CharSequence> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.speak);
        tv=(TextView)findViewById(R.id.tm);
            tset1 = (TextView)findViewById(R.id.tset1);
        Spannable content=new SpannableString("Speech To Text");
        content.setSpan(new UnderlineSpan(),0,content.length(),0);
        tset1.setText(content);


        // Step 1: Start recognizer Intent
        /*
              Flags:
              ACTION_recognize_speech : Takes user speech input and returns it to same activity
              LANGUAGE_MODEL_FREE_FORM : Consider inputs in free form English
              EXTRA_PROMPT : Text prompt to show user asking them to speak
              Once speech input is done we have to catch response from onActivity and then take appropiate action


         */


    }
    public void startNow(View v)
    {
        promptSpeechInput();

    }
    public void promptSpeechInput()
    {

        // showing google speech input dialog
        // Initialize Recognizer
        Intent i= new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        i.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        // putExtras is for Bundle object
        i.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        i.putExtra(RecognizerIntent.EXTRA_PROMPT,"Say Something");
        // with intent start activity for result
        try
        {
            startActivityForResult(i,REQ_CODE_SPEECH_INPUT);
        }
        catch(ActivityNotFoundException e)
        {
            Toast.makeText(getApplicationContext(),"Sorry your device Does not support Speech Recognition",Toast.LENGTH_LONG).show();
        }



    }
    // Now actually receive Input
    protected  void onActivityResult(int requestCode,int resultCode,Intent data)
    {
        // Calling the parent class constructor
        super.onActivityResult(requestCode,resultCode,data);

        switch (requestCode) {
            case REQ_CODE_SPEECH_INPUT: {
                if (resultCode == RESULT_OK && null != data) {

                    ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                  tv.setText(result.get(0));
                }
                break;

            }
            default:
            {
                Toast.makeText(getApplicationContext(),"Not valid requestCode",Toast.LENGTH_LONG).show();
                    break;
            }

        }
    }

}
