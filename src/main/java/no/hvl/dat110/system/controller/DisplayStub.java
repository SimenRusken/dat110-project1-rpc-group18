package no.hvl.dat110.system.controller;

import java.io.IOException;

import no.hvl.dat110.TODO;
import no.hvl.dat110.rpc.*;

public class DisplayStub extends RPCLocalStub {

	public DisplayStub(RPCClient rpcclient) {
		super(rpcclient);
	}

	public void write(String message) throws IOException {

		byte[] byteMessage = RPCUtils.marshallString(message);
		byte[] response = rpcclient.call((byte) Common.WRITE_RPCID, byteMessage);
		RPCUtils.unmarshallVoid(response);

	}
}
