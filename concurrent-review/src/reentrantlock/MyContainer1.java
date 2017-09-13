package reentrantlock;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 应用背景：
 * 编写固定容量(自定义)容器, 2个生产者线程/10个消费者线程。
 * count, put(), get()
 *
 *实现
 *  ReentrantLock + Condition(可唤醒指定的线程)
 */
public class MyContainer1<T> {
	
	private final int MAX = 10;
	private int count = 0;
	private List<T> list = new LinkedList<>();
	
	private Object o;
	
	private ReentrantLock lock = new ReentrantLock();
	private Condition producer = lock.newCondition();
	private Condition consumer = lock.newCondition();
	
	
	public void put(T t) {
		try {
			lock.lock();
			while(count == MAX) {
					producer.await();
			}
			
			++count;
			list.add(t);
			consumer.signalAll();
		}catch(InterruptedException e) {
			e.printStackTrace();
		}finally {
			lock.unlock();
		}
	}
	
	public T get() {
		T t = null;
		try {
			lock.lock();
			while(count == 0) {
				consumer.await();
			}
			
			count--;
			t = list.remove(0);
			producer.signalAll();
		}catch(InterruptedException e) {
			e.printStackTrace();
		}finally {
			lock.unlock();
		}
		return t;
	}
	
	
	public static void main(String[] args) {
		MyContainer1<String> container = new MyContainer1<String>();
		
		for(int i = 0; i < 2; i++) {
			new Thread(()->{
				for(int j = 0; j < 25; j++) {
					container.put(Thread.currentThread().getName() + "产品" + j);
					System.out.println(Thread.currentThread().getName() + "生产了1个元素");
				}
			}, "producer" + i ).start();
		}
		
		for(int i = 0; i < 8; i++) {
			new Thread(()->{
				for(int j = 0; j < 5; j++) {
					String str = container.get();
					System.out.println(Thread.currentThread().getName() + "消费了" +str);
				}
			}, "consumer" + i).start();
		}
	}
	
}






















