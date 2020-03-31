package UI;

import BL.*;
import BL.Canvas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.*;

public class GUI extends JFrame implements KeyListener
{
  private int counter;
  private int numberOfAsteroids;
  private Player player;
  private Canvas canvas;
  private boolean gameOver;
  private Haendler haendler;
  private Set<Integer> pressed;

  public static void main(String[] args)
  {
    GUI g = new GUI();
  }

  public GUI()
  {
    pressed = new HashSet();
    gameOver = false;
    counter = 0;
    canvas = new Canvas();
    player = new Player();
    canvas.setPlayer(player);
    player.setCanvas(canvas);
    numberOfAsteroids = 6;
    haendler = new Haendler();
    haendler.setPlayer(player);
    haendler.setCanvas(canvas);
    go();
  }

  public void go()
  {
    this.setLayout(new java.awt.BorderLayout());
    addKeyListener(this);
    setTitle("Asteroid");
    setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
    setSize(800, 800);
    getContentPane().add(canvas);
    this.setBackground(Color.BLACK);
    setVisible(true);
    gameLoop();
  }

  public void gameLoop()
  {
    boolean game1 = false, game2 = false;
    while (!gameOver)
    {
      game1 = haendler.playerMovement(pressed);
      haendler.moveBullet();
      haendler.moveAsteroid();
      haendler.addAsteroid(counter, numberOfAsteroids);
      haendler.asteroidCollision();
      haendler.bulletHitDetector();
      game2 = haendler.playerHitDetector();
      if (game1 || game2)
      {
        gameOver = true;
      }
      canvas.setList(haendler.getBulletList());
      canvas.setList2(haendler.getAsteroidList());
      refresh();
      try
      {
        Thread.sleep(20);
      } catch (InterruptedException e)
      {
        System.out.println("Fehler");
      }
      counter++;
      if (counter > 1000)
      {
        counter = 1;
        numberOfAsteroids++;
      }
    }
    if (game1)
    {
      DlgBreak dlg = new DlgBreak(this, true);
      dlg.setHaendler(haendler);
      dlg.setLocation(this);
      dlg.setVisible(true);
      gameOver = false;
      pressed.clear();
      gameLoop();
    } else if (game2)
    {
      DlgGameOver dlg = new DlgGameOver(this, true);
      dlg.setLocation(this);
      dlg.setVisible(true);
      restart();
    }

  }

  private void restart()
  {
    player.setX(0);
    player.setY(0);
    player.setDirection(-90);
    haendler.getAsteroidList().clear();
    haendler.getBulletList().clear();
    counter = 0;
    numberOfAsteroids = 6;
    gameOver = false;
    pressed.clear();
    gameLoop();
  }

  private void refresh()
  {
    setSize(getWidth() + 1, getHeight());
    setSize(getWidth() - 1, getHeight());
  }

  @Override
  public void keyTyped(KeyEvent e)
  {
    pressed.add(e.getKeyCode());
  }

  @Override
  public void keyPressed(KeyEvent e)
  {
    pressed.add(e.getKeyCode());
  }

  @Override
  public void keyReleased(KeyEvent e)
  {
    pressed.remove(e.getKeyCode());
  }
}
