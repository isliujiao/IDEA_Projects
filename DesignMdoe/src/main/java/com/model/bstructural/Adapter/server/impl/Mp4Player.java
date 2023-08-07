package com.model.bstructural.Adapter.server.impl;

import com.model.bstructural.Adapter.server.AdvancedMediaPlayer;

/**
 * Mp4格式播放器
 * @author 厚积薄发
 * @create 2023-05-08-16:45
 */
public class Mp4Player implements AdvancedMediaPlayer {

    @Override
    public void playVlc(String fileName) {
        //什么也不做
    }

    @Override
    public void playMp4(String fileName) {
        System.out.println("Playing mp4 file. Name: "+ fileName);
    }
}