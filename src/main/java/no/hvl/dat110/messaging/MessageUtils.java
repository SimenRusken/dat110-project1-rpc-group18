package no.hvl.dat110.messaging;

import java.util.Arrays;

import no.hvl.dat110.TODO;

public class MessageUtils {

	public static final int SEGMENTSIZE = 128;

	public static int MESSAGINGPORT = 8080;
	public static String MESSAGINGHOST = "localhost";

	public static byte[] encapsulate(Message message) {
		
		
		byte[] segment = new byte[SEGMENTSIZE];
		byte[] data = null;
		int index = 0;
		
		
		data = message.getData();
		if (data == null) {
			return null;
		}
		
		int lengdeAvMessage = data.length;
		segment[index] = (byte)lengdeAvMessage;
		index++;
		
		for (int i = 0; i < data.length; i++) {
		    segment[index++] = data[i];
		}
		
		
		return segment;
		
	}

	public static Message decapsulate(byte[] segment) {

		
		int lengde = segment[0];
		
		byte[] data = new byte[lengde];
		
		for (int i = 0; i < lengde; i++) {
			data[i] = segment[i+1];
		}
		
		Message message = new Message(data);
		return message;
	
	}
}
