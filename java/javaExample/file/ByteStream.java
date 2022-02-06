package file;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.IOException;

public class ByteStream {

    private static final String fileName = "/part";
    
    public static void writeDataToFile(byte[] data, File f) {
        f.getParentFile().mkdirs();
        try {
            FileOutputStream fos = new FileOutputStream(f);
            fos.write(data);
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void splitFile(File dir, File file) {
        try (FileInputStream fis = new FileInputStream(file)) {
            dir.mkdirs();
            int splitLength = 100000;
            
            long splitSum = file.length() / splitLength;
            byte[] all = new byte[splitLength];
            byte[] remain = new byte[(int)(file.length() % splitLength)];
            long i;
            
            for (i =  0; i < splitSum; i++) {
                FileOutputStream fos = new FileOutputStream(new File(dir.getAbsolutePath() + fileName + i));
                fis.read(all);
                fos.write(all);
                fos.close();
            }
            
            FileOutputStream fos = new FileOutputStream(new File(dir.getAbsolutePath() + fileName + i));
            fis.read(remain);
            fos.write(remain);
            fos.close();
   
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void mergeFile(File dir, File fianlFile) {
        FileOutputStream fos = null;
        try {
            fianlFile.getParentFile().mkdirs();
            fos = new FileOutputStream(fianlFile);
            int splitNum = dir.list().length;
            for (int i = 0; i < splitNum; i++) {
                File f = new File(dir.getAbsolutePath() + fileName + i);
                FileInputStream fis = new FileInputStream(f);
                byte[] all = new byte[(int)f.length()];
                fis.read(all);
                fos.write(all);
                fis.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != fos) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void reverseFile(File revFile, File file) {
        revFile.getParentFile().mkdirs();
        try {
            FileInputStream fis = new FileInputStream(file);
            FileOutputStream fos = new FileOutputStream(revFile);
            int fileLength = (int)file.length();
            byte[] all = new byte[fileLength];
            for(long i = 0; i < fileLength; i++) {
                fis.read(all, (int)(fileLength - 1 - i), 1);
            }
            fos.write(all);

            fis.close();
            fos.close(); 
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        File f = new File("D:/charaterstream/test.txt");
        byte[] data = {'t', 'y', 'y', 'y', 'd', 's'};
        //byte[] data = {12, 13};
        writeDataToFile(data, f);
        f = new File("D:/林俊杰-Wonderland.mp3");
        File dir = new File("D:/splitFile");
        splitFile(dir, f);
        File merFile = new File("D:/merge-wonderland.mp3");
        mergeFile(dir, merFile);
        // File revFile = new File("D:/rev-wonderland.mp3");
        // reverseFile(revFile, f);

        f = new File("D:/test.txt");
        dir = new File("D:/ChineseSplitFile");
        merFile = new File("D:/merge-test.txt");
        splitFile(dir, f);
        mergeFile(dir, merFile);
    }
}
