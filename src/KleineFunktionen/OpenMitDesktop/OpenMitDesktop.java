package KleineFunktionen.OpenMitDesktop;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;


public class OpenMitDesktop {

		public static void main(String[] args) {
		new OpenMitDesktop();

	}

		private URI pfadURI;

	public OpenMitDesktop() {

		try {
			pfadURI = OpenMitDesktop.class.getProtectionDomain().getCodeSource().getLocation().toURI();
		} catch (URISyntaxException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {

			Desktop.getDesktop().browse(new File(pfadURI).toURI());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



}
