package chat.client;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * 只能发送信息的版本
 *
 */
public class SimpleChatClientA {

	JTextField outgoing;
	PrintWriter writer;
	Socket socket;
	

	
	public void go() {
		JFrame frame = new JFrame("Ludicrously Simple Chat Client");
		JPanel mainPanel = new JPanel();
		
		outgoing = new JTextField(20);
		JButton sendButton = new JButton("Send");
		
		sendButton.addActionListener(new SendButtonListener());
		
		mainPanel.add(outgoing);
		mainPanel.add(sendButton);
		
		frame.getContentPane().add(BorderLayout.CENTER, mainPanel);
		
		setUpNetWorking();
		
		frame.setSize(400, 500);
		frame.setVisible(true);
	}

	private void setUpNetWorking() {
		
		try {
			socket = new Socket("127.0.0.1", 4242);
			writer = new PrintWriter(socket.getOutputStream());
			System.out.println("networking establish");
		}catch(IOException e) {
			e.printStackTrace();
		}
	}	
	
	public class SendButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			
			try {
				writer.println(outgoing.getText());
				writer.flush();
			}catch(Exception ex) {
				ex.printStackTrace();
			}
			
			outgoing.setText("");
			outgoing.requestFocus();
		}
		
	}
	
	
	
}


