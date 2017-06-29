package InfosAuslesen.LaufendeProzesseInCSV;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;


public class ProzessListeErstellen implements Runnable
	{

		 public void liste()
		 	{


							 	try {
									Processes.ALL_PROCESSES.listProcesses();
								} catch (Exception e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							 	System.out.println("Datei angelegt");

							 	//s.printStatus();
//							 	ServerProg s = new ServerProg();
//							 	Status sta = new Status();
//							 	Thread th = new Thread(sta);
//							 	th.start();
//
//							 	System.out.println("Thread gestartet");
//							 	new Status();

			 }

public static enum Processes implements IProcessListingStrategy {
 ALL_PROCESSES;

 private IProcessListingStrategy processListing = selectProcessListingStrategy();

 @Override
 public void listProcesses() throws Exception {
     processListing.listProcesses();
 }

 private IProcessListingStrategy selectProcessListingStrategy() {
     //todo add support for mac ...
     return isWindows() ? new WinProcessListingStrategy() : new LinuxProcessListingStrategy();
 }

 private static boolean isWindows() {
     return System.getProperty("os.name").toLowerCase().indexOf("win") >= 0;

 }
}

static interface IProcessListingStrategy {
 void listProcesses() throws Exception;
}

static abstract class AbstractNativeProcessListingStrategy implements IProcessListingStrategy {
 @SuppressWarnings("null")
	@Override
 public void listProcesses() throws IOException {
     Process process = makeProcessListingProcessBuilder().start();
     Scanner scanner = new Scanner(process.getInputStream());
     scanner.close();
    // process.waitFor();




 }



	protected abstract ProcessBuilder makeProcessListingProcessBuilder();
}

static class WinProcessListingStrategy extends AbstractNativeProcessListingStrategy {
 @Override
 protected ProcessBuilder makeProcessListingProcessBuilder() {
     return new ProcessBuilder("cmd", "/c", "tasklist.exe > tasklist.csv");
 }
}

static class LinuxProcessListingStrategy extends AbstractNativeProcessListingStrategy {
 @Override
 protected ProcessBuilder makeProcessListingProcessBuilder() {
     return new ProcessBuilder("ps", "-e");
 }
}

@Override
public void run() {
	// TODO Auto-generated method stub

}}


