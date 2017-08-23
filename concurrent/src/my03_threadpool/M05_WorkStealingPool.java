package my03_threadpool;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class M05_WorkStealingPool {

	static class R implements Runnable {
		
		int sleepTime;

		public R(int sleepTime) {
			this.sleepTime = sleepTime;
		}

		@Override
		public void run() {
			try {
				TimeUnit.MILLISECONDS.sleep(sleepTime);
			}catch(InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName());
		}
	}
	
	public static void main(String[] args) throws IOException {
		ExecutorService service = Executors.newWorkStealingPool();
		System.out.println(Runtime.getRuntime().availableProcessors());
		
		service.execute(new R(1000));//
		service.execute(new R(2000));
		service.execute(new R(2000));
		service.execute(new R(2000));	
		service.execute(new R(2000));//该任务会由第一个完成任务的线程进行执行	
		
		System.in.read();
	}
	
}
