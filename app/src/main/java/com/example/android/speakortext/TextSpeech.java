package com.example.android.speakortext;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

/**
 * Created by Divyani on 07-01-2017.
 */
public class TextSpeech extends AppCompatActivity
{
    double speechRate=0.0f,pitch=0.0f;
    Spinner s;
    SeekBar sPitch;
    SeekBar sRate;
    TextView tset;

    TextToSpeech toj;
    EditText e;
    ArrayAdapter<CharSequence> adapter;
    String sp=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.text);
        e=(EditText)findViewById(R.id.e);
         s=(Spinner)findViewById(R.id.spinner);
        tset=(TextView)findViewById(R.id.tset);
        Spannable content=new SpannableString("Text To Speech");
        content.setSpan(new UnderlineSpan(), 0, content.length(), 0);
        tset.setText(content);
        sPitch=(SeekBar)findViewById(R.id.s1Pitch);
        sRate=(SeekBar)findViewById(R.id.s2Rate);

        sRate.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                speechRate = ((double)i+1)/10;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        sPitch.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                pitch=((double)i+1)/10;
                // scale of incrementing the pitch
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });






















        adapter = ArrayAdapter.createFromResource(this,R.array.SelectText,R.layout.support_simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        s.setAdapter(adapter);
        s.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getBaseContext(),adapterView.getItemAtPosition(i)+" Selected",Toast.LENGTH_SHORT).show();
                sp = s.getSelectedItem().toString();
               // Toast.makeText(getApplicationContext(),"SP : "+sp,Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                sp = "US-English";
            }
        });
        //Using the TextToSpeech
        toj=new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status != TextToSpeech.ERROR)
                {
                    toj.setLanguage(Locale.US);

                }
            }
        });
    }
    public void onPause()
    {
        // if it has paused but not null then shutdown it
        if(toj !=null)
        {
            // stop : stop to speak
            // shutdown : release all the resources
            toj.stop();
            toj.shutdown();
        }
        super.onPause();
    }
    public void speakText(View v)
    {


        String s8="Japanese";
        String s6="FRENCH";
        String s5="Canada-French";
        String s3="Germany-German";
        String s1="US-English";
        String s7="Korean";
        String s4="Italy-Italian";
        String s2="CHINA-Chinese";
        // if there is no error in text to speech then set the language for conversion
        if(sp.equals(s1))
        {
            toj.setLanguage(Locale.ENGLISH);
        }
        else if(sp.equals(s2))
        {
           // Toast.makeText(getApplicationContext(),"ok",Toast.LENGTH_SHORT).show();
            toj.setLanguage(Locale.CHINA);

        }
        else if(sp.equals(s3))
        {
            toj.setLanguage(Locale.GERMANY);
        }
        else if(sp.equals(s4))
        {
            toj.setLanguage(Locale.ITALY);
        }
        else if(sp.equals(s5))
        {
            toj.setLanguage(Locale.CANADA_FRENCH);
        }
        else  if(sp.equals(s6))
        {
            toj.setLanguage(Locale.FRANCE);
        }
        else if(sp.equals(s7))
        {
            toj.setLanguage(Locale.KOREAN);
        }
        else if(sp.equals(s8))
        {
            toj.setLanguage(Locale.JAPANESE);
        }
        else
        {
            toj.setLanguage(Locale.UK);
        }

        toj.setPitch((float)pitch);
        toj.setSpeechRate((float)speechRate);


        //Toast.makeText(getApplicationContext(),"SP : "+sp,Toast.LENGTH_SHORT).show();
        String toSpeak = e.getText().toString();
        if(toSpeak == null)
        {
            toSpeak = "Nothing to Speak";
        }
        Toast.makeText(getApplicationContext(), toSpeak, Toast.LENGTH_LONG).show();
        toj.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null);
    }
}
