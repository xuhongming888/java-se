package threadLocal;

import java.util.concurrent.TimeUnit;

public class ThreadLocalTest {
	
	static ThreadLocal<Person> tl = new ThreadLocal<Person>();

	static class Person{
		private String name = "zhang shan";
	}
	
	public static void main(String[] args) {
		
		
		new Thread(()->{
					
				try {
					TimeUnit.MILLISECONDS.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
				System.out.println(tl.get());
		}, "t1").start();
		
	
		new Thread(()->{
			
			tl.set(new Person());
			try {
				TimeUnit.MILLISECONDS.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			System.out.println("t2Ïß³Ì£º  " + tl.get().name);
		}, "t2").start();
	}	

}
