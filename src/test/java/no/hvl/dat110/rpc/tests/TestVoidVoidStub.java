package no.hvl.dat110.rpc.tests;

import java.io.IOException;

import no.hvl.dat110.rpc.RPCClient;
import no.hvl.dat110.rpc.RPCLocalStub;
import no.hvl.dat110.rpc.RPCUtils;

public class TestVoidVoidStub extends RPCLocalStub {

	public TestVoidVoidStub (RPCClient rpcclient) {
		super(rpcclient);
	}
	
	public void m() throws IOException {
		
		byte[] request = RPCUtils.marshallVoid();
		
		byte[] reply = rpcclient.call((byte)1,request);
		
		RPCUtils.unmarshallVoid(reply);
		
	}
}
