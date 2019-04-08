import java.io.BufferedReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.HashMap;

public class udpmain {
	
	static HashMap chatWindows;
	static byte[] inbuffer;
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		server();
	}
	
	public static void server()
	{
		try{
			DatagramSocket inSocket = new DatagramSocket(6400);
			//byte[] receiveByte = new byte[1000];
			chatWindows = new HashMap();
			
			while(true)
				{
					inbuffer  = new byte[1000];
					DatagramPacket inPacket = new DatagramPacket(inbuffer, inbuffer.length);
					inSocket.receive(inPacket);
					if(chatWindows.containsKey(inPacket.getAddress()))
					{
								
					}
					else{
						chatWindows.put(inPacket.getAddress(), inSocket.getPort());
					}
					
					//System.out.println(chatWindows);
					
					String msg = new String(inPacket.getData());
					System.out.println(msg);
					
					DatagramPacket servPacket = new DatagramPacket(inbuffer, inbuffer.length, inPacket.getAddress(), inPacket.getPort());
					inSocket.send(servPacket);
					
				}
			
		
		}catch(Exception e){
			
		}
	}

}
