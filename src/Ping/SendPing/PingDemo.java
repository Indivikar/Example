package Ping.SendPing;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class PingDemo {
	public static void main(String[] args) throws UnknownHostException, IOException {
	    InetAddress inet;

	    inet = InetAddress.getByAddress(new byte[] { 127, 0, 0, 1 });
	    System.out.println("Sending Ping Request to " + inet);
	    System.out.println(inet.isReachable(5000) ? "Host ist erreichbar" : "Host ist nicht erreichbar");

	    inet = InetAddress.getByAddress(new byte[] { (byte) 173, (byte) 194, 32, 38 });
	    System.out.println("Sending Ping Request to " + inet);
	    System.out.println(inet.isReachable(5000) ? "Host ist erreichbar" : "Host ist nicht erreichbar");
	}
}
