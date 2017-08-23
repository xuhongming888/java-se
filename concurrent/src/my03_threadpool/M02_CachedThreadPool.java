package my03_threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class M02_CachedThreadPool {

	public static void main(String[] args) throws InterruptedException {
		ExecutorService service = Executors.newCachedThreadPool();
		System.out.println(service);
		
		for(int i = 0; i < 2; i++) {
			service.execute(()->{
				try {
					TimeUnit.MICROSECONDS.sleep(500);
				}catch(InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println(Thread.currentThread().getName());
			});
		}
		
		System.out.println(service);
		
		TimeUnit.SECONDS.sleep(70);
		
		System.out.println(service);
		
	}
	
	
	
}