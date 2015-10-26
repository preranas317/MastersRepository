import java.net.DatagramPacket;
import java.net.MulticastSocket;
import java.net.Socket;

public class multicast extends Thread {

	MulticastSocket ms = null;
	Socket socket = null;

	public multicast(MulticastSocket ms, Socket socket) {
		this.ms = ms;
		this.socket = socket;

	}

	public void run() {
		

			byte buf[] = new byte[1024];
			while (true) {
				DatagramPacket data = new DatagramPacket(buf, buf.length);
				try {
					sleep(300);
					ms.receive(data);

					@SuppressWarnings("deprecation")
					String msg1 = new String(data.getData(), 0, 0,
							data.getLength());
				String[] a = msg1.split(" ");
				
					System.out.println(msg1);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		;
	;

}
