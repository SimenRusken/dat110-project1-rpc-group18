package no.hvl.dat110.system.controller;

import java.io.IOException;
import java.net.UnknownHostException;

import no.hvl.dat110.TODO;
import no.hvl.dat110.rpc.RPCClient;
import no.hvl.dat110.rpc.RPCClientStopStub;

public class Controller {

	private static int N = 5;

	public static void main(String[] args) throws UnknownHostException, IOException {

		DisplayStub display;
		SensorStub sensor;

		RPCClient displayclient, sensorclient;

		System.out.println("Controller starting ...");

		// create RPC clients for the system
		displayclient = new RPCClient(Common.DISPLAYHOST, Common.DISPLAYPORT);
		sensorclient = new RPCClient(Common.SENSORHOST, Common.SENSORPORT);

		// setup stop methods in the RPC middleware
		RPCClientStopStub stopdisplay = new RPCClientStopStub(displayclient);
		RPCClientStopStub stopsensor = new RPCClientStopStub(sensorclient);

		display = new DisplayStub(displayclient);
		sensor = new SensorStub(sensorclient);

		displayclient.connect();
		sensorclient.connect();

		for (int i = 0; i < N; i++) {
			int readNumber = sensor.read();
			display.write("" + readNumber);
			
			try {
				Thread.sleep(2000);
			} catch(InterruptedException e) {
				e.printStackTrace();
			}
		}

		stopdisplay.stop();
		stopsensor.stop();

		displayclient.disconnect();
		sensorclient.disconnect();

		System.out.println("Controller stopping ...");

	}
}
