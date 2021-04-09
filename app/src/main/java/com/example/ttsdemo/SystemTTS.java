package com.example.ttsdemo;

import android.content.Context;
import android.speech.tts.TextToSpeech;
import android.speech.tts.UtteranceProgressListener;
import android.util.Log;

import java.util.Locale;

/**
 * 语音播报类
 *
 * @author panyingdao
 * @date 2021/4/9.
 */
//@SuppressLint("NewApi")
public class SystemTTS extends UtteranceProgressListener implements TtsListener, TextToSpeech.OnUtteranceCompletedListener {

    private static SystemTTS singleton;
    // 系统语音播报类
    private final TextToSpeech textToSpeech;
    private boolean isSuccess = true;
    private static final String TAG = "SystemTTS";

    public static SystemTTS getInstance(Context context) {
        if (singleton == null) {
            synchronized (SystemTTS.class) {
                if (singleton == null) {
                    singleton = new SystemTTS(context);
                }
            }
        }
        return singleton;
    }

    private SystemTTS(Context context) {
        Context mContext = context.getApplicationContext();
        textToSpeech = new TextToSpeech(mContext, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {
                //系统语音初始化成功
                if (i == TextToSpeech.SUCCESS) {
                    int result = textToSpeech.setLanguage(Locale.CHINA);
                    textToSpeech.setPitch(1.0f);// 设置音调，值越大声音越尖（女生），值越小则变成男声,1.0是常规
                    textToSpeech.setSpeechRate(1.0f);
                    textToSpeech.setOnUtteranceProgressListener(SystemTTS.this);
                    textToSpeech.setOnUtteranceCompletedListener(SystemTTS.this);
                    if (result == TextToSpeech.LANG_MISSING_DATA
                            || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                        //系统不支持中文播报
                        isSuccess = false;
                    }
                }

            }
        });
    }

    @Override
    public void playText(String playText) {
        if (!isSuccess) {
            return;
        }
        if (textToSpeech != null) {
            textToSpeech.speak(playText,
                    TextToSpeech.QUEUE_ADD, null, null);
        }
    }

    @Override
    public void stopSpeak() {
        if (textToSpeech != null) {
            textToSpeech.stop();
        }
    }


    //播报完成回调
    @Override
    public void onUtteranceCompleted(String utteranceId) {
    }

    @Override
    public void onStart(String utteranceId) {
        Log.i(TAG,"开始播报");
    }

    @Override
    public void onDone(String utteranceId) {
        Log.i(TAG,"播报完成");
    }

    @Override
    public void onError(String utteranceId) {
        Log.i(TAG,"播报异常");
    }
}
