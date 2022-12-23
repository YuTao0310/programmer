package file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class ComprehensiveExercise {
    
    private static void copyFile(String srcFile, String destFile) {
        File src = new File(srcFile);
        File dest = new File(destFile);
        dest.getParentFile().mkdirs();
        try (
            FileReader fr = new FileReader(src);
            BufferedReader br = new BufferedReader(fr);
            FileWriter fw = new FileWriter(dest);
            PrintWriter pw = new PrintWriter(fw);
        ) {
            String str = null;
            while ((str = br.readLine()) != null) {
                pw.println(str);
            }
            pw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void copyFolder(String srcFolder, String destFolder) {
        File src = new File(srcFolder);
        File dest = new File(destFolder);
        dest.mkdirs();
        File[] fs = src.listFiles();
        for (File f : fs) {
            if (f.isDirectory()) {
                copyFolder(f.getAbsolutePath(), destFolder + "/" + f.getName());
            }
            if (f.isFile()) {
                copyFile(f.getAbsolutePath(), destFolder + "/" +  f.getName());
            }
        }
    }

    private static void search(File folder, String search) {
        File[] fs = folder.listFiles();
        for (File f : fs) {
            if (f.isDirectory()) {
                search(f, search);
            }
            if (f.isFile() && f.getName().endsWith(".java")) {
                try (
                    FileReader fr = new FileReader(f);
                    BufferedReader br = new BufferedReader(fr);
                ) {
                    String str = null;
                    while((str = br.readLine()) != null) {
                        if (str.contains(search)) {
                            System.out.println("找出子目标串" + search + "在文件：" + f.getAbsolutePath());
                            break;
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public static void main(String[] args) {
        copyFile("D:/Dog.java", "D:/copy.java");
        copyFolder("D:/pycode", "D:/copycode");
        search(new File("C:\\Users\\Administrator\\Desktop\\javaExample"), "Magic");
    }
}
