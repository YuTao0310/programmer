package character;

import java.util.Scanner;

public class TestString {

    static String FirstLetterCapitalize(String str) {
        String[] stringArray = str.split(" ");
        String letterCaptalizeString = "";
        for (int i = 0; i < stringArray.length; i++) {
            stringArray[i] = stringArray[i].substring(0, 1).toUpperCase() + stringArray[i].substring(1);
            // stringArray[i] = Character.toUpperCase(stringArray[i].charAt(0)) +
            // stringArray[i].substring(1);
            letterCaptalizeString += stringArray[i] + " ";
        }
        return letterCaptalizeString;
    }

    static int CountNumberOfLetter(String str, char letter) {
        String[] stringArray = str.split(" ");
        int count = 0;
        for (int i = 0; i < stringArray.length; i++) {
            if (stringArray[i].charAt(0) == Character.toUpperCase(letter)
                    || stringArray[i].charAt(0) == Character.toLowerCase(letter))
                count++;
        }
        return count;
    }

    static String SpacedUpperLower(String str) {
        String newString = "";
        for (int i = 0; i < str.length(); i++) {
            if (i % 2 == 0)
                newString += Character.toUpperCase(str.charAt(i));
            else
                newString += Character.toLowerCase(str.charAt(i));
        }
        return newString;
    }

    static String LastLetterCapitalize(String str) {
        String[] stringArray = str.split(" ");
        String letterCaptalizeString = "";
        int length = 0;
        for (int i = 0; i < stringArray.length; i++) {
            length = stringArray[i].length();
            stringArray[i] = stringArray[i].substring(0, length - 1)
                           + stringArray[i].substring(length - 1, length).toUpperCase();
            letterCaptalizeString += stringArray[i] + " ";
        }
        return letterCaptalizeString;
    }

    //也可以考虑使用Stirng.indexOf方法来进行求解
    static String LastWordCapitalize(String str, String word){
        String wordReplace = FirstLetterCapitalize(word);
        String[] stringArray = str.split(" ");
        String newString = "";
        int length = stringArray.length;
        for(int i = length - 1; i >= 0; i--){
            if(stringArray[i].equals(word)){
                stringArray[i] = wordReplace;
                break;
            }
        }

        for(int i = 0; i < length; i++)
            newString += stringArray[i] + " ";

        return newString;
    }

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        System.out.println("input a sentence: ");
        String inputSentence = s.nextLine();
        /*
         * String sentenceLetterCapitalize = FirstLetterCapitalize1(inputSentence);
         * System.out.println("the sentence after first letter is capitalize is : ");
         * System.out.println(sentenceLetterCapitalize); char letter = 'p'; int
         * numberOfLetter = CountNumberOfLetter(inputSentence, letter); System.out.
         * format("the number with first letter of %s in a sentence is      %d%n",
         * letter, numberOfLetter); String stringUpperLower =
         * SpacedUpperLower(inputSentence);
         * System.out.println("the sentence with spaced uppercase and lowercase is: ");
         * System.out.println(stringUpperLower);
         * String sentenceLastLetterCapitalize = LastLetterCapitalize(inputSentence);
         * System.out.format("the sentence with last letter captitalize is %s%n", sentenceLastLetterCapitalize);
        */
        
        String word = "two";
        String sentenceLastWordCapitalize = LastWordCapitalize(inputSentence, word);
        System.out.format("the sentence with last word %s capitalized is : %n %s %n",
                          word, sentenceLastWordCapitalize);
        s.close();
    }
}