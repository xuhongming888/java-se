/*
实例化100个线程，每个线程向map容器中放置10000个整数，比较各map容器在多线程下的执行效率。
*/
package my02_concurrentcontainer;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;

public class T01_ConcurrentMap {

	public void performanceComparable(Map<Integer, Integer> map, int threadNumber, CountDownLatch latch) {
		 Thread[] threads = new Thread[threadNumber];
		 Random r = new Random();
		 for(int i = 0; i < threads.length; i++) {
			 threads[i] = new Thread(()->{
				 for(int j = 0; j < 10000; j++) {
					 map.put(j, r.nextInt(100));
				 }
				 latch.countDown();
			 }, "t" + i);
		 }
		 
		 
		 Arrays.asList(threads).forEach(t->t.start());
		 
	}
	
	public static void main(String[] args) {
		T01_ConcurrentMap cm = new T01_ConcurrentMap();
		int threadNumber = 600;

	/**********Hashtable性能测试*************************************************/
		Map<Integer, Integer> map1 = new Hashtable<>();
		CountDownLatch latch1 = new CountDownLatch(threadNumber);
		
		long start1 = System.currentTimeMillis();
		cm.performanceComparable(map1, threadNumber, latch1);
		
		try {
			latch1.await();
		}catch(InterruptedException e) {
			e.printStackTrace();
		}
		long end1 = System.currentTimeMillis();
		System.out.println("Hashtable所需时间：     " + (end1 - start1) + "           现有容量" + map1.size());
		
	/**********Collections.synchronizeMap 性能测试*************************************************/
		Map<Integer, Integer> map2 = Collections.synchronizedMap(new HashMap<>());
		CountDownLatch latch2 = new CountDownLatch(threadNumber);
		
		long start2 = System.currentTimeMillis();
		cm.performanceComparable(map2, threadNumber, latch2);
		
		try {
			latch2.await();
		}catch(InterruptedException e) {
			e.printStackTrace();
		}
		long end2 = System.currentTimeMillis();
		System.out.println("Collections.synchronizedMap所需时间：     " + (end2 - start2) + "          现有容量" + map2.size() );
		
		
	/**********ConcurrentHashMap性能测试*************************************************/
		Map<Integer, Integer> map3 = new ConcurrentHashMap<>();
		CountDownLatch latch3 = new CountDownLatch(threadNumber);
		
		long start3 = System.currentTimeMillis();
		cm.performanceComparable(map3, threadNumber, latch3);
		
		try {
			latch3.await();
		}catch(InterruptedException e) {
			e.printStackTrace();
		}
		long end3 = System.currentTimeMillis();
		System.out.println("ConcurrentHashMap所需时间：     " + (end3 - start3) + "          现有容量" + map3.size() );		

	/**********ConcurrentSkipListMap性能测试       (concurrentSkipListMap: 插入并且排好序了)*************************************************/
		Map<Integer, Integer> map4 = new Hashtable<>();
		CountDownLatch latch4 = new CountDownLatch(threadNumber);
		
		long start4 = System.currentTimeMillis();
		cm.performanceComparable(map4, threadNumber, latch4);
		
		try {
			latch4.await();
		}catch(InterruptedException e) {
			e.printStackTrace();
		}
		long end4 = System.currentTimeMillis();
		System.out.println("ConcurrentSkipListMap所需时间：     " + (end4 - start4) + "         现有容量" + map4.size() );		
		
		
	}
}


























































