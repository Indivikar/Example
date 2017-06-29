package InfosAuslesen.LaufendeProzesse2;

import java.io.IOException;
import java.util.Scanner;

public class Prozesse{

    public static void main(String[] args) {

		try {
			Process process = new ProcessBuilder("tasklist.exe", "/fo", "csv", "/nh").start();
			   new Thread(() -> {
			        Scanner sc = new Scanner(process.getInputStream());
			        if (sc.hasNextLine()) sc.nextLine();
			        while (sc.hasNextLine()) {
			            String line = sc.nextLine();
			            String[] parts = line.split(",");
			            String unq = parts[0].substring(1).replaceFirst(".$", "");
			            String pid = parts[1].substring(1).replaceFirst(".$", "");
			            System.out.println(line);
			            System.out.println(unq + " " + pid);

			        }
			    }).start();
			   process.waitFor();
		} catch (IOException | InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

    System.out.println("Done");
    }

}
