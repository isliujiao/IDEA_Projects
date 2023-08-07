package com.model.bstructural.Adapter;

import com.model.bstructural.Adapter.adapter.MediaAdapter;
import com.model.bstructural.Adapter.server.MediaPlayer;

/**
 * @author 厚积薄发
 * @create 2023-05-08-16:48
 */
public class AudioPlayer implements MediaPlayer {
    MediaAdapter mediaAdapter;

    @Override
    public void play(String audioType, String fileName) {

        //播放 mp3 音乐文件的内置支持
        if ("mp3".equalsIgnoreCase(audioType)) {
            System.out.println("Playing mp3 file. Name: " + fileName);
        }

        //mediaAdapter 提供了播放其他文件格式的支持
        else if ("vlc".equalsIgnoreCase(audioType) || "mp4".equalsIgnoreCase(audioType)) {
            mediaAdapter = new MediaAdapter(audioType);
            mediaAdapter.play(audioType, fileName);
        } else {
            System.out.println("Invalid media. " + audioType + " format not supported");
        }
    }
}