package com.model.bstructural.Adapter.server;

/**
 * 媒体播放机
 * @author 厚积薄发
 * @create 2023-05-08-16:40
 */
public interface MediaPlayer {
    /**
     * @param audioType 录音带
     * @param fileName 文件名
     */
    public void play(String audioType, String fileName);
}