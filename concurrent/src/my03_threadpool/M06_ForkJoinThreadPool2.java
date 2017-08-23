package my03_threadpool;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class M06_ForkJoinThreadPool2 {

	static int[] nums = new int[500000];
	static Random r = new Random();
	final static int MAX_NUM = 50000;
	
	static {
		for(int i = 0; i < nums.length; i++)
			nums[i] = r.nextInt(100);
	}
	
	static class SumTask extends RecursiveTask<Long>{
		
		int start, end;

		public SumTask(int start, int end) {
			super();
			this.start = start;
			this.end = end;
		}
		
		public Long compute() {
			if(end - start < MAX_NUM) {
				long sum = 0L;
				for(int i = start; i < end; i++)
					sum += nums[i];
				return sum;
			}
			
			int middle = start + (end - start)/2;
			SumTask subTask1 = new SumTask(start, middle);
			SumTask subTask2 = new SumTask(middle, end);
			
			subTask1.fork();
			subTask2.fork();
			
			return subTask1.join() + subTask2.join();
		}
		
	}
	
	public static void main(String[] args) {
		
		long startTime0 = System.currentTimeMillis();
		long result0 = Arrays.stream(nums).sum();
		long endTime0 = System.currentTimeMillis();
		System.out.println("result0 = " + result0 + "   运行时间： " + (endTime0 - startTime0));
		
		long startTime1 = System.currentTimeMillis();
		SumTask t1 = new SumTask(0, nums.length);
		
		ForkJoinPool fjp = new ForkJoinPool();
		fjp.execute(t1);
System.out.println(fjp);
		
		long result1 = t1.join();
		long endTime1 = System.currentTimeMillis();
		System.out.println("result1 = " + result1 + "   运行时间： " + (endTime1 - startTime1));	
		
System.out.println(fjp);
		fjp.shutdown();
System.out.println(fjp);
	}
	
	
	
}
