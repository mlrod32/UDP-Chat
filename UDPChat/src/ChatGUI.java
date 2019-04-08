import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.DatagramSocket;
import java.net.InetAddress;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.WindowConstants;

import clientudp.clientmain;
import clientudp.clientreceive;

public class ChatGUI extends JFrame {
	JFrame myFrame;
	JPanel mainPanel;
	JPanel chatPanel;
	JTextArea chatBox;
	JTextArea messageBox;
	JScrollPane scrollPane;
	//clientmain ct = new clientmain();
	//clientreceive cr = new clientreceive();
	
	public ChatGUI(InetAddress IP, int port, DatagramSocket dss){
		
		
		myFrame = new JFrame();
		myFrame.setTitle(IP + " : " + port);
		myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		myFrame.setSize(300, 300);
		//myFrame.setLayout(null);
		
		
		//send.setBounds(180, 140, 80, 80);
		//myFrame.add(send);
		
		chatPanel = new JPanel();
		chatPanel.setBackground(Color.RED);
		scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(10, 33, 239, 194);
		chatPanel.setLayout(null);
		chatPanel.add(scrollPane);
		
		
		chatBox = new JTextArea();
		scrollPane.setViewportView(chatBox);
		chatBox.setEditable(false);
		
		messageBox = new JTextArea();
		messageBox.setBounds(10,251,179,79);
		chatPanel.add(messageBox);
		
		JButton send = new JButton("Send");
		send.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0){
				if(!messageBox.getText().equals(null)){
					ChatMain.send(messageBox.getText().trim(), IP, port);
				}
				messageBox.setText("");
			}		
			
		});
		
		send.setBounds(189, 307, 75, 23);
		chatPanel.add(send);
		
		myFrame.add(chatPanel, BorderLayout.CENTER);
		myFrame.setVisible(true);
	}	
		
	public void display(String msg)
	{
		chatBox.append(msg + "\n");
	}
	

	
}
