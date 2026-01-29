package no.hvl.dat110.rpc;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import no.hvl.dat110.TODO;

public class RPCUtils {

	public static byte[] encapsulate(byte rpcid, byte[] payload) {

		int lengthArr = payload.length;
		byte[] rpcmsg = new byte[lengthArr + 1];

		rpcmsg[0] = rpcid;

		for (int i = 0; i < lengthArr; i++) {
			rpcmsg[i + 1] = payload[i];
		}

		return rpcmsg;
	}

	public static byte[] decapsulate(byte[] rpcmsg) {

		byte[] payload = new byte[rpcmsg.length - 1];
		for (int i = 1; i < rpcmsg.length; i++) {
			payload[i - 1] = rpcmsg[i];
		}
		return payload;

	}

	// convert String to byte array
	public static byte[] marshallString(String str) {

		byte[] encoded = str.getBytes();
		int length = encoded.length;

		ByteBuffer buffer = ByteBuffer.allocate(4 + length);
		buffer.putInt(length);
		buffer.put(encoded);
		encoded = buffer.array();
		return encoded;

	}

	// convert byte array to a String
	public static String unmarshallString(byte[] data) {

		ByteBuffer buffer = ByteBuffer.wrap(data);
		int length = buffer.getInt();
		byte[] string = new byte[length];
		buffer.get(string);
		String decoded = new String(string, StandardCharsets.UTF_8);
		return decoded;

	}

	public static byte[] marshallVoid() {

		byte[] encoded = new byte[0];

		return encoded;

	}

	public static void unmarshallVoid(byte[] data) {

	}

	// convert boolean to a byte array representation
	public static byte[] marshallBoolean(boolean b) {
	    byte[] encoded = new byte[1];
	    if (b) {
	        encoded[0] = 1;
	    } else {
	        encoded[0] = 0;
	    }
	    System.out.println("Marshall boolean: " + b + " → " + encoded[0]);
	    return encoded;
	}

	public static boolean unmarshallBoolean(byte[] data) {
	    boolean result = (data[0] > 0);
	    System.out.println("Unmarshall boolean: " + data[0] + " → " + result);
	    return result;
	}

	// integer to byte array representation
	public static byte[] marshallInteger(int x) {

		byte[] encoded = null;

		ByteBuffer buffer = ByteBuffer.allocate(4);
		buffer.putInt(x);
		encoded = buffer.array();

		return encoded;
	}

	// byte array representation to integer
	public static int unmarshallInteger(byte[] data) {

		int decoded = 0;

		ByteBuffer buffer = ByteBuffer.wrap(data);
		decoded = buffer.getInt();

		return decoded;

	}
}
