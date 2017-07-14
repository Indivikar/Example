package Kopieren.AusZwischenablageEinfuegen;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.filechooser.FileFilter; // für Dateiauswahldialog
import java.awt.datatransfer.*; // für Copy&Paste und Drap&Drop

public class Editor extends JFrame implements ActionListener {

    JTextArea textfeld = new JTextArea(20, 60);

    public String dateiname;
    public File filedirectory;
    public String filename;

    public Editor() {
        super("Editor - Unbenannt");
        setLocation(100,100);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //-----------------Look-And-Feel-Stil-----------------
           try {
            UIManager.setLookAndFeel (UIManager.getSystemLookAndFeelClassName());
                } catch(Exception e) {};

        //-----------------Menü-----------------
        JMenuBar bar = new JMenuBar();

        //Menüeintrag Bearbeiten
        JMenuItem j21 = new JMenuItem("Kopieren");
        JMenuItem j22 = new JMenuItem("Einfügen");
        JMenu m3 = new JMenu("Bearbeiten");
        m3.add(j21);
        m3.add(j22);
        j21.addActionListener(this);
        j22.addActionListener(this);
        bar.add(m3);


        //-----------------Textfeld-----------------
        JPanel pane = new JPanel(new BorderLayout());
        textfeld.setLineWrap(true);
        textfeld.setWrapStyleWord(true);
        JScrollPane scrollpane = new JScrollPane(textfeld, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        pane.add(scrollpane, BorderLayout.CENTER);

        ausZwischenablageEinfuegen();
        
        setJMenuBar(bar);
        setContentPane(pane);
        pack();
        setVisible(true);
    }

    public static void main (String[] arguments) {
        Editor ed= new Editor();
    }

    
    private String ausZwischenablageEinfuegen(){
    	String clipCont = null;
    	
        Transferable data = Toolkit.getDefaultToolkit().getSystemClipboard().getContents(null);
        DataFlavor[] df = data.getTransferDataFlavors();
        
        try{
        clipCont = (String) data.getTransferData(DataFlavor.stringFlavor);
        textfeld.setText(clipCont);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error -- " + e.toString(),"Error", JOptionPane.ERROR_MESSAGE);
        }
        
		return clipCont;
    }
    
    
    
    public void actionPerformed(ActionEvent evt){

        if (evt.getSource() instanceof JMenuItem) {
            //Dateiauswahldialog-Konstruktor mit Dateifilter
            JFileChooser auswahl = new JFileChooser();
            auswahl.setFileFilter( new FileFilter() {
                public boolean accept( File f ) {
                return f.isDirectory() || f.getName().toLowerCase().endsWith(".txt");
                }
                public String getDescription() {
                return "Texte (*.txt)";
                }
                } );
            String cmd = evt.getActionCommand();


            //Bearbeiten Einfügen
            if (cmd.equals("Einfügen")) {
            	
            	
                Transferable data = Toolkit.getDefaultToolkit().getSystemClipboard().getContents(null);
                DataFlavor[] df = data.getTransferDataFlavors();
                try{
                String clipCont = (String) data.getTransferData(DataFlavor.stringFlavor);
                textfeld.setText(clipCont);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Error -- " + e.toString(),"Error", JOptionPane.ERROR_MESSAGE);
                }
            	
            	
//                Transferable data = Toolkit.getDefaultToolkit().getSystemClipboard().getContents(null);
//                DataFlavor[] df = data.getTransferDataFlavors();
//                for(int i=0; i<df.length; i++)
//                {
//                if (df[i].isMimeTypeEqual(DataFlavor.imageFlavor)) // bild
//                clipbwnd.imgPane.setIcon(new ImageIcon((Image) data.getTransferData(df[i])));
//                         // Übergabe durch eine Datei
//                else if (df[i].isFlavorJavaFileListType()) { // datei
//                List fileList = (List) data.getTransferData(df[i]);
//                File firstFile = (File) fileList.get(0);
//                clipbwnd.imgPane.setIcon(new ImageIcon(firstFile.getAbsolutePath()));
//                }
//                }
//                if(data == null) {
//                JOptionPane.showMessageDialog(this,"Zwischenablage ist leer!","Information",JOptionPane.INFORMATION_MESSAGE); }
//                else {
//                String clipCont = (String) data.getTransferData(DataFlavor.stringFlavor);
//                }
                
                


            }

            //Hilfe
            if (cmd.equals("Info")) {
                textfeld.setText("");
                setTitle("Editor - Unbenannt");
            }



        }

    }
}
 
 
