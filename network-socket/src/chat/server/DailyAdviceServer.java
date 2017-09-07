package chat.server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class DailyAdviceServer {

	private String[] adviceList = {"��ã�İ����", "��ã���Ҳ��"};
	
	
	private String getAdvice() {
		int random = (int) (Math.random() * adviceList.length);
		
		return adviceList[random];
	}
	
	
	public void go() {
		
		try {
			
			ServerSocket serverSocket = new ServerSocket(4242);
			
			while(true) {
				
				Socket socket = serverSocket.accept();
				
				PrintWriter writer = new PrintWriter(socket.getOutputStream());
				String advice = getAdvice();
				
				writer.println(advice);
				
				writer.close();
				
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
}





