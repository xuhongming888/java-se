package reentrantlock;

import java.util.LinkedList;
import java.util.List;

public class MyContainer2<T> {
	
	private final static int MAX = 10;
	private int count = 0;
	private List<T> list = new LinkedList<>();
	
	
	public synchronized void put(T t) {
		try {
			while(list.size() == MAX) {
				this.wait();
			}
			
			++count;
			list.add(t);
			this.notifyAll();
			
		}catch(InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public synchronized T get() {
		T t = null;
		
		try {
			while(list.size() == 0) {
				this.wait();
			}
			
			count--;
			t = list.remove(0);
			this.notifyAll();
			
		}catch(InterruptedException e) {
			e.printStackTrace();
		}
		return t;
	}
	
	
	public static void main(String[] args) {
		
		MyContainer2<String> container = new MyContainer2<String>();
		
		
		for(int i = 0; i < 2; i++) {
			new Thread(()->{
				for(int j = 0; j < 25; j++) {
					container.put(Thread.currentThread().getName() + j);
					System.out.println(Thread.currentThread().getName() + "生产了元素" + j);
				}
				
			}, "producer" + i).start();
		}
		
/*		try {
			TimeUnit.MICROSECONDS.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}*/
		
		for(int i = 0; i < 2; i++) {
			new Thread(()->{
				for(int j = 0; j < 8; j++) {
					String str = container.get();
					System.out.println(Thread.currentThread().getName() + "消费了" + str);
				}
			}, "consumer" + i).start();
		}
		
	}
	

}























