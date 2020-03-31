package BL;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;
import java.util.LinkedList;

public class Canvas extends JPanel
{
  private Player player;
  private LinkedList<Bullet> list;
  private LinkedList<Asteroid> list2;

  @Override
  public void paintComponent(Graphics g)
  {
    super.paintComponents(g);
    Graphics2D g2d = (Graphics2D) g;

    /*Line2D.Double l = new Line2D.Double(0,getHeight()/2,getWidth(),getHeight()/2);
    g2d.draw(l);
    l = new Line2D.Double(getWidth()/2,0,getWidth()/2,getHeight());
    g2d.draw(l);*/

    if (g != null)
    {
      if (list.size() > 0)
      {
        for (Bullet b : list)
        {
          b.draw(g2d);
        }
      }
      if(list2.size() >0)
      {
        for(Asteroid ast:list2)
        {
          ast.draw(g2d);
        }
      }
      g2d.rotate(Math.toRadians(player.getDirection() + 90), getWidth() / 2 + player.getX(), getHeight() / 2 - player.getY());
      player.draw(g2d);

    }
  }

  public Player getPlayer()
  {
    return player;
  }

  public void setPlayer(Player player)
  {
    this.player = player;
  }

  public void setList(LinkedList<Bullet> list)
  {
    this.list = list;
  }

  public void setList2(LinkedList<Asteroid> list2)
  {
    this.list2 = list2;
  }
}
