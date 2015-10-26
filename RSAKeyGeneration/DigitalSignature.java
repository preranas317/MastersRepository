package cs480;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.math.BigInteger;
import java.nio.file.Files;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@SuppressWarnings({ "unused", "serial" })
public class DigitalSignature implements Serializable {
	private String theMessage; // Plaintext message

	private boolean signed; // is message signed
	private BigInteger theSignature; // signature for message
	private BigInteger E; // public key exponent
	private BigInteger N; // public key mod value

	public DigitalSignature(BigInteger E, BigInteger N) {
		this.E = E;
		this.N = N;

	}

	public DigitalSignature() {
		super();
	}

	public void setMessage(String s) {
		theMessage = s;
	}

	public String getMessage() {
		return theMessage;
	}

	public boolean isSigned() {
		return signed;
	}

	public BigInteger signMessage(BigInteger D) throws Exception {

		MessageDigest m1 = MessageDigest.getInstance("MD5");

		byte[] b1 = theMessage.getBytes();

		m1.update(b1);

		byte[] digest1 = m1.digest();

		BigInteger orig1Int = new BigInteger(1, digest1);

		theSignature = orig1Int.modPow(D, this.N);

		signed = true;
		return theSignature;

	}

	public boolean verifyMessage(String message, BigInteger theSignature)
			throws Exception {
		MessageDigest m1 = MessageDigest.getInstance("MD5");

		byte[] b1 = message.getBytes();
		m1.update(b1);

		byte[] digest1 = m1.digest();

		BigInteger orig1Int = new BigInteger(1, digest1);

		BigInteger encrypted = theSignature.modPow(this.E, this.N);

		if (orig1Int.equals(encrypted)) {
			return true;
		}

		return false;

	}

}
