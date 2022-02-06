package exception;

import java.io.File;
import java.io.FileInputStream;

public class TestException1 {
 
    public static void main(String[] args) throws Throwable {
        method();
    }
     
    private static void method() throws Throwable {
        File f = new File("d:/LOL.exe");
 
        FileInputStream fis =  new FileInputStream(f);
        fis.close();
        
        //使用Throwable进行异常捕捉
    }
     
}
