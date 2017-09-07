package chat.test;

import chat.server.DailyAdviceServer;
import chat.server.VerySimpleChatServer;

public class ServerMain {
	public static void main(String[] args) {
	
//		DailyAdviceServer server = new DailyAdviceServer();
		VerySimpleChatServer server = new VerySimpleChatServer();
		server.go();
	}
}
