package exception;

/*Demo
    手动抛出异常
    自己throw(制造一个异常抛出)
 */


import java.io.FileNotFoundException;

public class ThrowEx {

    // 手动抛出运行时异常
    public void setAge(int age) {
        if (age < 0) {
            throw new NullPointerException("输入的年龄小于0");
        }
    }
    /*
          此方法手动抛出了运行时异常
          运行时异常可以不用处理
     */

    // 手动抛出编译时异常
    public void setAge2(int age) throws FileNotFoundException {
        if (age < 0) {
            throw new FileNotFoundException("输入的年龄小于0");
        }
    }
    /*
          此方法手动抛出的了编译时异常
          编译时异常需要被处理
          这里采用了 throws 这个异常, 也就是说方法并没有处理这个异常, 而是将异常抛给了调用者
      这样一来调用了这个方法的人就必须要处理这个异常才可以.
          注意:   在这里并不用自己使用 try catch 处理这个异常
                自己在方法里抛出异常, 方法再自己处理没有任何作用.
               所以方法中的异常需要抛给调用者去处理.
     */


    public static void main(String[] args) {
        ThrowEx throwEx = new ThrowEx();

        throwEx.setAge(-5);
    }

}