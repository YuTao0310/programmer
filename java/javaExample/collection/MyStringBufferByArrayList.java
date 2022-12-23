package collection;
import character.IStringBuffer;
import java.util.ArrayList;

public class MyStringBufferByArrayList implements IStringBuffer {

    private ArrayList<Character> buffer;

    MyStringBufferByArrayList() {
        this.buffer = new ArrayList<Character>();
    }

    MyStringBufferByArrayList(char[] charArray) {
        this.buffer = new ArrayList<Character>();
        for (char c : charArray) {
            this.buffer.add(c);
        }
    }

    MyStringBufferByArrayList(String str) {
        this(str.toCharArray());     
    }

    @Override
    public void append(String str){
        if (null == str) {
            throw new NullPointerException("string is null");
        }
        char[] charArray = str.toCharArray();

        for (char c : charArray) {
            this.buffer.add(c);
        }     
    }

    @Override
    public void append(char c) {
        this.buffer.add(c);
    }

    @Override
    public void insert(int pos, char b) throws IndexIsNagetiveException, IndexIsOutofRangeException {
        if (pos < 0) {
            throw new IndexIsNagetiveException("pos is smaller than 0");
        }
        if (pos > this.buffer.size()) {
            throw new IndexIsOutofRangeException("pos is greater than the length of StringBuffer");
        }
        this.buffer.add(pos, b);
    }

    @Override
    public void insert(int pos, String b) throws IndexIsNagetiveException, IndexIsOutofRangeException {
        if(pos < 0) {
            throw new IndexIsNagetiveException("pos is smaller than 0");
        }
        if (pos > this.buffer.size()) {
            throw new IndexIsOutofRangeException("pos is greater than the length of StringBuffer");
        }
        if (null == b) {
            throw new NullPointerException("string is null");
        }
        char[] charArray = b.toCharArray();
        int i = 0;
        for (char c : charArray) {
            this.buffer.add(pos + i, c);
            i++;
        }
    }

    @Override
    public void delete(int start) throws IndexIsNagetiveException, IndexIsOutofRangeException{
        if (start < 0) {
            throw new IndexIsNagetiveException("start is smaller than 0");
        }
        if (start > this.buffer.size()) {
            throw new IndexIsNagetiveException("start is greater than the length of StringBuffer");
        }
        int count = this.buffer.size() - start;
        for (int i = 0; i < count; i++) {
            this.buffer.remove(start);
        }
    }

    @Override
    public void delete(int start, int end) throws IndexIsNagetiveException, IndexIsOutofRangeException{
        if (start > end) {
            throw new IndexIsOutofRangeException("start is greater than end");
        }
        if (start < 0) {
            throw new IndexIsNagetiveException("start is smaller than 0");
        }
        if (end > this.buffer.size()) {
            throw new IndexIsOutofRangeException("end is greater than the length of StringBuffer");
        }
        if (end < 0) {
            throw new IndexIsNagetiveException("end is smaller than 0");
        }
        int count = end - start;
        for (int i = 0; i < count; i++) {
            this.buffer.remove(start);
        }  
    }

    @Override
    public void reverse() {
        int length = this.buffer.size();
        for(int i = 0; i <= (length - 1)/2; i++){
            char temp = this.buffer.get(i);
            this.buffer.set(i, this.buffer.get(length - 1 - i));
            this.buffer.set(length - 1 - i, temp); 
        }
    }

    @Override
    public int length() {
        return this.buffer.size();
    }

    public int capacity() {
        return this.buffer.size();
    }

    @Override
    public String toString(){
        String str = "";
        for(int i = 0; i < this.buffer.size(); i++)
            str += this.buffer.get(i);
        return str;
    }
    public static void main(String[] args) {
        MyStringBufferByArrayList b = new MyStringBufferByArrayList("happy");
        try {
            b.append(" birthday!");
            b.append('?');
            b.insert(6, "hahaha ");
            // b.insert(6, null);
            b.insert(6, 'k');
            b.delete(6);
            b.delete(4, 2);
            b.reverse();
            b.delete(0);
            b.append("happy day!");
            System.out.println(b);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }   
}

