package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class server extends Thread {

	String group = "all-systems.mcast.net";
	static int port = 4000;
	List<String> clients = new ArrayList<String>();
	MulticastSocket multicastSocket;
	ServerSocket server;
	Socket socket;

	public server(int port) {

		
	}

	public static void main(String[] args) {
		/*Thread thread = new server(port);
		thread.start();
		*/
		System.out.println("in main method of server");
		server s = new server(port);
		s.createSocket();
		System.out.println("in main method of server1");
		

	}

	
	public void createSocket(){
		try {
			server = new ServerSocket(port);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Thread thread = new Thread(){
		public void run() {
			System.out.println("server started..");
			try {
				socket = server.accept();
				DataInputStream input = new DataInputStream(socket.getInputStream());
				DataOutputStream output = new DataOutputStream(
						socket.getOutputStream());
				InetAddress ia = InetAddress.getByName(group);
				while (true) {
					String[] string = input.readUTF().split(",");
					System.out.println("string splitted");
					
					if (string[0].equalsIgnoreCase("name")) {

						clients.add(string[1]);
						System.out.println("clients list size :" + clients.size()+"added ->"+string[1]);
						// System.out.println("message received from +"+input.readUTF());
						output.writeUTF(group);
						String multicastMsg = string[0] + " added to the group.";
						byte[] buff = multicastMsg.getBytes();

						multicastSocket = new MulticastSocket(port);
						
						DatagramPacket dp = new DatagramPacket(buff, buff.length,
								ia, port);
						multicastSocket.send(dp);
						//string = input.readUTF().split(" ");

					}
					else if (string[0].equalsIgnoreCase("msg")) {
						System.out.println("else if loop");
						//read msg from client and send it to multicast group.
						// string = input.readUTF().split(","); 
						// System.out.println(string.length);
						String msg = string[1]+" says : "+string[2];
						System.out.println("msg :"+msg);
						byte[] msgBuff = msg.getBytes();
						System.out.println("string to bytes");
						DatagramPacket dp = new DatagramPacket(msgBuff, msgBuff.length,ia,port);
						multicastSocket.send(dp);
						System.out.println("datagram packet sent..");
					//	string = input.readUTF().split(" ");
						System.out.println("message sent to multicast group : "+msg);
					}
;
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				try {
					socket.close();
					multicastSocket.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		}
	};
	thread.setPriority(Thread.MAX_PRIORITY);
	thread.start();
	}
	}
