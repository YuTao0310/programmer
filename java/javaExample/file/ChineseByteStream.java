package file;

import java.io.UnsupportedEncodingException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class ChineseByteStream {

	public static void main(String[] args) {
		String str = "��";
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
			System.out.printf("�ַ�: \"%s\" ���ڱ��뷽ʽ%s�µ�ʮ������ֵ��%n", str, encode);
			byte[] bs = str.getBytes(encode);

			for (byte b : bs) {
				int i = b&0xff;
				System.out.print(Integer.toHexString(i) + "\t");
			}
			System.out.println();
			System.out.println();
		} catch (UnsupportedEncodingException e) {
			System.out.printf("UnsupportedEncodingException: %s���뷽ʽ�޷������ַ�%s\n", encode, str);
		}
	}

    private static void fileToEncodingStream() {
        File f = new File("D:/test1.txt");
        try (FileInputStream fis = new FileInputStream(f);) {
            byte[] all = new byte[(int) f.length()];
            fis.read(all);
  
            //�ļ��ж�������������
            System.out.println("�ļ��ж������������ǣ�");
            for (byte b : all) 
            {
                int i = b&0xff;  //ֻȡ16���Ƶĺ���λ
                System.out.print(Integer.toHexString(i) + ", ");
            }
            // byte[] all1 = {(byte)0xad, 62, 63, 64, (byte)0xd, (byte)0xa, 61};
            System.out.println('\n' + "��������֣�����GBK��������ȥ��");
            String str = new String(all,"GBK"); //�ĳ�GBKģʽ��ʾ
            System.out.println(str);
        } catch (IOException e) {

            e.printStackTrace();
        }
  
    }
}