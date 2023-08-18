package com.utils.file;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.zip.GZIPOutputStream;

/**
 * 文件拆分
 *
 */
public class FileSplitUtil {
    private final Logger LOG = LoggerFactory.getLogger(this.getClass());
    private static final int BUFFER_SIZE = 1024 * 1024 * 4; // 缓冲区大小，可根据需要调整

    /**
     * 拆分文件 并 Gzip
     *
     * sourceFilePath  原文件地址
     * targetDirectory  输出文件的地址
     * targetFileNamePre  输出文件的文件名前缀
     * maxFileSize  每个分割文件的最大大小
     */
    public static List<String> splitFile(String sourceFilePath, String targetDirectory, String targetFileNamePre, long maxFileSize) {
        try (BufferedReader reader = new BufferedReader(new FileReader(sourceFilePath))) {
            long fileSize = new File(sourceFilePath).length();
            int fileCount = (int) Math.ceil((double) fileSize / maxFileSize);

            ArrayList<String> gzipFilePaths = new ArrayList<>();

            for (int i = 0; i < fileCount; i++) {
                String fileNumber = String.format("%04d", i + 1);
                String targetFilePath = targetDirectory + File.separator + targetFileNamePre + fileNumber + ".txt";
                BufferedWriter writer = new BufferedWriter(new FileWriter(targetFilePath), BUFFER_SIZE);

                long bytesWritten = 0;
                String line;
                while ((line = reader.readLine()) != null) {
                    bytesWritten += line.getBytes().length;
                    writer.write(line);
                    writer.newLine();

                    if (bytesWritten >= maxFileSize) {
                        break;
                    }
                }
                writer.close();

                //压缩
                String compressedFilePath = targetDirectory + File.separator + targetFileNamePre + fileNumber + ".txt.gz";
                FileUtil.gzipCompress(targetFilePath, compressedFilePath);
                gzipFilePaths.add(compressedFilePath);

                // 删除生成的txt文件
                File txtFile = new File(targetFilePath);
                txtFile.delete();
            }
            //返回所有的文件地址
            return gzipFilePaths;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 根据行数进行txt文件拆分
     *
     * sourceFilePath  原文件地址
     * targetDirectory  输出文件的地址
     * targetFileNamePre  输出文件的文件名前缀
     * linesPerFile  文件分割的行数
     */
    public static List<String> splitFile2(String sourceFilePath, String targetDirectory, String targetFileNamePre, int linesPerFile) {
        try (BufferedReader reader = new BufferedReader(new FileReader(sourceFilePath))) {
            int fileCount = 1;
            int lineCount = 0;
            String line;
            List<String> splitFilePaths = new ArrayList<>();
            BufferedWriter writer = null;

            while ((line = reader.readLine()) != null) {
                if (lineCount % linesPerFile == 0) {
                    if (writer != null) {
                        writer.close();
                    }
                    String targetFilePath = targetDirectory + File.separator + targetFileNamePre + fileCount + ".txt";
                    splitFilePaths.add(targetFilePath);
                    writer = new BufferedWriter(new FileWriter(targetFilePath));
                    fileCount++;
                }

                writer.write(line);
                writer.newLine();
                lineCount++;
            }

            if (writer != null) {
                writer.close();
            }

            return splitFilePaths;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }


    //对拆分好的文件进行多线程打包 gzip
    public static void compressFiles(List<String> fileAddresses) {
        System.out.println("开始进行文件打包");
        int numThreads = Math.min(fileAddresses.size(), Runtime.getRuntime().availableProcessors());
        ExecutorService executor = Executors.newFixedThreadPool(numThreads);
        List<String> gzipFilePaths = new ArrayList<>();

        for (String fileAddress : fileAddresses) {
            executor.execute(() -> {
                String gzipFilePath = compressFile(fileAddress);
                if (gzipFilePath != null) {
                    synchronized (gzipFilePaths) {
                        gzipFilePaths.add(gzipFilePath);
                    }
                }
            });
        }

        executor.shutdown();

        while (!executor.isTerminated()) {
            Thread.yield();
        }

        for (String fileAddress : fileAddresses) {
            File originalFile = new File(fileAddress);
            originalFile.delete();
        }

        System.out.println("所有文件已经压缩完成。压缩文件路径列表：");
        for (String filePath : gzipFilePaths) {
            System.out.println(filePath);
        }
    }

    //文件打gzip
    public static String compressFile(String fileAddress) {
        try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(fileAddress));
             GZIPOutputStream gzipOutputStream = new GZIPOutputStream(new FileOutputStream(fileAddress + ".gz"))) {

            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = bis.read(buffer)) != -1) {
                gzipOutputStream.write(buffer, 0, bytesRead);
            }
            gzipOutputStream.finish();

            return fileAddress + ".gz";
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }


    //获取txt文件数据的总行数
    public static int countLines(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            int count = 0;
            while (reader.readLine() != null) {
                count++;
            }
            return count;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
