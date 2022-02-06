package file;

import java.io.UnsupportedEncodingException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class ChineseByteStream {

	public static void main(String[] args) {
		String str = "中";
		showCode(str);
        fileToEncodingStream();
	}

	private static void showCode(String str) {
		String[] encodes = { "BIG5", "GBK", "GB2312", "UTF-8", "UTF-16", "UTF-32" };
		for (String encode : encodes) {
			showCode(str, encode);
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

    private static void fileToEncodingStream() {
        File f = new File("D:/test1.txt");
        try (FileInputStream fis = new FileInputStream(f);) {
            byte[] all = new byte[(int) f.length()];
            fis.read(all);
  
            //文件中读出来的数据是
            System.out.println("文件中读出来的数据是：");
            for (byte b : all) 
            {
                int i = b&0xff;  //只取16进制的后两位
                System.out.print(Integer.toHexString(i) + ", ");
            }
            // byte[] all1 = {(byte)0xad, 62, 63, 64, (byte)0xd, (byte)0xa, 61};
            System.out.println('\n' + "把这个数字，放在GBK的棋盘上去：");
            String str = new String(all,"GBK"); //改成GBK模式显示
            System.out.println(str);
        } catch (IOException e) {

            e.printStackTrace();
        }
  
    }
}