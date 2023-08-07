package com.model.bstructural.Adapter.adapter;

import com.model.bstructural.Adapter.server.AdvancedMediaPlayer;
import com.model.bstructural.Adapter.server.MediaPlayer;
import com.model.bstructural.Adapter.server.impl.Mp4Player;
import com.model.bstructural.Adapter.server.impl.VlcPlayer;

/**
 * 适配器，根据不同格式播放进行不同形式的操作
 * @author 厚积薄发
 * @create 2023-05-08-16:47
 */
public class MediaAdapter implements MediaPlayer {

    AdvancedMediaPlayer advancedMusicPlayer;

    public MediaAdapter(String audioType){
        if("vlc".equalsIgnoreCase(audioType) ){
            advancedMusicPlayer = new VlcPlayer();
        } else if ("mp4".equalsIgnoreCase(audioType)){
            advancedMusicPlayer = new Mp4Player();
        }
    }

    /**
     *
     * @param audioType 录音带
     * @param fileName 文件名
     */
    @Override
    public void play(String audioType, String fileName) {
        if("vlc".equalsIgnoreCase(audioType)){
            advancedMusicPlayer.playVlc(fileName);
        }else if("mp4".equalsIgnoreCase(audioType)){
            advancedMusicPlayer.playMp4(fileName);
        }
    }
}