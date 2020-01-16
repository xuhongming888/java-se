package part1.initial.sequence;

/**
 * @description:
 * @author: xuzh1
 * @create: 2020-01-16 10:37
 **/
public class SubClass extends SuperClass {

    public String whenAmISet = "初始化子类的成员变量";

    public SubClass() {
        System.out.println("执行子类的构造方法");
    }

    static{
        System.out.println("执行子类的静态块语句");
    }

    {
        //用于验证成员变量已经初始化
        System.out.println(whenAmISet);
        System.out.println("执行子类的动态块语句");
    }
}
