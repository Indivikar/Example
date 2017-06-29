package Swing.ProgrammStartenMitButton;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.FileSystem;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JTree;
import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;



public class Interface extends javax.swing.JFrame implements ActionListener{
private static final long serialVersionUID = -2479348283207711881L;

	private Toolkit t; // ist eine Klasse aus der Bibliothek, wir verwenden sie jetzt um das Fenster auf dem Monitor in die mitte zu setzen
	private int x = 0, y = 0, width = 800,  height = 600;

	// Komponenten


	private JButton 	button_1;
	private JButton 	button_2;


	public Interface() {

		t = Toolkit.getDefaultToolkit(); 			// ist eine Klasse aus der Bibliothek "java.awt.Toolkit", wir verwenden sie jetzt um das Fenster auf dem Monitor in die mitte zu setzen

		Dimension d = t.getScreenSize(); 			// ist eine Klasse aus der Bibliothek "java.awt.Toolkit", damit ermitteln wir die Auflösung vom Monitor
		x = (int) ((d.getWidth() - width) /	2);		// ist eine Klasse aus der Bibliothek "java.awt.Dimension"
		y = (int) ((d.getHeight() - height) / 2);

		setTitle("Swing Tutorial");
		setBounds(x, y, width, height);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		this.initComponents(null);


		setVisible(true);
	}

	private void initComponents(String directory){
		this.getContentPane().setLayout(null); // dieser Befehl sagt, das unser Layout nicht automatisch angeordnet wird, somit müssen alle Komponenten bestimmt werden
		// JMenu inkl. Menus & MenuItem


		// Button 1
		this.button_1 = new JButton("FireFox Starten");
		this.button_1.setBounds(10, 10, 200, 40);
		this.getContentPane().add(button_1); // getContentPane() ist der inhalt vom Fenster

		button_1.addActionListener(this);

		// Button 2
		this.button_2 = new JButton("Beenden");
		this.button_2.setBounds(10, 60, 200, 40);
		this.getContentPane().add(button_2); // getContentPane() ist der inhalt vom Fenster

		button_2.addActionListener(this);




	}




	public static void main(String[] args){
		new Interface();
	}

	private static final String TASKLIST = "tasklist";

	public static boolean isProcessRunging(String serviceName) throws Exception {

	 Process p = Runtime.getRuntime().exec(TASKLIST);
	 BufferedReader reader = new BufferedReader(new InputStreamReader(
	   p.getInputStream()));
	 String line;
	 while ((line = reader.readLine()) != null) {

	  System.out.println(line);
	  if (line.contains(serviceName)) {
	   return true;
	  }
	 }

	 return false;

	}

	private static final String KILL = "taskkill /F/IM/PID ";
	public static void killProcess(String serviceName) throws Exception {

	  Runtime.getRuntime().exec(KILL + serviceName);

	 }
	 public void actionPerformed (ActionEvent ae){
	        // Die Quelle wird mit getSource() abgefragt und mit den
	        // Buttons abgeglichen. Wenn die Quelle des ActionEvents einer
	        // der Buttons ist, wird der Text des JLabels entsprechend geändert
	        if(ae.getSource() == this.button_1){
	        	System.out.println("You clicked the button");
	        	try {
	        		   Process p1 = Runtime.getRuntime().exec("C:/WINDOWS/system32/cmd.exe");
	        		   Process p2 = Runtime.getRuntime().exec("\"C:\\Program Files (x86)\\Mozilla Firefox\\firefox.exe");
	        		   System.out.println("2");
	        		} catch( IOException ex) {
	        			System.out.println("1");
	        		}
	        }

	        if(ae.getSource() == this.button_2){
	        	System.out.println("beenden");
	        	String processName = "FILEZILLA.EXE";
	        	try {
					System.out.print(isProcessRunging(processName));
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        		try {
						if (isProcessRunging(processName)) {

  killProcess(processName);
 }
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

	        	try {


	        			Process p1 = Runtime.getRuntime().exec("kill -9 " + 7900);
	        			Process p2 = Runtime.getRuntime().exec("\"C:\\Program Files (x86)\\Mozilla Firefox\\firefox.exe");
	        			p1.destroy();
	                    p2.destroy();
	        		   System.out.println("3");
	        		} catch( IOException ex) {
	        			System.out.println("4");
	        		}
	        }


	    }
	}


