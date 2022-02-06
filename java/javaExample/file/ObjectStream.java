package file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class ObjectStream {

    private static void ObjectStreamTest() {
        //创建一个Hero garen
        //要把Hero对象直接保存在文件上，务必让Hero类实现Serializable接口
        Hero h = new Hero();
        h.name = "garen";
        h.hp = 616;
         
        //准备一个文件用于保存该对象
        File f =new File("d:/garen.lol");

        try(
	        //创建对象输出流
	        FileOutputStream fos = new FileOutputStream(f);
	        ObjectOutputStream oos =new ObjectOutputStream(fos);
        	//创建对象输入流        		
            FileInputStream fis = new FileInputStream(f);
            ObjectInputStream ois =new ObjectInputStream(fis);
        ) {
            oos.writeObject(h);
            Hero h2 = (Hero) ois.readObject();
            System.out.println(h2.name);
            System.out.println(h2.hp);
              
        } catch (IOException e) {
            
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
           
            e.printStackTrace();
        }
    }

    private static void ObjectStreamPractice() {

        File f = new File("D:/heros.lol");
         Hero[] heros = new Hero[10];

         for (int i = 0; i < 10; i++) {
             heros[i] = new Hero();
             heros[i].name = "" + i;
             heros[i].hp = 600 + i * 10;
         }     

         try (
	        //创建对象输出流
	        FileOutputStream fos = new FileOutputStream(f);
	        ObjectOutputStream oos =new ObjectOutputStream(fos);
        	//创建对象输入流        		
            FileInputStream fis = new FileInputStream(f);
            ObjectInputStream ois =new ObjectInputStream(fis);             
         ) {
            for (int i = 0; i < 10; i++) {
                oos.writeObject(heros[i]);
            }
            for (int i = 0; i < 10; i++) {
                Hero h = (Hero)ois.readObject();
                System.out.println("hero: " + h.name + " hp: " + h.hp);
            }
         } catch (IOException e) {
             e.printStackTrace();
         } catch (ClassNotFoundException e) {
             e.printStackTrace();
         }
    }
    public static void main(String[] args) {
        ObjectStreamTest();
        ObjectStreamPractice();
    }
}
