package cs480;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.math.BigInteger;
import java.util.Random;
import java.util.Scanner;

public class ChangeByte {

	public boolean corrupt(String fileName) throws IOException,
			ClassNotFoundException {

		Scanner sr = new Scanner(System.in);
		FileInputStream fis = new FileInputStream(fileName);
		ObjectInputStream ois = new ObjectInputStream(fis);

		BigInteger q = (BigInteger) ois.readObject();
		String data = (String) ois.readObject();

		byte[] b = data.getBytes();
		Random rn = new Random();
		int max = 100;
		int min = 1;
		int replacement = rn.nextInt(max - min + 1);

		System.out.println("enter the byte number to tamper : ");
		System.out.println("the number should be between " + 0 + " & "
				+ b.length);
		int tamper = sr.nextInt();
		b[tamper] = (byte) replacement;
		///String x = b.toString();
		//System.out.println("x-" + x);
		String some = new String(b, "UTF-8");
		System.out.println("some"+some);
		FileOutputStream fos = new FileOutputStream(fileName);
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(q);
		oos.writeObject(some);
		oos.close();

		return true;

	}

}
