package part1.initial.sequence;

/**
 * 类的实例化执行顺序
   1）父类静态块的内容--->子类静态块的内容；
  2）初始化父类的成员变量--->父类的非静态语句块--->父类的构造方法。
  3）初始化子类的成员变量--->子类的非静态语句块--->子类的构造方法。
 **/
public class Main {

    public static void main(String[] args) {
        new SubClass();
    }
}
