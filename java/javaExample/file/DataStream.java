package file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class DataStream {
    
    private static void DataInputStreamTest() {
        File f =new File("d:/lol.txt");
		try (
                FileInputStream fis  = new FileInputStream(f);
                DataInputStream dis =new DataInputStream(fis);
        ){
            boolean b= dis.readBoolean();
            int i = dis.readInt();
            String str = dis.readUTF();
            
            System.out.println("读取到布尔值:"+b);
            System.out.println("读取到整数:"+i);
            System.out.println("读取到字符串:"+str);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void DataOutputStreamTest() {
        File f =new File("d:/lol.txt");
		try (
                FileOutputStream fos  = new FileOutputStream(f);
                DataOutputStream dos =new DataOutputStream(fos);
        ){
            dos.writeBoolean(true);
            dos.writeInt(300);
            dos.writeUTF("123 this is gareen");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void DataOutputPractice1() {
        File f = new File("D:/data1.txt");
        try {
            FileWriter fw = new FileWriter(f);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write("13" + "@" + "55");
            bw.flush();
            bw.close();
            fw.close();
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);
            String str = br.readLine();
            String[] strs = str.split("@");
            System.out.println("number1: " + Integer.parseInt(strs[0]) + " number2: " + Integer.parseInt(strs[1]));
            br.close();
            fr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void DataOutputPractice2() {
        File f = new File("D:/data.txt");
        try {
            FileOutputStream fos = new FileOutputStream(f);
            DataOutputStream dos = new DataOutputStream(fos);
            dos.writeInt(13);
            dos.writeInt(55);
            dos.close();
            fos.close();
            FileInputStream fis = new FileInputStream(f);
            DataInputStream dis = new DataInputStream(fis);
            int number1 = dis.readInt();
            int number2 = dis.readInt();
            System.out.println("number1: " + number1 + " number2: " + number2);
            dis.close();
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        DataOutputStreamTest();
        DataInputStreamTest();
        DataOutputPractice1();
        DataOutputPractice2();
    }
}
