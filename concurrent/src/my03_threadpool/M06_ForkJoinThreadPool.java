package my03_threadpool;

import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class M06_ForkJoinThreadPool {

	private static int[] nums = new int[500000];
	final static int MAX_VAULES = 100000;
	
	static{
		for(int i = 0; i < nums.length; i++) {
			nums[i] = 1;
		}
	}
	
	static class MyTask extends RecursiveTask<Long>{
		
		int start, end;
		
		public MyTask(int start, int end) {
			super();
			this.start = start;
			this.end = end;
		}

		@Override
		protected Long compute() {
			if(end - start < MAX_VAULES) {
				long sum = 0L;
				for(int i = start; i < end; i++) 
					sum += nums[i];
				return sum;
			}
			
			int middle = start + (end - start)/2;
			
			MyTask subTask1 = new MyTask(start, middle);
			MyTask subTask2 = new MyTask(middle, end);
			
			subTask1.fork();
			subTask2.fork();
			
			return subTask1.join() + subTask2.join();
		}
		
		
	}
	
	public static void main(String[] args) {
		
		long startTime = System.currentTimeMillis();
		long result0 = Arrays.stream(nums).sum();
		long endTime = System.currentTimeMillis();
		System.out.println("result0 = " + result0 + "    消耗时间：" + (endTime - startTime));
		
		long startTime1 = System.currentTimeMillis();
		MyTask t1 = new MyTask(0, nums.length);
		ForkJoinPool fjp = new ForkJoinPool();
		fjp.execute(t1);
		
		long result1 = t1.join();
		long endTime1 = System.currentTimeMillis();
		System.out.println("result1 = " + result1 + "    消耗时间：" + (endTime1 - startTime1));
		
		
		
	}
	
}
