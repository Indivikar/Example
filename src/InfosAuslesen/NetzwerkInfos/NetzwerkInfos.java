package InfosAuslesen.NetzwerkInfos;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;

public class NetzwerkInfos {

	public NetzwerkInfos() {
		System.out.println("Lokale IP:    " + getLocalIpAddress());
		System.out.println("HostName:     " + getLocalHostName());

	}

	public static void main(String[] args) {
		new NetzwerkInfos();
	}

	public String getLocalIpAddress() {
	    try {
	        for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements();) {
	            NetworkInterface intf = en.nextElement();
	            for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements();) {
	                InetAddress inetAddress = enumIpAddr.nextElement();
	                if (!inetAddress.isLoopbackAddress() && inetAddress instanceof Inet4Address) {
	                    return inetAddress.getHostAddress();
	                }
	            }
	        }
	    } catch (SocketException ex) {
	        ex.printStackTrace();
	    }
	    return null;
	}


	public String getLocalHostName(){
		try {
			String hostName = InetAddress.getLocalHost().getHostName();
			return hostName;
//			System.out.println("HostName: " + hostName);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

}
