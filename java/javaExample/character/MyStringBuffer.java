package character;

import java.util.ArrayList;

public class MyStringBuffer implements IStringBuffer {

    private int length;
    private int capacity; 
    private char[] buffer;

    MyStringBuffer() {
        length = 0;
        capacity = 1;
        this.buffer = new char[capacity];
    }

    MyStringBuffer(char[] charArray) {
        this.length = charArray.length;
        this.capacity = 2 * this.length;
        this.buffer = new char[this.capacity];
        System.arraycopy(charArray, 0, this.buffer, 0, this.length);
    }

    MyStringBuffer(String str) {
        this(str.toCharArray());     
    }

    private void guranteeCapacity(int lenAdded) {
        int lengthAfter = this.length + lenAdded;
        if(buffer.length <= lengthAfter){
            this.capacity = 2 * lengthAfter;
            char[] newArray = new char[this.capacity];
            System.arraycopy(this.buffer, 0, newArray, 0, this.length); 
            this.buffer = newArray;                                                    
        }else if(buffer.length > 2 * lengthAfter){
            this.capacity = 2 * lengthAfter;
        }
        this.length = lengthAfter;
    }

    @Override
    public void append(String str){
        if (null == str) {
            throw new NullPointerException("string is null");
        }
        char[] charArray = str.toCharArray();
        int originalLength = this.length;
        int lenAdded = charArray.length;
        guranteeCapacity(lenAdded);
        System.arraycopy(charArray, 0, this.buffer, originalLength, lenAdded);
    }

    @Override
    public void append(char c) {
        guranteeCapacity(1);
        this.buffer[this.length - 1] = c;
    }

    @Override
    public void insert(int pos, char b) throws IndexIsNagetiveException, IndexIsOutofRangeException {
        if (pos < 0) {
            throw new IndexIsNagetiveException("pos is smaller than 0");
        }
        if (pos > this.length) {
            throw new IndexIsOutofRangeException("pos is greater than the length of StringBuffer");
        }
        guranteeCapacity(1);
        // char[] lastPart = Arrays.copyOfRange(this.buffer, pos, this.length - 1);
        // this.buffer[pos] = b;
        // System.arraycopy(lastPart, 0, this.buffer, pos + 1, lastPart.length);
        System.arraycopy(this.buffer, pos, this.buffer, pos + 1, this.length - 1 - pos);
        this.buffer[pos] = b;
    }

    @Override
    public void insert(int pos, String b) throws IndexIsNagetiveException, IndexIsOutofRangeException {
        if(pos < 0) {
            throw new IndexIsNagetiveException("pos is smaller than 0");
        }
        if (pos > this.length) {
            throw new IndexIsOutofRangeException("pos is greater than the length of StringBuffer");
        }
        if (null == b) {
            throw new NullPointerException("string is null");
        }
        char[] charArray = b.toCharArray();
        int originalLength = this.length;
        int lenAdded = charArray.length;
        guranteeCapacity(lenAdded);
        // char[] lastPart = Arrays.copyOfRange(this.buffer, pos, originalLength);
        // System.arraycopy(charArray, 0, this.buffer, pos, lenAdded);
        // System.arraycopy(lastPart, 0, this.buffer, pos + lenAdded, lastPart.length);
        System.arraycopy(this.buffer, pos, this.buffer, pos + lenAdded, originalLength - pos);
        System.arraycopy(charArray, 0, this.buffer, pos, lenAdded);
    }

    @Override
    public void delete(int start) throws IndexIsNagetiveException, IndexIsOutofRangeException{
        if (start < 0) {
            throw new IndexIsNagetiveException("start is smaller than 0");
        }
        if (start > this.length) {
            throw new IndexIsNagetiveException("start is greater than the length of StringBuffer");
        }
        int originalLength = this.length;
        guranteeCapacity(start - originalLength);
        char[] newArray = new char[this.capacity];
        System.arraycopy(this.buffer, 0, newArray, 0, start);
        this.buffer = newArray;
    }

    @Override
    public void delete(int start, int end) throws IndexIsNagetiveException, IndexIsOutofRangeException{
        if (start > end) {
            throw new IndexIsOutofRangeException("start is greater than end");
        }
        if (start < 0) {
            throw new IndexIsNagetiveException("start is smaller than 0");
        }
        if (end > this.length) {
            throw new IndexIsOutofRangeException("end is greater than the length of StringBuffer");
        }
        if (end < 0) {
            throw new IndexIsNagetiveException("end is smaller than 0");
        }
        int originalLength = this.length;
        guranteeCapacity(start - end);
        char[] newArray = new char[this.capacity];
        System.arraycopy(this.buffer, 0, newArray, 0, start);
        System.arraycopy(this.buffer, end, newArray, start, originalLength - end);
        this.buffer = newArray;     
    }

    @Override
    public void reverse() {
        for(int i = 0; i <= (this.length - 1)/2; i++){
            char temp = this.buffer[i];
            this.buffer[i] = this.buffer[this.length - 1 - i];
            this.buffer[this.length - 1 - i] = temp; 
        }
    }

    @Override
    public int length() {
        return this.length;
    }

    public int capacity() {
        return this.capacity;
    }

    @Override
    public String toString(){
        String str = "";
        for(int i = 0; i < this.length; i++)
            str += this.buffer[i];
        return str;
    }
    public static void main(String[] args) {
        MyStringBuffer b = new MyStringBuffer("happy");
        System.out.println(b);
        ArrayList<Character> cs = new ArrayList<Character>();
        cs.add('h');
        cs.add('k');
        System.out.println(cs);

        try {
            b.append(" birthday!");
            b.append('?');
            b.insert(6, "hahaha ");
            // b.insert(6, null);
            b.insert(6, 'k');
            b.delete(6);
            // b.delete(4, 2);
            b.reverse();
            b.delete(0);
            b.append("happy day!");
            System.out.println(b);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }   
}
