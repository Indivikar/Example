package InfosAuslesen.WanIPMitURL;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	URL whatismyip = null;
	try {
		whatismyip = new URL("http://checkip.amazonaws.com");
	} catch (MalformedURLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
BufferedReader in = null;
try {
	in = new BufferedReader(new InputStreamReader(
	                whatismyip.openStream()));
} catch (IOException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}

String ip = null;
try {
	ip = in.readLine();
} catch (IOException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
} //you get the IP as a String
System.out.println(ip);
	}

	public Main() {

	}



}


