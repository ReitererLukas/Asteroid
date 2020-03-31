package UI;

import BL.Haendler;

import javax.swing.*;
import java.awt.*;

public class DlgSettings extends JDialog
{
  private int tab, forward, right, left, shoot,pause;
  private boolean ok;
  private Haendler haendler;
  private JTextField[] tfArr;

  public DlgSettings(Frame owner, boolean modal)
  {
    super(owner, modal);
    tab = 0;
    ok = false;
    initComponents();
  }

  private void initComponents()
  {
    setTitle("Settings");
    setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
    bar = new JMenuBar();
    mainPanel = new JPanel();
    itemControls = new JMenuItem("Controls");
    controlPanel = new JPanel();
    butOK = new JButton("OK");
    butExit = new JButton("Exit");
    menu = new JMenu("Settings");

    setLayout(new BorderLayout());
    controlPanel.setLayout(new GridLayout(1, 2, 5, 5));
    setSize(300, 300);
    setJMenuBar(bar);
    bar.add(menu);
    menu.add(itemControls);
    add(mainPanel, BorderLayout.CENTER);
    add(controlPanel, BorderLayout.SOUTH);
    controlPanel.add(butOK);
    controlPanel.add(butExit);

    butMethods();
  }

  private void butMethods()
  {
    itemControls.addActionListener(new java.awt.event.ActionListener()
    {
      public void actionPerformed(java.awt.event.ActionEvent evt)
      {
        buildControls();
      }
    });

    butOK.addActionListener(new java.awt.event.ActionListener()
    {
      public void actionPerformed(java.awt.event.ActionEvent evt)
      {
        ok = true;
        changeControls();
        setVisible(true);
      }
    });

    butExit.addActionListener(new java.awt.event.ActionListener()
    {
      public void actionPerformed(java.awt.event.ActionEvent evt)
      {
        ok = false;
        setVisible(false);
      }
    });
  }

  private void buildControls()
  {
    setTitle("Settings - Controls");
    tab = 1;
    this.remove(mainPanel);
    mainPanel = new JPanel();
    add(mainPanel, BorderLayout.CENTER);
    tf1 = new JTextField();
    tf2 = new JTextField();
    tf3 = new JTextField();
    tf4 = new JTextField();
    tf5 = new JTextField();
    tfArr = new JTextField[5];

    mainPanel.setLayout(new GridLayout(5, 2, 5, 5));
    mainPanel.add(new JLabel("Forward"));
    mainPanel.add(tf1);
    tfArr[0] = tf1;
    mainPanel.add(new JLabel("Left"));
    mainPanel.add(tf2);
    tfArr[1] = tf2;
    mainPanel.add(new JLabel("Right"));
    mainPanel.add(tf3);
    tfArr[2] = tf3;
    mainPanel.add(new JLabel("Shoot"));
    mainPanel.add(tf4);
    tfArr[3] = tf4;
    mainPanel.add(new JLabel("Break"));
    mainPanel.add(tf5);
    tfArr[4] = tf5;
    refresh();
  }

  private void changeControls()
  {
    int i = 0;
    int[] num = new int[5];
    while(i < tfArr.length)
    {
      try{
        num[i] = Integer.parseInt(tfArr[i].getText());
      }
      catch (NumberFormatException ex)
      {
        num[i] = -1;
      }
    }
    if(num[0] != -1)
    {
      haendler.setForward(num[0]);
    }
    if(num[1] != -1)
    {
      haendler.setLeft(num[1]);
    }
    if(num[2] != -1)
    {
      haendler.setRight(num[2]);
    }
    if(num[3] != -1)
    {
      haendler.setShoot(num[3]);
    }
    if(num[4] != -1)
    {
      haendler.setPause(num[4]);
    }
  }


  public static void main(String[] args)
  {
    DlgSettings dlg = new DlgSettings(null, true);
    dlg.setVisible(true);
  }

  public void refresh()
  {
    setSize(getWidth() + 1, getHeight());
    setSize(getWidth() - 1, getHeight());
  }

  public int getTab()
  {
    return tab;
  }

  public void setTab(int tab)
  {
    this.tab = tab;
  }

  public boolean isOk()
  {
    return ok;
  }

  public void setOk(boolean ok)
  {
    this.ok = ok;
  }

  public int getForward()
  {
    return forward;
  }

  public void setForward(int forward)
  {
    this.forward = forward;
  }

  public int getRight()
  {
    return right;
  }

  public void setRight(int right)
  {
    this.right = right;
  }

  public int getLeft()
  {
    return left;
  }

  public void setLeft(int left)
  {
    this.left = left;
  }

  public int getShoot()
  {
    return shoot;
  }

  public void setShoot(int shoot)
  {
    this.shoot = shoot;
  }

  public int getPause()
  {
    return pause;
  }

  public void setPause(int pause)
  {
    this.pause = pause;
  }

  public Haendler getHaendler()
  {
    return haendler;
  }

  public void setHaendler(Haendler haendler)
  {
    this.haendler = haendler;
  }

  private JMenuBar bar;
  private JMenuItem itemControls;
  private JMenu menu;
  private JPanel mainPanel, controlPanel;
  private JButton butOK, butExit;
  private JTextField tf1, tf2, tf3, tf4, tf5;


}
