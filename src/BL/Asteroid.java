package BL;

import java.awt.*;
import java.awt.geom.Ellipse2D;

public class Asteroid
{
  private double x, y, direction;
  private Canvas canvas;
  private Size size;
  private int rad, delta;

  public Asteroid(double x, double y, double direction, Size size)
  {
    this.x = x;
    this.y = y;
    this.direction = direction;
    this.size = size;
    if (size.equals(Size.BIG))
    {
      rad = 50;
      delta = 2;
    } else if (size.equals(Size.MEDIUM))
    {
      rad = 25;
      delta = 3;
    } else
    {
      rad = 15;
      delta = 5;
    }

  }

  public void draw(Graphics2D g)
  {
    g.setColor(Color.WHITE);
    double h = canvas.getHeight() / 2.0, w = canvas.getWidth() / 2.0;
    Ellipse2D.Double e = new Ellipse2D.Double(w + x - (double)rad, h - y - (double)rad, 2 * rad, 2 * rad);
    g.draw(e);
  }

  public void move()
  {
    double dx, dy;
    dx = (double)delta * Math.cos(direction / 180.0 * Math.PI);
    dy = (double)delta * Math.sin(direction / 180.0 * Math.PI);
    x += dx;
    y -= dy;
    //x+=delta;
  }

  public void rotate(int alpha)
  {
    direction += alpha;
  }

  public double getDirection()
  {
    return direction;
  }

  public void setDirection(double direction)
  {
    this.direction = direction;
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

  public Canvas getCanvas()
  {
    return canvas;
  }

  public void setCanvas(Canvas canvas)
  {
    this.canvas = canvas;
  }

  public Size getSize()
  {
    return size;
  }

  public void setSize(Size size)
  {
    this.size = size;
  }

  public int getRad()
  {
    return rad;
  }

  public void setRad(int rad)
  {
    this.rad = rad;
  }
}
