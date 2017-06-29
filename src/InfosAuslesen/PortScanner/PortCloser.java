package InfosAuslesen.PortScanner;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;

public class PortCloser {

	public static void main(String[] args) {

		Socket Skt;
        String host = "127.0.0.1";
        int i = 2018; // port no.

             try {
                System.out.println("Looking for "+ i);
                Skt = new Socket(host, i);
                System.out.println("There is a Server on port "
                + i + " of " + host);
             }
             catch (UnknownHostException e) {
                System.out.println("Exception occured"+ e);

             }
             catch (IOException e) {
                 System.out.println("port is not used");

             }

	}


		private static boolean isPortInUse(String host, int port) {
			  // Assume no connection is possible.
			  boolean result = false;

			  try {
				(new Socket(host, port)).close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			result = true;

			  return result;
			}
}
