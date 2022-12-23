
package file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

public class ChineseCharacterStream {

	public static void main(String[] args) throws UnsupportedEncodingException, FileNotFoundException {
		File f = new File("D:\\test1.txt");
		System.out.println("Ĭ�ϱ��뷽ʽ:"+Charset.defaultCharset());
		//FileReader�õ������ַ�������һ�����Ѿ����ֽڸ���ĳ�ֱ���ʶ������ַ���
		//��FileReaderʹ�õı��뷽ʽ��Charset.defaultCharset()�ķ���ֵ����������ĵĲ���ϵͳ������GBK
		try (FileReader fr = new FileReader(f)) {
			char[] cs = new char[(int) f.length()];
			fr.read(cs);
			System.out.printf("FileReader��ʹ��Ĭ�ϵı��뷽ʽ%s,ʶ��������ַ��ǣ�%n",Charset.defaultCharset());
			System.out.println(new String(cs));
		} catch (IOException e) {
			e.printStackTrace();
		}

		try (InputStreamReader isr = new InputStreamReader(new FileInputStream(f),Charset.forName("GBK"))) {
			char[] cs = new char[(int) f.length()];
			isr.read(cs);
			System.out.printf("InputStreamReader ָ�����뷽ʽGBK,ʶ��������ַ��ǣ�%n");
			System.out.println(new String(cs));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try (FileReader isr = new FileReader(f, Charset.forName("UTF-8"))) {
			char[] cs = new char[(int) f.length()];
			isr.read(cs);
			System.out.printf("InputStreamReader ָ�����뷽ʽUTF-8,ʶ��������ַ��ǣ�%n");
			System.out.println(new String(cs));
		} catch (IOException e) {
			e.printStackTrace();
		}

		try (
			FileReader fr =  new FileReader(f, Charset.forName("GBK"));
			BufferedReader br = new BufferedReader(fr);
			FileWriter fw = new FileWriter("D:/output.txt", Charset.forName("GBK"));
			PrintWriter pw = new PrintWriter(fw);
		) {
			String str = null;
			while((str = br.readLine()) != null) {
				pw.println(str);
			}
			pw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

