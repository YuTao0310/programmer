package file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class BufferStream {

    private static void BufferedInputStreamTest() {
        // 准备文件lol.txt其中的内容是
        // garen kill teemo
        // teemo revive after 1 minutes
        // teemo try to garen, but killed again
        File f = new File("d:/lol.txt");
        // 创建文件字符流
        // 缓存流必须建立在一个存在的流的基础上
        try (
        		FileReader fr = new FileReader(f); 
        		BufferedReader br = new BufferedReader(fr);
        	) 
        {
            while (true) {
                // 一次读一行
                String line = br.readLine();
                if (null == line)
                    break;
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void BufferedOutpurStreamTest() {
        // 向文件lol2.txt中写入三行语句
        File f = new File("d:/lol2.txt");
         
        try (
                // 创建文件字符流
                FileWriter fw = new FileWriter(f);
                // 缓存流必须建立在一个存在的流的基础上               
                PrintWriter pw = new PrintWriter(fw);               
        ) {
            pw.println("garen kill teemo");
            pw.println("teemo revive after 1 minutes");
            pw.println("teemo try to garen, but killed again");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void BufferedFlushTest() {
        //向文件lol2.txt中写入三行语句
        File f =new File("d:/lol2.txt");
        //创建文件字符流
        //缓存流必须建立在一个存在的流的基础上
        try (FileWriter fr = new FileWriter(f);PrintWriter pw = new PrintWriter(fr);) {
            pw.println("garen kill teemo");
            //强制把缓存中的数据写入硬盘，无论缓存是否已满
                pw.flush();            
            pw.println("teemo revive after 1 minutes");
                pw.flush();
            pw.println("teemo try to garen, but killed again");
                pw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void removeComments(File javaFile) {
        File remFile = new File(javaFile.getParent()+ "/remFile.java");
        try {
            FileReader fr = new FileReader(javaFile);
            BufferedReader br = new BufferedReader(fr);
            FileWriter fw = new FileWriter(remFile);
            PrintWriter pw = new PrintWriter(fw);
            String str = null;
            while ((str = br.readLine()) != null) {
                if (!str.trim().startsWith("//")) {
                    pw.println(str);
                }
            }
            pw.flush();
            // remFile.renameTo(javaFile);
            br.close();
            fw.close();
            fr.close();
            pw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        BufferedInputStreamTest();
        BufferedOutpurStreamTest();
        BufferedFlushTest();
        removeComments(new File("D:/test.java"));
    }
    
}
