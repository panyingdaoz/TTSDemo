package com.example.ttsdemo;

/**
 * 语音播报接口
 *
 * @author panyingdao
 * @date 2021/4/9.
 */
public interface TtsListener {

    void playText(String playText);

    void stopSpeak();
}
