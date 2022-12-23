package file;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class SystemIn {

    private static void ScannerStringTest() {
        Scanner s = new Scanner(System.in);
            
        String line = s.nextLine();
    
        System.out.println(line);
       
        s.close(); 
    }

    private static void ScannerIntTest() {
        Scanner s = new Scanner(System.in);
        int a = s.nextInt();
        System.out.println("��һ��������"+a);
        int b = s.nextInt();
        System.out.println("�ڶ���������"+b);
        s.close();        
    }

    private static void ScannerPractice() {
        File templateFile = new File("D:/template.java");
        Scanner s = new Scanner(System.in);
        System.out.println("������������ƣ�");
        String className = s.nextLine();
        System.out.println("���������Ե�����??");
        String typeName = s.nextLine();
        System.out.println("���������Ե�����??");
        String propertyName = s.nextLine();
        String upropertyName = propertyName.substring(0, 1).toUpperCase() + propertyName.substring(1);
        File fianlFile = new File("D:/" + className + ".java");
        try (
            FileReader fr = new FileReader(templateFile);
            FileWriter fw = new FileWriter(fianlFile);
        ) {
            char[] cs = new char[(int)templateFile.length()];
            fr.read(cs);
            String str = new String(cs);
            str = str.replaceAll("@class@", className);
            str = str.replaceAll("@type@", typeName);
            str = str.replaceAll("@property@", propertyName);
            str = str.replaceAll("@Uproperty@", upropertyName);
            fw.write(str);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            s.close();
        }
    }

    public static void main(String[] args) {
        ScannerPractice();
        ScannerStringTest();
        ScannerIntTest();
    }
}
