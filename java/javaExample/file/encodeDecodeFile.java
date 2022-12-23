package file;

import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;

public class encodeDecodeFile {

    public static void encodeFile(File file, File encFile) {
        encFile.getParentFile().mkdirs();
        try {
            FileReader fis = new FileReader(file, Charset.forName("UTF-8"));
            FileWriter fos = new FileWriter(encFile, Charset.forName("UTF-8"));
            char[] cs = new char[(int)file.length()];
            fis.read(cs);
            for (int i = 0; i < cs.length; i++) {
                char c = cs[i];
                if (c >= '0' && c <= '9') {
                    cs[i] = (char)('0' + (c + 1 - '0') % 10);
                } else if (c >= 'a' && c <= 'z') {
                    //cs[i] = (char)('a' + (c + 1 - 'a') % 26);
                    cs[i] = (char)('z' - ('z' - (c - 1)) % 26);
                } else if (c >= 'A' && c <= 'Z') {
                    //cs[i] = (char)('A' + (c + 1 - 'A') % 26);
                    cs[i] = (char)('Z' - ('Z' - (c - 1)) % 26);
                }
            }
            fos.write(cs);
            fis.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void decodeFile(File file, File decFile) {
        decFile.getParentFile().mkdirs();
        try {
            FileReader fis = new FileReader(file);
            FileWriter fos = new FileWriter(decFile);
            char[] cs = new char[(int)file.length()];
            fis.read(cs);
            for (int i = 0; i < cs.length; i++) {
                char c = cs[i];
                if (c >= '0' && c <= '9') {
                    cs[i] = (char)('9' - ('9' - (c- 1)) % 10);
                } else if (c >= 'a' && c <= 'z') {
                    cs[i] = (char)('a' + (c + 1 - 'a') % 26);
                    //cs[i] = (char)('z' - ('z' - (c - 1)) % 26);
                } else if (c >= 'A' && c <= 'Z') {
                    cs[i] = (char)('A' + (c + 1 - 'A') % 26);
                    //cs[i] = (char)('Z' - ('Z' - (c - 1)) % 26);
                }
            }
            fos.write(cs);
            fis.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        // 如果test.txt采用GBK编码，那么生成的文件不管用GBK还是UTF-8显示都有乱码
        // 如果test.txt采用UTF-8编码，那么生成的文件用UTF-8没有乱码，GBK有乱码
        // 这是因为字符读入和输出时采用的是Unicode方式
        File file = new File("D:/test1.txt"); 
        File encFile = new File("D:/encodeTest.txt");
        File decFile = new File("D:/decodeTest.txt");
        encodeFile(file, encFile);
        decodeFile(encFile, decFile);
    }
}