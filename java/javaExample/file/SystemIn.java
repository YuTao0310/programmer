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
        System.out.println("第一个整数："+a);
        int b = s.nextInt();
        System.out.println("第二个整数："+b);
        s.close();        
    }

    private static void ScannerPractice() {
        File templateFile = new File("D:/template.java");
        Scanner s = new Scanner(System.in);
        System.out.println("请输入类的名称：");
        String className = s.nextLine();
        System.out.println("请输入属性的类型??");
        String typeName = s.nextLine();
        System.out.println("请输入属性的名称??");
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
