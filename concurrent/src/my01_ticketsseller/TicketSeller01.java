package my01_ticketsseller;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class TicketSeller01 {
	
	static List<String> tickets = new ArrayList<>();
	
	static {
		
		for(int i = 0; i < 1000; i++) {
			tickets.add("票根号" + i);
		}
		
	}

	public static void main(String[] args) {
		
		for(int i = 0; i < 10; i++) {
			
			new Thread(()->{
				synchronized(tickets) {
					while(tickets.size() > 0) {
						
						try {
							TimeUnit.MILLISECONDS.sleep(10);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						
						System.out.println(Thread.currentThread().getName() +"  销售了" + tickets.remove(0));
					}
				}
			}).start();
		}
		

	}

}
