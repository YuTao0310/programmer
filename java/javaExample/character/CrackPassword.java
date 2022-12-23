package character;
import java.util.Arrays;
import java.util.Scanner;

public class CrackPassword {

    //ʹ��forѭ���������ƽ�
    static char[] enumerationCrack(char[] s){

        char[] codeCrack = new char[s.length];
        for(int k = 0; k < s.length; k++)
            for(short i = '0'; i <= 'z'; i++){
                if(i == s[k]){              //����ʹ��==���Ƚ�short��char������ֱ��ʹ��char���ͽ��л�������
                    codeCrack[k] = (char)i; //shortתchar�����Զ�ת����char�����Զ�תshort
                    break;
                }
            }
        return codeCrack;

    }

    //Ҳ����ֱ����System.out.println
    static void printArray(char[] array){
        for(char each : array){
            System.out.print(each + ", ");
        }
        System.out.println(' ');
    }

    //ʹ�õݹ鷽���������ƽ�
    static char[] recursionCrack(char[] s){
        char findChar = '0';
        for(short i = '0'; i < 'z'; i++)
            if(i == s[0]){
                findChar = (char)i;
                break;
            }

        char[] findCharArray = new char[s.length];
        findCharArray[0] = findChar;

        if(s.length > 1)   
        {
            char[] newArray =  Arrays.copyOfRange(s, 1, s.length);
            newArray = recursionCrack(newArray);
            System.arraycopy(newArray, 0, findCharArray, 1, s.length - 1); 
        }
        return findCharArray;
    }
    public static void main(String[] args) {

        Scanner s = new Scanner(System.in);
        System.out.println("input code length: ");
        int length = s.nextInt();
        s.close();

        char[] charArray = new char[length];
        for(int i = 0; i < length; i++){
            charArray[i] = RandomString.getRandomChar();
        }

        System.out.println(charArray);

        char[] enumeCode = enumerationCrack(charArray);
        System.out.println(enumeCode);

        char[] recursionCode = recursionCrack(charArray);
        System.out.println(recursionCode);
    }
}
