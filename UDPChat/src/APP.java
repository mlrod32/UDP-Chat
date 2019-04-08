import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.HashMap;

public class APP {
	
	HashMap windows = new HashMap();
	
	
	public void method(InetAddress IP){
		
		if(windows.containsKey(IP))
			receiveMethod();
		}
	
	public static void receiveMethod(){
		
		
		DatagramSocket inSocket = null;
		byte[] inBuffer = new byte[100];
		
		DatagramPacket inPacket = new DatagramPacket(inBuffer, inBuffer.length);
		
		try{
			InetAddress myAddress = InetAddress.getLocalHost();
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
}

