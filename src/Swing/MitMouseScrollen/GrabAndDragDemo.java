package Swing.MitMouseScrollen;

import java.awt.Container;
import java.awt.Cursor;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JViewport;
import javax.swing.event.MouseInputAdapter;

public class GrabAndDragDemo extends JFrame {
  public GrabAndDragDemo() {
    super("Grab and drag Demo");
    ImageIcon ii = new ImageIcon(GrabAndDragDemo.class.getResource( "Map.PNG" ));
    JScrollPane jsp = new JScrollPane();
    jsp.setViewportView(new GrabAndScrollLabel(ii));
    getContentPane().add(jsp);
    setSize(300, 250);
    setVisible(true);

    WindowListener l = new WindowAdapter() {
      public void windowClosing(WindowEvent e) {
        System.exit(0);
      }
    };
    addWindowListener(l);
  }

  public static void main(String[] args) {
    new GrabAndDragDemo();
  }
}

class GrabAndScrollLabel extends JLabel {
  public GrabAndScrollLabel(ImageIcon i) {
    super(i);

    MouseInputAdapter mia = new MouseInputAdapter() {
      int xDiff, yDiff;

      boolean isDragging;

      Container c;

      public void mouseDragged(MouseEvent e) {
        c = GrabAndScrollLabel.this.getParent();
        if (c instanceof JViewport) {
          JViewport jv = (JViewport) c;
          Point p = jv.getViewPosition();
          int newX = p.x - (e.getX() - xDiff);
          int newY = p.y - (e.getY() - yDiff);

          int maxX = GrabAndScrollLabel.this.getWidth()
              - jv.getWidth();
          int maxY = GrabAndScrollLabel.this.getHeight()
              - jv.getHeight();
          if (newX < 0)
            newX = 0;
          if (newX > maxX)
            newX = maxX;
          if (newY < 0)
            newY = 0;
          if (newY > maxY)
            newY = maxY;

          jv.setViewPosition(new Point(newX, newY));
        }
      }

      public void mousePressed(MouseEvent e) {
        setCursor(Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR));
        xDiff = e.getX();
        yDiff = e.getY();
      }

      public void mouseReleased(MouseEvent e) {
        setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
      }
    };
    addMouseMotionListener(mia);
    addMouseListener(mia);
  }
}
