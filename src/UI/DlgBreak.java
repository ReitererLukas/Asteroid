package UI;

import BL.Haendler;

import javax.swing.*;
import java.awt.*;

public class DlgBreak extends JDialog
{
  private Haendler haendler;

  public DlgBreak(Frame owner, boolean modal)
  {
    super(owner, modal);
    initComponents();
  }

  public void initComponents()
  {
    butContinue = new JButton("Continue");
    butExit = new JButton("Exit");
    butSettings = new JButton("Settings");
    setLayout(new GridLayout(3, 1, 5, 5));
    add(butContinue);
    add(butSettings);
    add(butExit);
    setTitle("Break");
    setSize(200, 150);
    setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
    getRootPane().setDefaultButton(butContinue);
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

    butSettings.addActionListener(new java.awt.event.ActionListener()
    {
      public void actionPerformed(java.awt.event.ActionEvent evt)
      {
        openSettings();
      }
    });

    butContinue.addActionListener(new java.awt.event.ActionListener()
    {
      public void actionPerformed(java.awt.event.ActionEvent evt)
      {
        setVisible(false);
      }
    });
  }

  public static void main(String[] args)
  {
    DlgBreak dlg = new DlgBreak(null, true);
    dlg.setVisible(true);
  }

  private void openSettings()
  {
    DlgSettings dlg = new DlgSettings(null,true);
    dlg.setHaendler(haendler);
    dlg.setVisible(true);
    if(dlg.isOk())
    {

    }
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

  public Haendler getHaendler()
  {
    return haendler;
  }

  public void setHaendler(Haendler haendler)
  {
    this.haendler = haendler;
  }

  private JButton butContinue, butExit, butSettings;
}
