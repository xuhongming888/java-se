package my03_threadpool;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class M01_FixedThreadPool2 {
	
	private int[] a = new int[500000];
	
	{
		Random r = new Random();
		for(int i = 0 ; i < 500000; i++)
			a[i] = r.nextInt(100);
	}
	
	public long compute(int start, int end) {
		long sum = 0;
		for(int i = start; i< end; i++) 
			sum += a[i];
		
		return sum;
	}
	
	
	public static void main(String[] args) {
		
		M01_FixedThreadPool2 ftp = new M01_FixedThreadPool2();
		
		ExecutorService service = Executors.newFixedThreadPool(5);
		
		for(int i = 0; i < 5; i++) {
			final int j = i;
			service.execute(()->{
				ftp.compute(j * 100000, (j+1)*100000);
				
			});
			
		}
		
		
		
	}
}




















