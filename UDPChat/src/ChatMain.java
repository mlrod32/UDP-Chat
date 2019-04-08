import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Scanner;

public class ChatMain {

	static APP app = new APP();
	static Scanner scr = new Scanner(System.in);
	
	public static void main(String[]args){
		
		String msg = scr.nextLine();
		try{
		InetAddress IP = InetAddress.getByName("192.168.1.115");
		int port = 64000;
		
		send(msg,IP,port);
		Thread receiveThread = new Thread(new Runnable(){
			public void run(){
				APP.receiveMethod();
			}
		});
		}
		catch(Exception e){
			
		}	
	}
	
	
	public static void send(String msg, InetAddress IP, int port)
	{
		try{
			DatagramSocket outSocket = new DatagramSocket(6400);
			byte[] inbuffer =  new byte[1000];
			
			//byte[] receiveByte = new byte[1000];
			
			
			while(true)
				{
					
					inbuffer  = new byte[1000];
					inbuffer = msg.getBytes();
					DatagramPacket inPacket = new DatagramPacket(inbuffer, inbuffer.length, IP, port);
					outSocket.send(inPacket);
					
					
				}
			
		
		}catch(Exception e){
			
		}
	}
}
