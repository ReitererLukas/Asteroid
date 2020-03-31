package UI;

import javax.swing.*;
import java.awt.*;

public class DlgGameOver extends JDialog
{
  public DlgGameOver(Frame owner, boolean modal)
  {
    super(owner, modal);
    initComponents();
  }

  public void initComponents()
  {
    butExit = new JButton("Exit");
    butRestart = new JButton("Restart");
    setLayout(new GridLayout(2, 1, 5, 5));
    add(butRestart);
    add(butExit);
    setTitle("Game Over");
    setSize(200, 150);
    setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
    getRootPane().setDefaultButton(butRestart);
    butMethods();
  }

  public void butMethods()
  {
    butExit.addActionListener(new java.awt.event.ActionListener()
    {
      public void actionPerformed(java.awt.event.ActionEvent evt)
      {
        System.exit(0);
      }
    });

    butRestart.addActionListener(new java.awt.event.ActionListener()
    {
      public void actionPerformed(java.awt.event.ActionEvent evt)
      {
        setVisible(false);
      }
    });
  }

  public void setLocation(JFrame frame)
  {
    double xf,xd,yf,yd;
    xf = frame.getWidth()/2.0;
    yf = frame.getHeight()/2.0;
    xd = this.getWidth()/2.0;
    yd = this.getHeight()/2.0;

    setLocation((int)(xf-xd),(int)(yf-yd));
  }

  public static void main(String[] args)
  {
    DlgGameOver dlg = new DlgGameOver(null, true);
    dlg.setVisible(true);
  }


  private JButton butRestart, butExit;
}
