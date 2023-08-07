package com.example.javatest;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.StringUtils;

import javax.imageio.ImageIO;
import javax.swing.tree.TreeNode;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

class JavaTestApplicationTests {


    public String imgTime = new SimpleDateFormat("yyyyMMddHHssmm").format(new Date());

    @Test
    void t() throws InterruptedException {
        tt();
    }

    void tt() throws InterruptedException {
        while (true){
            Thread.sleep(1000);
            System.out.println(imgTime);
        }
    }

    public boolean isIsomorphic(String s, String t) {
        if(s.length() != t.length()){
            return false;
        }
        Set<Object> set = new HashSet<>();
        HashMap<Object,Object> map = new HashMap<>();
        char step = 'a';
        char[] box = new char[s.length()];
        for(int i = 0; i < s.length(); i++){
            if(!set.contains(s.charAt(i))){
                box[i] = step;
                set.add(s.charAt(i));
                map.put(s.charAt(i),step++);
            }else{
                box[i] = (char) map.get(s.charAt(i));
            }
        }
        set = new HashSet<>();
        map = new HashMap<>();
        step = 'a';
        char[] box2 = new char[s.length()];
        for(int i = 0; i < t.length(); i++){
            if(!set.contains(t.charAt(i))){
                box2[i] = step;
                set.add(t.charAt(i));
                map.put(t.charAt(i),step++);
            }else{
                box2[i] = (char) map.get(t.charAt(i));
            }
        }
        for(int i = 0; i < s.length();i++){
            if(box[i] != box2[i]){
                return false;
            }
        }
        return true;
    }

}
