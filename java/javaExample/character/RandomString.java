package character;
import java.util.Scanner;


public class RandomString {
    //其他生成方法有
    //法1：如下所示，也可以采用Switch方法来随机选择
    //法2：生成'0'到'z'的随机字符，当它是数字或字母就返回。这种时候直接构造成一个函数调用。
    //法3：事先构造0-1、'A'-'Z'、'a'-'z'的字符数组，然后从该字符数组中随机选择数据。最好将字符数组构造为一个全局数组，这样每次调用的时候不需要重复构造。
    static char getRandomChar(){
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
    public static void main(String[] args) {

        System.out.println("输入字符串长度: ");
        Scanner s = new Scanner(System.in);
        int length = s.nextInt();

        char[] charArray = new char[length];
        for(int i = 0; i < length; i++){
            charArray[i] = getRandomChar();
        }

        String str = new String(charArray);
        System.out.println(str);

        s.close();

    }
}
