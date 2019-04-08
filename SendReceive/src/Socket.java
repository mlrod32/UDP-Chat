import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.*;
import javax.swing.*;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class Socket {
	
	private static InetAddress myAddress;
	private static BufferedReader rmessage;
	private static Scanner scnr = new Scanner(System.in);
	
	
	public static void receiveMethod(){
		
		
		DatagramSocket inSocket = null;
		byte[] inBuffer = new byte[100];
		
		DatagramPacket inPacket = new DatagramPacket(inBuffer, inBuffer.length);
		
		try{
			inSocket = new DatagramSocket(64000, myAddress);
		}
		catch (Exception e){
			
			e.printStackTrace();
			System.exit(-1);
		}
		
		do{
			for(int i = 0; i < inBuffer.length; i++){
				inBuffer[i] = ' ';
			}
			
			try{
				//this thread will block in the receive call
				//until a message is received
				inSocket.receive(inPacket);
			}catch (Exception e){
				e.printStackTrace();
				System.exit(-1);
			
			}
			InetAddress IPAddress = inSocket.getInetAddress();
			
			String message = new String(inPacket.getData());
			System.out.println("Recieved Message = " + message + " " + IPAddress);
			
		}while(true);
		
	}
	
	public static void send(String message, boolean empty){
		
		DatagramSocket outSocket = null;
		
		try{
			outSocket = new DatagramSocket(63000, myAddress);
		}
		catch (Exception e){
			
			e.printStackTrace();
			System.exit(-1);
		}
		
		byte[] buffer = new byte [100];
		
		//for(int i = 0; i <= 10; i++){
			message = scnr.nextLine();			
			buffer = message.getBytes();
			
			
			
			try{
				
				byte[] otherHost = new byte[4];
				otherHost[0] = (byte) 192;
				otherHost[1] = (byte) 168;
				otherHost[2] = (byte) 1;
				otherHost[3] = (byte) 112;
				
					
				
				InetAddress otherAddress = InetAddress.getByAddress(otherHost); 
				InetAddress IPAdress = outSocket.getInetAddress();
				DatagramPacket packet = new DatagramPacket(buffer, message.length(), myAddress, 64000);
				System.out.println(message + " " + IPAdress);
				outSocket.send(packet);
				TimeUnit.SECONDS.sleep(5);
				
			}catch (Exception e) {
				
				e.printStackTrace();
				System.exit(-1);
				
			}
	}
	
	public static void main(String[]args){
		
		try{
			myAddress = InetAddress.getLocalHost();			
		}
		catch (Exception e){
			
			e.printStackTrace();
			System.exit(-1);
			
		}
		
		System.out.println("My Address:" + myAddress.getHostAddress());
		
		DatagramSocket outSocket = null;
		
		try{
			outSocket = new DatagramSocket(63000, myAddress);
		}
		catch (Exception e){
			
			e.printStackTrace();
			System.exit(-1);
		}
		
		rmessage = new BufferedReader(new InputStreamReader(System.in));
		
		// runnable interface
		Thread receiveThread = new Thread(new Runnable (){
			public void run(){
				receiveMethod();
			}
		});
		
		receiveThread.setName("My Datagram Receive Thread");
		receiveThread.start();
		
		
		System.out.println("Start Sending? Press Enter... ");
		//scnr.nextLine();
		
		// get the bytes of the message, and put into a buffer
		//String prefix = "Message Number ";
		
		byte[] buffer = new byte [100];
		
		//for(int i = 0; i <= 10; i++){
			String message = scnr.nextLine();			
			buffer = message.getBytes();
			
			boolean empty = true;
			
			try{
				
				byte[] otherHost = new byte[4];
				otherHost[0] = (byte) 192;
				otherHost[1] = (byte) 168;
				otherHost[2] = (byte) 1;
				otherHost[3] = (byte) 112;
				
					
				
				InetAddress otherAddress = InetAddress.getByAddress(otherHost); 
				InetAddress IPAdress = outSocket.getInetAddress();
				DatagramPacket packet = new DatagramPacket(buffer, message.length(), myAddress, 64000);
				System.out.println(message + " " + IPAdress);
				outSocket.send(packet);
				TimeUnit.SECONDS.sleep(5);
				
			}catch (Exception e) {
				
				e.printStackTrace();
				System.exit(-1);
				
			}
		//}
		
		try{
			TimeUnit.MINUTES.sleep(1);
		}
		catch(Exception e){
			e.printStackTrace();
			System.exit(-1);
		}
		
		System.out.println("Main Method Existing ... Bye ... ");
	}
}


