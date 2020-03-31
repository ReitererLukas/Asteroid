package BL;

import java.awt.*;
import java.awt.geom.Ellipse2D;

public class Bullet
{
  private double direction;
  private double x, y;
  private int rad;
  private Canvas canvas;

  public Bullet(double direction, double x, double y)
  {
    this.direction = direction;
    this.x = x;
    this.y = y;
    rad = 2;
  }

  public void draw(Graphics2D g)
  {
    g.setColor(Color.WHITE);
    double h = canvas.getHeight() / 2.0, w = canvas.getWidth() / 2.0;
    Ellipse2D.Double e = new Ellipse2D.Double(w + x - (double) rad, h - y - (double) rad, 2 * rad, 2 * rad);
    g.draw(e);
  }

  public void move()
  {
    double dx, dy;
    dx = 7.0 * Math.cos(direction / 180.0 * Math.PI);
    dy = 7.0 * Math.sin(direction / 180.0 * Math.PI);
    x = x + dx;
    y = y - dy;
    // y +=delta;
  }

  public void setCanvas(Canvas canvas)
  {
    this.canvas = canvas;
  }

  public double getX()
  {
    return x;
  }

  public void setX(double x)
  {
    this.x = x;
  }

  public double getY()
  {
    return y;
  }

  public void setY(double y)
  {
    this.y = y;
  }

  public double getDirection()
  {
    return direction;
  }

  public void setDirection(double direction)
  {
    this.direction = direction;
  }
}
