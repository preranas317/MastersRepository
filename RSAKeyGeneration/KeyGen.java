package cs480;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class KeyGen

{
	private BigInteger p;
	private BigInteger q;
	private BigInteger n;
	private BigInteger phi;
	private BigInteger e;
	private BigInteger d;
	private Random rnd;

	public KeyGen()
	{
		super();
		

	}

	public BigInteger getP()
	{
		return p;
	}

	public BigInteger getQ()
	{
		return q;
	}

	public BigInteger getPhi()
	{
		return phi;
	}

	public BigInteger getE()
	{
		return e;
	}

	public BigInteger getD()
	{
		return d;
	}

	public BigInteger getN()
	{
		return n;
	}

	boolean create() throws IOException
	{
		
		System.out
				.println("==============================================================");

		System.out.println("PART - 1");
		System.out
				.println("==============================================================");

		rnd = new Random();
		System.out.println("-------------------");
		p = BigInteger.probablePrime(512, rnd);

		System.out.println("p = " + p);
		System.out.println("is p prime ? = " + p.isProbablePrime(1));
		System.out.println("p size  = " + p.bitLength() + " bits");
		System.out.println("-------------------");
		q = BigInteger.probablePrime(512, rnd);
		System.out.println("q = " + q);
		System.out.println("is q prime ? = " + q.isProbablePrime(1));
		System.out.println("q size  = " + q.bitLength() + " bits");
		n = p.multiply(q);
		System.out.println("-------------------");
		System.out.println("n = " + n);
		System.out.println("is n prime ? = " + n.isProbablePrime(1));
		System.out.println("n size  = " + n.bitLength() + " bits");

		phi = (p.subtract(BigInteger.ONE)).multiply(q.subtract(BigInteger.ONE));
		System.out.println("-------------------");
		System.out.println("phi = " + phi);
		System.out.println("is phi prime ? = " + phi.isProbablePrime(1));
		System.out.println("phi size  = " + phi.bitLength() + " bits");

		while (true)
		{

			e = BigInteger.probablePrime(512, rnd);

			// If E < PHI
			if (e.compareTo(phi) == -1)
			{
				// If Greatest Common Divisor of E and PHI = 1 exit loop
				if ((e.gcd(phi).compareTo(BigInteger.ONE)) == 0)
				{

					break;
				}

			}

		}

		System.out.println("-------------------");
		System.out.println("e = " + n);
		System.out.println("is e prime ? = " + e.isProbablePrime(1));
		System.out.println("e size  = " + e.bitLength() + " bits");
		System.out.println("-------------------");
		System.out.println("gcd of e and ø(n) = " + e.gcd(phi));
		d = e.modInverse(phi);
		System.out.println("-------------------");
		System.out.println("d = " + d);
		System.out.println("is d prime ? = " + d.isProbablePrime(1));
		System.out.println("d size  = " + d.bitLength() + " bits");

		System.out
				.println("==============================================================");
		
		System.out.println("Generating files....");
		System.out.println("-------------------");
		FileOutputStream fos1 = new FileOutputStream("pubkey.rsa");
		System.out.println("pubkey.rsa generated ");
		ObjectOutputStream oos1 = new ObjectOutputStream(fos1);

		//oos1.writeObject(key.getE());
		
		//oos1.writeObject(key.getN());
		oos1.writeObject(e);
		oos1.writeObject(n);
		oos1.close();
		System.out.println(" e and n saved in pubkey.rsa");
		System.out.println("-------------------");
		FileOutputStream fos2 = new FileOutputStream("privkey.rsa");
		System.out.println("privkey.rsa generated ");
		ObjectOutputStream oos2 = new ObjectOutputStream(fos2);

		//oos2.writeObject(key.getD());
		//oos2.writeObject(key.getN());
		oos2.writeObject(d);
		oos2.writeObject(n);
		oos2.close();
		System.out.println(" d and n saved in privkey.rsa");

		return true;

	}

}
