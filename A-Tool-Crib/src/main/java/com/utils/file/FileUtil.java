package com.utils.file;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.GZIPOutputStream;

/**
 * 文件相关工具包
 *
 */
public class FileUtil {
    private final Logger LOG = LoggerFactory.getLogger(this.getClass());

    //压缩文件为gz
    public static void gzipCompress(String sourceFilePath, String targetFilePath) throws IOException {
        System.out.println("----------开始压缩文件：" + sourceFilePath + "  -->到：" + targetFilePath+"   ----------");
        FileInputStream inputStream = new FileInputStream(sourceFilePath);
        FileOutputStream outputStream = new FileOutputStream(targetFilePath);
        GZIPOutputStream gzipOutputStream = new GZIPOutputStream(outputStream);
        byte[] buffer = new byte[1024];
        int length;
        while ((length = inputStream.read(buffer)) > 0) {
            gzipOutputStream.write(buffer, 0, length);
        }
        gzipOutputStream.close();
        outputStream.close();
        inputStream.close();
        System.out.println("----------压缩文件：" + sourceFilePath + "  -->到：" + targetFilePath +"  已完成----------");
    }


    //删除文件
    public static void delFile(String sourceFilePath)  {
        System.out.println("----------开始删除文件："+sourceFilePath+"   ----------");
        // 创建File对象
        File file = new File(sourceFilePath);
        // 检查文件是否存在
        if (file.exists()) {
            // 删除文件
            file.delete();
            System.out.println("----------已删除文件："+sourceFilePath+"   ----------");
        }else {
            System.out.println("----------文件不存在："+sourceFilePath+"   ----------");
        }
    }

}
