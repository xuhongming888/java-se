package part1.initial.sequence;

/**
 * @description:
 * @author: xuzh1
 * @create: 2020-01-16 10:28
 **/
public class SuperClass {

    public String whenAmISet = "初始化父类的成员变量";

    SuperClass() {
        System.out.println("执行父类构造方法");
    }

    void process(){}

    static{
        System.out.println("执行父类静态快语句");
    }

    {
        //用于验证成员变量已经初始化
        System.out.println(whenAmISet);
        System.out.println("执行父类动态块语句");
    }
}
