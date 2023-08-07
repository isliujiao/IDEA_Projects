package com.model.bstructural.Adapter.server;

/**
 * 高级媒体播放机
 *
 * @author 厚积薄发
 * @create 2023-05-08-16:40
 */
public interface AdvancedMediaPlayer {
    /**
     * vlc格式播放器
     *
     * @param fileName 文件名
     */
    public void playVlc(String fileName);

    /**
     * mp4格式播放器
     *
     * @param fileName 文件名
     */
    public void playMp4(String fileName);
}