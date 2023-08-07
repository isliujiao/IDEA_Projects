package com.model.bstructural.Proxy;

import com.model.bstructural.Proxy.server.Image;

/**
 * @author 厚积薄发
 * @create 2023-05-17-11:04
 */
public class ProxyImage implements Image {

    private ImageImpl realImage;
    private String fileName;

    public ProxyImage(String fileName){
        this.fileName = fileName;
    }

    @Override
    public void display() {
        if(realImage == null){
            realImage = new ImageImpl(fileName);
        }
        realImage.display();
    }
}