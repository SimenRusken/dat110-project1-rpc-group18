package no.hvl.dat110.rpc;

import java.io.IOException;
import java.net.UnknownHostException;

import no.hvl.dat110.TODO;
import no.hvl.dat110.messaging.*;

public class RPCClient {

	// underlying messaging client used for RPC communication
	private MessagingClient msgclient;

	// underlying messaging connection used for RPC communication
	private MessageConnection connection;
	
	public RPCClient(String server, int port) {
	
		msgclient = new MessagingClient(server,port);
	}
	
	public void connect() throws UnknownHostException, IOException {
		
		connection = msgclient.connect();
	}
	
	public void disconnect() {
		
		connection.close();
	}

	/*
	 Make a remote call om the method on the RPC server by sending an RPC request message and receive an RPC reply message

	 rpcid is the identifier on the server side of the method to be called
	 param is the marshalled parameter of the method to be called
	 */

	public byte[] call(byte rpcid, byte[] param) throws IOException {
		
		byte[] returnval = null;
		
		byte[] arr = new byte[param.length+1];
		arr[0] = rpcid;
		for (int i = 1; i < arr.length; i++) {
			arr[i] = param[i-1];
		}
		
		
		
		Message request = new Message(arr);
		connection.send(request);
		
		Message response = connection.receive();
		byte[] byteResponse = response.getData();
		
		returnval = RPCUtils.decapsulate(byteResponse);
		
		
		return returnval;
		
	}

}
