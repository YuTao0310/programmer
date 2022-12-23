package file;

import java.io.File;
import java.io.IOException;

public class TraverseFile {

    static class Result {
        long min;
        long max;
        File minFile;
        File maxFile;
    }

    // ����ѭ����ʽ�����������ļ����µ������ļ�
    static void Traverse1(File file) throws IOException {
        File[] fs = file.listFiles();
        long min = Long.MAX_VALUE;
        long max = -20;
        File minFile = file;
        File maxFile = file;
        int flag = 0;
        for (File f : fs) {
            if (f.isFile()) {
                long len = f.length();
                if (len < min && len >0) {
                    min = len;
                    minFile = f;
                    flag = 1;
                }
                if (len > max) {
                    max = len;
                    maxFile = f;
                    flag = 1;
                }
            }
        }
        if (flag == 0) {
            System.out.println("û�������������ļ�");;
        } else {
            System.out.println("min file is " + minFile + "the length is " + min);
            System.out.println("max file is " + maxFile + "the length is " + max);
        }
    }

    // ���õݹ鷽ʽ�����������ļ���
    static Result Traverse2(File file) throws IOException{
        File[] fs = file.listFiles();
        long min = Long.MAX_VALUE;
        long max = -20;
        File minFile = file;
        File maxFile = file;
        if (null != fs) {
            for (File f : fs) {
                if (f.isFile()) {
                    long len = f.length();
                    if (len < min && len >0) {
                        min = len;
                        minFile = f;
                    }
                    if (len > max) {
                        max = len;
                        maxFile = f;
                    }
                }
                if (f.isDirectory()) {
                    Result r = Traverse2(f);
                    if (r.min < min && r.min >0) {
                        min = r.min;
                        minFile = r.minFile;
                    }
                    if (r.max > max) {
                        max = r.max;
                        maxFile = r.maxFile;
                    }
                }  
            }
        }

        Result res = new Result();
        res.min = min;
        res.max = max;
        res.minFile = minFile;
        res.maxFile = maxFile;
        return res;     
    }

    public static void main(String[] args) {
        // File file = new File("c:/windows");
        File file = new File("c:/Users/Administrator/Desktop/javaExample");
        try {
            Traverse1(file);
            Result res = Traverse2(file);
            System.out.println("min file is " + res.minFile + "the length is " + res.min);
            System.out.println("max file is " + res.maxFile + "the length is " + res.max);        
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

// ������������������ʵ�ֵݹ飬������������ܹ����ٴ�����ظ����֣���ʵ��Ӧ���и��ӹ㷺
/*
package org.example;
?
import com.sun.deploy.util.ArrayUtil;
?
import java.io.File;
import java.util.ArrayList;
?
public class TestFile {
????public static void main(String[] args) {
????????File f = new File("D:/MongoDB");
?
????????A a = new A();
????????a.getmaxormin(f);
?
????????System.out.println("�����ļ���" + a.maxfile + ",���С��" + a.maxsize + "�ֽ�");
????????System.out.println("��С���ļ���" + a.minfile + ",���С��" + a.minsize + "�ֽ�");
?
????}
?
class A {
????static long maxsize = 0;
????static long minsize = Integer.MAX_VALUE;
????static File maxfile = null;
????static File minfile = null;
????public void getmaxormin(File f) {
????????File[] l = f.listFiles();
????????for (int i = 0; i < l.length; i++) {
????????????if (l[i].isDirectory()) {
????????????????System.out.println(l[i]);
????????????????this.getmaxormin(l[i]);
????????????} else {
???????????????//����������ļ�
????????????????if (l[i].length() > this.maxsize) {
????????????????????this.maxsize = l[i].length();
????????????????????this.maxfile = l[i];
????????????????????System.out.println(l[i]);
????????????????}
????????????????//��������С�ļ���ע��ȥ��0�ֽڵ��ļ�
????????????????if (l[i].length() < this.minsize && l[i].length() > 0) {
????????????????????this.minsize = l[i].length();
????????????????????this.minfile = l[i];
????????????????????System.out.println(l[i]);
????????????????}
????????????}
????????}
????}
}
*/