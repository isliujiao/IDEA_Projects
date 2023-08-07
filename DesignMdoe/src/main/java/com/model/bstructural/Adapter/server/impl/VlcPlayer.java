package com.model.bstructural.Adapter.server.impl;

import com.model.bstructural.Adapter.server.AdvancedMediaPlayer;

/**
 * vlc格式播放器
 * @author 厚积薄发
 * @create 2023-05-08-16:40
 */
public class VlcPlayer implements AdvancedMediaPlayer {
    @Override
    public void playVlc(String fileName) {
        System.out.println("Playing vlc file. Name: "+ fileName);
    }

    @Override
    public void playMp4(String fileName) {
        //什么也不做
    }
}