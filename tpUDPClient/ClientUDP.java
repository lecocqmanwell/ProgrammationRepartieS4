package tpUDPClient;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class ClientUDP {
	
	private DatagramSocket sc;

	ClientUDP() throws IOException{
		sc = new DatagramSocket();
	}
	
	void go() throws IOException{
		
		byte [] buf = "Bonjour le monde !".getBytes();
		
		DatagramPacket out = new DatagramPacket(buf, buf.length, InetAddress.getByName("bouleau12"), 9890);
		
		sc.send(out);
		
	
		byte [] bufRecept = new byte[100];
		DatagramPacket in = new DatagramPacket(bufRecept, bufRecept.length);
		sc.receive(in);
		String message = new String(in.getData(), in.getOffset(), in.getLength());
		System.out.println(message);
		
	}
	public static void main(String[] args) throws IOException {
		new ClientUDP().go();
	}

}
