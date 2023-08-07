package com.model.bstructural.Proxy;

import com.model.bstructural.Proxy.server.Image;

/**
 * @author 厚积薄发
 * @create 2023-05-17-11:04
 */
public class ImageImpl implements Image {

    private String fileName;

    public ImageImpl(String fileName){
        this.fileName = fileName;
        loadFromDisk(fileName);
    }

    @Override
    public void display() {
        System.out.println("Displaying " + fileName);
    }

    private void loadFromDisk(String fileName){
        System.out.println("Loading " + fileName);
    }
}