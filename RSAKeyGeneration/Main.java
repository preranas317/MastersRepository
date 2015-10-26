package cs480;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.math.BigInteger;
import java.util.Random;
import java.util.Scanner;

public class Main {

	static BigInteger E;
	static BigInteger N;
	static BigInteger D;

	public static void main(String[] args) throws Exception {
		System.out.println("---------------");

		System.out.println("CS422");
		System.out.println("---------------");

		System.out.println("A RSA based Digital Signature System");
		System.out
				.println("=================================================================");

		
		System.out
				.println("=================================================================");
		System.out.println("---------------");
		System.out.println("      RSA      ");
		System.out.println("---------------");

		boolean flag = true;
		while (flag == true) {
			System.out
					.println("=================================================================");
			System.out
					.println("0. Create keys\n1. Send\n2. Corrupt(Tamper)\n3. Recieve\n4.Exit");
			System.out
					.println("=================================================================");

			Scanner sr = new Scanner(System.in);

			int input = sr.nextInt();
			switch (input) {
			case 0:
				KeyGen kg = new KeyGen();
				boolean create = kg.create();

				System.out.println("keys created - " + create);
				break;
			case 1: {
				FileInputStream fis = new FileInputStream("pubkey.rsa");
				ObjectInputStream ois = new ObjectInputStream(fis);
				E = (BigInteger) ois.readObject();
				N = (BigInteger) ois.readObject();
				ois.close();
				fis = new FileInputStream("privkey.rsa");
				ois = new ObjectInputStream(fis);
				BigInteger D = (BigInteger) ois.readObject();
				System.out.println("------------------");
				System.out.println("E = " + E);
				System.out.println("N = " + N);
				System.out.println("D = " + D);
				System.out.println("------------------");
				ois.close();
				DigitalSignature ds = new DigitalSignature(E, N);
				String everything = null;
				BufferedReader br = new BufferedReader(new FileReader(
						"test.txt"));
				{
					StringBuilder sb = new StringBuilder();
					String line = br.readLine();

					while (line != null) {
						sb.append(line);
						sb.append(System.lineSeparator());
						line = br.readLine();
					}
					everything = sb.toString();
					
				}
				br.close();

				ds.setMessage(everything);
				BigInteger sign = ds.signMessage(D);
				
				FileOutputStream fout = new FileOutputStream("test.txt.signed");
				ObjectOutputStream oos = new ObjectOutputStream(fout);
				oos.writeObject(sign);
				oos.writeObject(everything);
				fout.close();

			}
				break;
			case 2: {
				System.out.println("Corrupt or Tamper message");
				FileInputStream ios = new FileInputStream("test.txt.signed");

				System.out.println("Enter the file name to corrupt");

				String fileName = sr.next();

				ChangeByte cb = new ChangeByte();
				boolean corrupted = cb.corrupt(fileName);
				if (corrupted) {
					System.out.println("File corrupted.....");
				}

			}
				break;
			case 3: {
				System.out.println("Verify Message");

				FileInputStream fis = new FileInputStream("test.txt.signed");
				ObjectInputStream ois = new ObjectInputStream(fis);

				DigitalSignature ds = new DigitalSignature(E, N);

				BigInteger abc = (BigInteger) ois.readObject();
				String s = (String) ois.readObject();
			
				System.out.println(abc+"\n"+s);

				boolean verify = ds.verifyMessage(s, abc);
				ois.close();
				if (verify == true) {
					System.out.println("------------------------------");
					System.out.println("THE MESSAGE WAS NOT CORRUPTED");
					System.out.println("------------------------------");
				} else {
					System.out.println("------------------------------");
					System.out.println("THE MESSAGE WAS TAMPERED");
					System.out.println("------------------------------");
				}

			}
				break;
			case 4:
				flag = false;
				System.exit(0);
				break;
			default:

				System.out.println("Invalid Choice");

			}
		}
	}
}
