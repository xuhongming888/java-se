/**
 * ThreadLocal线程局部变量
 *
 * ThreadLocal是使用空间换时间，synchronized是使用时间换空间
 * 比如在hibernate中session就存在与ThreadLocal中，避免synchronized的使用
 *
 * 运行下面的程序，理解ThreadLocal
 * 
 * @author 马士兵
 */
package yxxy.c_022;

import java.util.concurrent.TimeUnit;

public class ThreadLocal2 {
	//volatile static Person p = new Person();
	static ThreadLocal<Person> tl = new ThreadLocal<>();
	
	public static void main(String[] args) {
				
		new Thread(()->{
			try {
				TimeUnit.SECONDS.sleep(2);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			System.out.println(tl.get());
		}).start();
		
		new Thread(()->{
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			tl.set(new Person());     //在本线程, 实例化了1个person对象
			System.out.println("t2线程" + tl.get());
		}).start(); 
	}
	
	static class Person {
		String name = "zhangsan";
	}
}


