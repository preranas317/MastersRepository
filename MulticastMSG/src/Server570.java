import java.io.*;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;

public class Server570 {
	private ServerSocket serverSocket = null;
	private Socket socket = null;
	/*
	 * private InputStream inStream = null; private OutputStream outStream =
	 * null;
	 */
	String group = "all-systems.mcast.net";
	static int port = 4000;
	List<String> clients = new ArrayList<String>();
	MulticastSocket multicastSocket;
	ServerSocket server;

	// int i=0;
	public Server570() throws IOException {
		serverSocket = new ServerSocket(port);
	}

	public void createSocket() {
		try {

			while (true) {
				socket = serverSocket.accept();

				//System.out.println("Connected to clinet");
				createReadThread();
				// createWriteThread();

			}
		} catch (IOException io) {
			io.printStackTrace();
		}
	}

	public void createReadThread() {
		Thread readThread = new Thread() {
			@SuppressWarnings("deprecation")
			public void run() {
				try {
					while (true) {
						DataInputStream input = new DataInputStream(
								socket.getInputStream());
						DataOutputStream output = new DataOutputStream(
								socket.getOutputStream());
						InetAddress ia = InetAddress.getByName(group);
					
						String abc = input.readUTF();
						if(abc!=null){
							String[] string = abc.split("//");
						//System.out.println("string splitted");

						if (string[0].equalsIgnoreCase("name")) {

							clients.add(string[1]);
							System.out.println(string[1]+" added to the client list");
							
							output.writeUTF(group);
							String multicastMsg = string[1]
									+ " added to the group.";
							byte[] buff = multicastMsg.getBytes();

							multicastSocket = new MulticastSocket(port);
							multicastSocket.joinGroup(ia);
							DatagramPacket dp = new DatagramPacket(buff,
									buff.length, ia, port);
							multicastSocket.send(dp);
							// string = input.readUTF().split(" ");
							
						} else if (string[0].equalsIgnoreCase("msg")) {
							
							// read msg from client and send it to multicast
							// group.

						/*	System.out.println("multicast Socket : "
									+ multicastSocket);*/
							if (string[2].equals(".")) {
								output.writeUTF("you can log off");
								//stop();
								// user wants to sign off
								String signOff = string[1] + " has logged off.";
								byte[] msg = signOff.getBytes();
								DatagramPacket dp = new DatagramPacket(msg,
										msg.length, ia, port);
								multicastSocket.send(dp);
								System.out.println("multicast message : " + signOff);
								break;
								
							} else {
								sleep(100);
								String msg = string[1] + " : " + string[2];
								//System.out.println("msg :" + msg);

								byte[] msgBuff = msg.getBytes();

							//	System.out.println("string to bytes");
								DatagramPacket dp = new DatagramPacket(msgBuff,
										msgBuff.length, ia, port);
								multicastSocket.send(dp);
								//System.out.println("datagram packet sent..");
								// string = input.readUTF().split(" ");
								byte[] buff = dp.getData();

								@SuppressWarnings("deprecation")
								String msg1 = new String(dp.getData(), 0, 0,
										dp.getLength());
								
								System.out.println("multicast message : " + msg1);
							}
						}
						;
					}}

				} catch (SocketException se) {
//					System.exit(0);

				} catch (IOException i) {
					i.printStackTrace();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		};
		readThread.setPriority(Thread.MAX_PRIORITY);
		readThread.start();
	}

	public static void main(String[] args) throws IOException {
		Server570 chatServer = new Server570();
		System.out.println("server started..");
		chatServer.createSocket();

	}
}
