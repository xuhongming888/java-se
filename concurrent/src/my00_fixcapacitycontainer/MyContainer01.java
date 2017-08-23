/* 
 * 面试题：写一个固定容量同步容器，拥有put和get方法，以及getCount方法，
 * 能够支持2个生产者线程以及10个消费者线程的阻塞调用
 * 
 */
package my00_fixcapacitycontainer;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

public class MyContainer01<T> {
	
	final private LinkedList<T> lists = new LinkedList<T>();
	final private int MAX = 10;
	private int count = 0;
	
	public synchronized void put(T t) {
		while(lists.size() == MAX) {
			
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
		
		++count;
		lists.add(t);
		this.notifyAll();
	}
	
	
	public synchronized T get() {
		
		while(lists.size() == 0) {
			
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		T t = lists.removeFirst();
		--count;
		this.notifyAll();
		return t;
	}
	
	
	public synchronized int getCount() {
		return this.count;
	}

	public static void main(String[] args) {
		
		MyContainer01<String> container = new MyContainer01<String>();
		
		for(int i = 0; i < 10; i++) {
			new Thread(()->{
				for(int j = 0; j < 20; j++) {
					System.out.println(Thread.currentThread().getName() + "消费了" + container.get());
				}
				
			}, "cusotomer" + i) .start();
			
		}
		
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}		
		
		for(int i = 0; i < 2; i++) {
			
			new Thread(()->{
				for(int j = 0; j < 25; j++) {
					container.put(Thread.currentThread().getName() + "  " + j);
				}
				
			}, "producer" + i) {}.start();
		}
		
		
	}
	
}























