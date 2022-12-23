package multiplethread;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class FileTest {

    private static void searchFile(File f, String search) {
        try (
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);
        ) {
            String str = null;
            while((str = br.readLine()) != null) {
                if (str.contains(search)) {
                    System.out.println(Thread.currentThread() + "�ҳ���Ŀ�괮" + search + "���ļ���" + f.getAbsolutePath());
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private static void searchFolder(File folder, String search) {
        File[] fs = folder.listFiles();
        for (File f : fs) {
            if (f.isDirectory()) {
                searchFolder(f, search);
            }
            if (f.isFile() && f.getName().endsWith(".java")) {
                /** �����̷߳��� */
                /*
                Thread searchThread = new Thread() {
                    @Override
                    public void run() {
                        searchFile(f, search);
                    }
                };
               searchThread.start();
               */
                /** �̳߳ط��� */
                ThreadPoolExecutor threadPool= new ThreadPoolExecutor(10, 15, 60, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>());
                threadPool.execute(new Runnable() {
                    @Override
                    public void run() {
                        searchFile(f, search);
                    }
                });
            }
        }
    }

    private static void search(File folder, String search) {
        File[] fs = folder.listFiles();
        for (File f : fs) {
            if (f.isDirectory()) {
                search(f, search);
            }
            if (f.isFile() && f.getName().endsWith(".java")) {
                try (
                    FileReader fr = new FileReader(f);
                    BufferedReader br = new BufferedReader(fr);
                ) {
                    String str = null;
                    while((str = br.readLine()) != null) {
                        if (str.contains(search)) {
                            System.out.println("�ҳ���Ŀ�괮" + search + "���ļ���" + f.getAbsolutePath());
                            break;
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public static void main(String[] args) {
        long start, end;

        System.out.println("----------Single Thread----------");
        start = System.currentTimeMillis();
        search(new File("C:\\Users\\Administrator\\Desktop\\programmer\\javaExample"), "Magic");
        end = System.currentTimeMillis();
        long singleThreadTime = end - start;

        System.out.println("----------Multiple Thread----------");
        /** ����Ķ��߳�ͳ��ʱ�䷽ʽ�д���ȶ */
        start = System.currentTimeMillis();
        searchFolder(new File("C:\\Users\\Administrator\\Desktop\\programmer\\javaExample"), "Magic");
        end = System.currentTimeMillis();
        long multipleThreadTime = end - start;

        System.out.println("Mutliple Thread Time : " + multipleThreadTime + " , " +
                           "Single Thread TIme : " + singleThreadTime);                 
    }
}
