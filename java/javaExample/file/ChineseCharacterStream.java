
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
		System.out.println("默认编码方式:"+Charset.defaultCharset());
		//FileReader得到的是字符，所以一定是已经把字节根据某种编码识别成了字符了
		//而FileReader使用的编码方式是Charset.defaultCharset()的返回值，如果是中文的操作系统，就是GBK
		try (FileReader fr = new FileReader(f)) {
			char[] cs = new char[(int) f.length()];
			fr.read(cs);
			System.out.printf("FileReader会使用默认的编码方式%s,识别出来的字符是：%n",Charset.defaultCharset());
			System.out.println(new String(cs));
		} catch (IOException e) {
			e.printStackTrace();
		}

		try (InputStreamReader isr = new InputStreamReader(new FileInputStream(f),Charset.forName("GBK"))) {
			char[] cs = new char[(int) f.length()];
			isr.read(cs);
			System.out.printf("InputStreamReader 指定编码方式GBK,识别出来的字符是：%n");
			System.out.println(new String(cs));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try (FileReader isr = new FileReader(f, Charset.forName("UTF-8"))) {
			char[] cs = new char[(int) f.length()];
			isr.read(cs);
			System.out.printf("InputStreamReader 指定编码方式UTF-8,识别出来的字符是：%n");
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

