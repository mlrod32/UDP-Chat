import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FocusTraversalPolicy;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

public class ChatGUI {

	private JFrame myFrame;
	private JPanel mainPanel;
	private JPanel chatPanel;
	private JTextArea chatBox;
	private JTextArea messageBox;
	private JScrollPane scrollPane;
	
	public ChatGUI(){
		
		myFrame = new JFrame();
		myFrame.setSize(300,400);
		myFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		myFrame.setVisible(true);
		
		mainPanel = new JPanel();
		chatPanel = new JPanel();
		mainPanel.add(chatPanel,"chat");
		mainPanel.setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(10, 33, 239, 194);
		chatPanel.add(scrollPane);
		
		chatBox = new JTextArea();
		scrollPane.setViewportView(chatBox);
		chatBox.setEditable(false);
		
		messageBox = new JTextArea();
		messageBox.setBounds(10, 251, 179, 79);
		chatPanel.add(messageBox);	
		
		JButton send = new JButton("Send");
		send.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0){
				if(!messageBox.getText().equals(null)){
					send(messageBox.getText().trim(),send.getText().isEmpty());
				}
			}		
			
		});
		
		send.setBounds(189, 307, 75, 23);
		chatPanel.add(send);
	}
}
