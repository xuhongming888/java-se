package my02_concurrentcontainer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.CopyOnWriteArrayList;

public class T02_ConcurrentList {

	public void runAndCoumpteTime(List<Integer> list, int threadNumber) {
		
		long start = System.currentTimeMillis();
		Thread[] threads = new Thread[threadNumber];
		for(int i = 0; i < threads.length; i++) {
			threads[i] = new Thread(()->{
				for(int j = 0; j < 500; j++) {
					list.add(j);
				}
			});
		}
		
		Arrays.asList(threads).forEach(t->t.start());
		Arrays.asList(threads).forEach(t->{
			try {
				t.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});
		
		long end = System.currentTimeMillis();
		System.out.println((end - start) + "    list容量  " + list.size());
	}
	
	public static void main(String[] args) {
		T02_ConcurrentList concurrentList = new T02_ConcurrentList();
		int threadNumber = 100;
		
	/*********CopyOnWirteArrayList性能*******CopyOnWriteArrayList所需时间：   3718    list容量  100000****************/	
		System.out.print("CopyOnWriteArrayList所需时间：   ");
		List<Integer> list1 = new CopyOnWriteArrayList<>();
		concurrentList.runAndCoumpteTime(list1, threadNumber);
		
    /*********Vector性能*****Vector所需时间：   23    list容量  100000**********************************/	
		System.out.print("Vector所需时间：   ");
		Vector<Integer> list2 = new Vector<>();
		concurrentList.runAndCoumpteTime(list2, threadNumber);		
		
	/*********Collections.synchronizedList性能********************************************************/
		System.out.print("Vector所需时间：   ");
		List<Integer> list3 = Collections.synchronizedList(new ArrayList<>());
		concurrentList.runAndCoumpteTime(list3, threadNumber);		
		
	}
}



























