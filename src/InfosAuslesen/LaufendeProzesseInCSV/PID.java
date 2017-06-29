package InfosAuslesen.LaufendeProzesseInCSV;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;



public class PID {

	public static void main(String[] args)
    {

		try {
		    String line;
		    Process p = Runtime.getRuntime().exec
		    	    (System.getenv("windir") +"\\system32\\"+"tasklist.exe");
		    BufferedReader input =
		            new BufferedReader(new InputStreamReader(p.getInputStream()));
		    while ((line = input.readLine()) != null) {
		        System.out.println(line); //<-- Parse data here.
		    }
		    input.close();
		} catch (Exception err) {
		    err.printStackTrace();
		}

//		ProzessListeErstellen ple = new ProzessListeErstellen();
//		ple.liste();
//		new PID();

    }

	public PID() {

				File datei = new File("tasklist.csv"); // Läd die Datei
				FileReader schreiber = null;
				try {
					schreiber = new FileReader(datei);
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} // Liest die Datei
				BufferedReader reader = new BufferedReader(schreiber);


//					System.out.println(reader);


				String beendeID;

				int counter = 0;

				while (true)
		       	{
					counter++;
					String line = null;
					try {
						line = reader.readLine();
//						if(line != null){
							System.out.println(line);
//						}

					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

					if( line != null )
		       		{


						if(line.indexOf("chrome.exe") > -1)
						{

							System.out.println(line);

							String lineRA = line.replaceAll("\\s+", ";");

							String[] parts = lineRA.split(";");

							System.out.println(parts[1]);

							try
							{
								Runtime.getRuntime().exec("taskkill /PID " + parts[1]);
							}
							catch (IOException e)
							{
							// TODO Auto-generated catch block
							e.printStackTrace();
							}
							System.out.println("break" + counter);
							break;
						}

					}
					if(counter == 10000)
					{
						System.out.println("break");
						break;
					}

		       	}
				try {

					schreiber.close();
					reader.close();

				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}



			}

	}


