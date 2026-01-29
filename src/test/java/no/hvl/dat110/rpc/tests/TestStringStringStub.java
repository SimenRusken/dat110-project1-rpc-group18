package no.hvl.dat110.rpc.tests;

import java.io.IOException;

import no.hvl.dat110.rpc.RPCClient;
import no.hvl.dat110.rpc.RPCLocalStub;
import no.hvl.dat110.rpc.RPCUtils;

public class TestStringStringStub extends RPCLocalStub {

	public TestStringStringStub(RPCClient rpcclient) {
		super(rpcclient);
	}
	
	public String m(String str) throws IOException {
		
		byte[] request = RPCUtils.marshallString(str);
		
		byte[] reply = rpcclient.call((byte)2,request);
		
		String strres = RPCUtils.unmarshallString(reply);
		
		return strres;
	}
}
