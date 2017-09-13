package thread.join;

import java.util.concurrent.TimeUnit;

public class ThreadJoinMethodTest {

	public static void main(String[] args) {
		
		Thread previous = Thread.currentThread();
		
		for(int i = 0; i < 10; i++) {
			
			Thread thread = new Thread(new Daemino(previous), String.valueOf(i));
			thread.start();
			
			previous = thread;
		}
		
		try {
			TimeUnit.MILLISECONDS.sleep(5);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println(Thread.currentThread().getName() + "Terminate");

	}
	
	
	static class Daemino implements Runnable {
		
		Thread thread = null;
		
		public Daemino(Thread thread) {
			this.thread = thread;
		}
		
				
		public void run() {
			
			try {
				thread.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			System.out.println(Thread.currentThread().getName() + " terminate");
		}
	}
}
