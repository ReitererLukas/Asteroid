package BL;

import javax.sound.sampled.Line;
import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

public class Player
{
  private double x,y;
  private Canvas canvas;
  private Point2D.Double canon,leftFoot,rightFoot;
  private double direction;

  public Player ()
  {
    x = 0;
    y = 0;
    direction = -90;
    canon = new Point2D.Double(1000,1000);
    leftFoot = new Point2D.Double(1000,1000);
    rightFoot = new Point2D.Double(1000,1000);
  }

  public void draw(Graphics2D g)
  {
    g.setColor(Color.WHITE);
    double h = canvas.getHeight()/2.0, w = canvas.getWidth()/2.0;
    //baseline
    Line2D.Double l = new Line2D.Double(w+(x-7.5),h-(y-10),w+(x+7.5),h-(y-10));
    g.draw(l);
    //Sides
    l = new Line2D.Double(w+(x-7.5-4.5),h-(y-10-5),w+x,h-(y+5));
    g.draw(l);
    l = new Line2D.Double(w+(x+7.5+4.5),h-(y-10-5),w+x,h-(y+5));
    g.draw(l);
    canon = new Point2D.Double(x,y+5);
    leftFoot = new Point2D.Double((x-7.5-4.5),(y-10-5));
    rightFoot = new Point2D.Double((x+7.5+4.5),(y-10-5));
  }

  public Canvas getCanvas()
  {
    return canvas;
  }

  public void setCanvas(Canvas canvas)
  {
    this.canvas = canvas;
  }

  public double getX()
  {
    return x;
  }

  public void setX(int x)
  {
    this.x = x;
  }

  public double getY()
  {
    return y;
  }

  public void setY(int y)
  {
    this.y = y;
  }

  public Point2D.Double getCanon()
  {
    return canon;
  }

  public void setCanon(Point2D.Double canon)
  {
    this.canon = canon;
  }

  public double getDirection()
  {
    return direction;
  }

  public void setDirection(double direction)
  {
    this.direction = direction;
  }

  public void rotate(int direction)
  {
    this.direction += direction;
  }

  public Point2D.Double getLeftFoot()
  {
    return leftFoot;
  }

  public void setLeftFoot(Point2D.Double leftFoot)
  {
    this.leftFoot = leftFoot;
  }

  public Point2D.Double getRightFoot()
  {
    return rightFoot;
  }

  public void setRightFoot(Point2D.Double rightFoot)
  {
    this.rightFoot = rightFoot;
  }

  public void move(int delta)
  {
    double dx,dy;
    dx = delta * Math.cos(direction/180*Math.PI);
    dy = delta * Math.sin(direction/180*Math.PI);
    x = x + dx;
    y = y - dy;
  }
}
