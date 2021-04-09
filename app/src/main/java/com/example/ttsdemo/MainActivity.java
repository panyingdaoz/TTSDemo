package com.example.ttsdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

/**
 * 语音播报类
 *
 * @author panyingdao
 * @date 2021/4/9.
 */
public class MainActivity extends AppCompatActivity {


    private static final String TAG = "MainActivity";
    private SystemTTS mSpeech = null;
    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSpeech = SystemTTS.getInstance(getApplicationContext());
        mTextView=findViewById(R.id.text);
        mTextView.setOnClickListener(v -> mSpeech.playText("你好！我只TTS小智"));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mSpeech.stopSpeak();
    }
}