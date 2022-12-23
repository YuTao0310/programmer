package file;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;

public class ChineseStreamPractice {

    private static void byteStreamToEncodingString() {
        try {
            String charset = "UTF-8";
            byte[] all = {(byte)0xE5, (byte)0xB1, (byte)0x8C};
            String str = new String(all, charset);
            for (byte b : all) 
            {
                int i = b&0x000000ff;  //只取16进制的后两位
                System.out.print(Integer.toHexString(i) + ", ");
            }
            System.out.println(charset + " : " + str);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private static void removeRom() {
        try {
            File f = new File("D:/test1.txt");
            FileInputStream fis = new FileInputStream(f);
            byte[] cs = new byte[(int)f.length()];
            fis.read(cs);
            String str = new String(cs, "UTF-8");
            showCode(str, "UTF-8");
            byte[] removeCs = Arrays.copyOfRange(cs, 3, (int)f.length());
            str = new String(removeCs, "UTF-8");
            showCode(str, "UTF-8");
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        
    }

    private static void showCode(String str, String encode) {
		try {
			System.out.printf("字符: \"%s\" 的在编码方式%s下的十六进制值是%n", str, encode);
			byte[] bs = str.getBytes(encode);

			for (byte b : bs) {
				int i = b&0xff;
				System.out.print(Integer.toHexString(i) + "\t");
			}
			System.out.println();
			System.out.println();
		} catch (UnsupportedEncodingException e) {
			System.out.printf("UnsupportedEncodingException: %s编码方式无法解析字符%s\n", encode, str);
		}
	}
    
    public static void main(String[] args) {
        byteStreamToEncodingString();
        removeRom();
        showCode("中", "GBK");
    }
}
