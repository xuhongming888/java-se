package my01_ticketsseller;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.TimeUnit;

public class TicketSeller03 {
	
	static Queue<String> tickets = new ConcurrentLinkedQueue<>();
	
	static {
		for( int i = 0; i < 1000; i++) {
			tickets.add("票根号" + i);
		}
	}
	
	public static void main(String[] args) {
		for(int i = 0; i < 10; i++) {
			new Thread(()->{
				while(true) {
					String s = tickets.poll();
					
					try {
						TimeUnit.MILLISECONDS.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}					
					
					if(s== null) break;
					else
						System.out.println(Thread.currentThread().getName() + "  销售了" + s);
				}
				
			}).start();
		}
		
	}
}
