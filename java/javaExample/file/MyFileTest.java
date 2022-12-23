package file;
 
import java.io.File;
import java.io.IOException;
 
public class MyFileTest {
 
    public static void main(String[] args) throws IOException {
 
        File f = new File("C:/Users/Administrator/Desktop/javaExample/hh/ii/kk/ll/h.txt");
 
        // ���ַ����������ʽ�����ص�ǰ�ļ����µ������ļ������������ļ������ļ��У�
        f.list();
 
        // ���ļ��������ʽ�����ص�ǰ�ļ����µ������ļ������������ļ������ļ��У�
        // File[] fs= f.listFiles();
 
        // ���ַ�����ʽ���ػ�ȡ�����ļ���
        f.getParent();
 
        // ���ļ���ʽ���ػ�ȡ�����ļ���
        f.getParentFile();
        // �����ļ��У�������ļ���skin�����ڣ���������Ч
        f.mkdir();
 
        // �����ļ��У�������ļ���skin�����ڣ��ͻᴴ�����ļ���
        f.mkdirs();
 
        // ����һ�����ļ�,������ļ���skin�����ڣ��ͻ��׳��쳣
        f.createNewFile();
        // ���Դ���һ�����ļ�֮ǰ��ͨ�����ᴴ����Ŀ¼
        f.getParentFile().mkdirs();
 
        // �г����е��̷�c: d: e: �ȵ�
        // f.listRoots();
 
        // �h���ļ�
        f.delete();
 
        // JVM������ʱ�򣬄h���ļ�����������ʱ�ļ���ɾ��
        f.deleteOnExit();
 
    }
}
/*
package file;
  
import java.io.File;
import java.util.Date;
  
public class TestFile {
  
    public static void main(String[] args) {
  
        File f = new File("d:/LOLFolder/LOL.exe");
        System.out.println("��ǰ�ļ��ǣ�" +f);
        //�ļ��Ƿ����
        System.out.println("�ж��Ƿ���ڣ�"+f.exists());
         
        //�Ƿ����ļ���
        System.out.println("�ж��Ƿ����ļ��У�"+f.isDirectory());
          
        //�Ƿ����ļ������ļ��У�
        System.out.println("�ж��Ƿ����ļ���"+f.isFile());
          
        //�ļ�����
        System.out.println("��ȡ�ļ��ĳ��ȣ�"+f.length());
          
        //�ļ�����޸�ʱ��
        long time = f.lastModified();
        Date d = new Date(time);
        System.out.println("��ȡ�ļ�������޸�ʱ�䣺"+d);
        //�����ļ��޸�ʱ��Ϊ1970.1.1 08:00:00
        f.setLastModified(0);
          
        //�ļ�������
        File f2 =new File("d:/LOLFolder/DOTA.exe");
        f.renameTo(f2);
        System.out.println("��LOL.exe��������DOTA.exe");
         
        System.out.println("ע�⣺ ��Ҫ��D:\\LOLFolderȷʵ����һ��LOL.exe,\r\n�ſ��Կ�����Ӧ���ļ����ȡ��޸�ʱ�����Ϣ");
    }
}
*/
