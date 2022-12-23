package character;
import java.util.Scanner;


public class RandomString {
    //�������ɷ�����
    //��1��������ʾ��Ҳ���Բ���Switch���������ѡ��
    //��2������'0'��'z'������ַ������������ֻ���ĸ�ͷ��ء�����ʱ��ֱ�ӹ����һ���������á�
    //��3�����ȹ���0-1��'A'-'Z'��'a'-'z'���ַ����飬Ȼ��Ӹ��ַ����������ѡ�����ݡ���ý��ַ����鹹��Ϊһ��ȫ�����飬����ÿ�ε��õ�ʱ����Ҫ�ظ����졣
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

        System.out.println("�����ַ�������: ");
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
