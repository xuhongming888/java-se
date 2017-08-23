package my01_ticketsseller;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class TicketSeller02 {
	
	static List<String> tickets = new ArrayList<>();
	
	static {
		
		for(int i = 0; i < 200; i++) {
			tickets.add("票根号" + i);
		}
		
	}

	public static void main(String[] args) {
		
		for(int i = 0; i < 10; i++) {
			
			new Thread(()->{
				
				while(true) {
					synchronized(tickets) {
						if(tickets.size() <= 0) break;
						
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
