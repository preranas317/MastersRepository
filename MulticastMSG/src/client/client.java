package client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class client {
	
	
	public client(){
		
	}
	
	public static void main(String[] args) throws IOException {
		
		Socket socket = null;
		String host = "localhost";
		int port = 4000;
		System.out.println("client main method..");
		try {
			
			socket = new Socket("localhost",port);
			System.out.println("client connected..");
			DataOutputStream out = new DataOutputStream(socket.getOutputStream());
			DataInputStream in = new DataInputStream(socket.getInputStream());
			
			System.out.println("enter username : ");
			Scanner sc = new Scanner(System.in);
			String name = sc.next();
			out.writeUTF("name,"+name);
			String groupAddr = in.readUTF();
			System.out.println("group addr : "+groupAddr);
			MulticastSocket multicastSocket = new MulticastSocket(port);
			
			InetAddress ia = InetAddress.getByName(groupAddr);
			multicastSocket.joinGroup(ia);
			String msg  = null;
			System.out.println("group joined..");
			while(true){
				//write msg for multicast group
				System.out.println("write msg to the group : ");
				sc = new Scanner(System.in);
				msg = sc.next();
				out.writeUTF("msg,"+name+","+msg);
				System.out.println("msg written");
				
				
			}
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			socket.close();
		}
	}

}
