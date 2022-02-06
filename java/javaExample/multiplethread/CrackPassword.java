package multiplethread;
import java.util.Scanner;
import java.util.LinkedList;
import java.util.List;

public class CrackPassword {
    private static final List<String> codeAll = new LinkedList<>();
    /** 
     * 1、使用for循环来进行破解，本代码采用的方式
     * 2、使用递归的思想，见password子文件夹下文件
     * */
    private static String enumerationCrack(String s){
        String dict = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        int length = s.length();
        long total = (long)Math.pow(62, length);
        for (long index = 1; index < total + 1; index++) {
            long temp = index;
            String codeCrack = "";
            for (int j = 0; j < length; j++) {
                int label = (int)((temp -1) % 62);
                if (label < 0) {
                    label = 0;
                }
                codeCrack = codeCrack + dict.charAt(label);
                temp /= 62;
            }
            codeAll.add(codeCrack);
            if (codeCrack.equals(s)) {
                System.out.println("Find code : " + codeCrack);
                return codeCrack;
            }
        }
        System.out.println("Not Find code!");
        return null;
    }

    private static char getRandomChar(){
        double rand0 = Math.random();
        int rand;
        if(rand0 < 1.0/3){
            rand = (int)(Math.random() * 4 + 48);
        }else if(rand0 < 2.0/3){
            rand = (int)(Math.random() * 26 + 65);
        }else{
            rand = (int)(Math.random() * 26 + 97);
        }
        return (char)rand;
    }

    private static String getRandomString(int length) {
        String str = "";
        for (int i = 0; i < length; i++) {
            str += getRandomChar();
        }
        return str;
    }
    public static void main(String[] args) {

        Scanner s = new Scanner(System.in);
        System.out.println("input code length: ");
        int length = s.nextInt();
        s.close();

        String password = getRandomString(length);

        System.out.println(password);

        //String enumeCode = enumerationCrack(password);
        // System.out.println(codeAll);
        Thread crackThread = new Thread() {
            @Override
            public void run() {
                enumerationCrack(password);
            }
        };
        Thread logThread = new Thread() {
            @Override
            public void run() {
                while(!codeAll.isEmpty()) {
                    System.out.println("Code with a try is : " + codeAll.get(0));
                    codeAll.remove(0);
                }
            }
        };
        logThread.setDaemon(true);
        crackThread.start();
        logThread.start();
    }
}
